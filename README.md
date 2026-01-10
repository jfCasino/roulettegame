# jfcasino - PRPO projekt

## birokratske informacije
Člani: Jaka Furlan, 63230069
Ime skupine: Enostavna Ruleta
Skupina: samostojno 8

## pregled projekta
Ideja projekta je ustvariti spletni kazino, v katerem bodo igralci lahko igrali razne igre.
Moj proof of concept - enostavna izpeljava te ideje v sklopu tega predmeta vključuje sledeče funkcionalnosti:
- igralci lahko dostopajo do svoje denarnice
- igralci lahko stavijo na igro rulete
- igralci lahko dostopajo do statistike svojih stav in statistike najboljših igralcev
Ker sem porjekt delal samostojno, in mi je bila večina pojmov novih, 
je projekt izveden in zasnovan tako, da je nanj v prihodnje kar se da lahko dodati druge funkcionalnosti,
(če bi se ga odločil kdaj razširiti)kot so registracija uporabnikov, možnost več denarnic na enega uporabnika
(v primeru večih valut), enotne sheme statistik, v primeru dodajanja večih iger, bo lahko do vseh stav iz vseh
iger uporabnik dostopal iz že implementirane stats-service.

Cilj projekta je bil pokazati pridobljeno znanje pri predmetu PRPO, s fokusom na pravilni zasnovi
arhitekture mikrostoritev in manjšem poudarku na tehnični zahtevnosti.

## pregled arhitekture

### Mikrostoritve
Glede na funkcionalnosti, so implementirane sledeče mikrostoritve:
- **wallet service**
    - skrb za upravljanje z denarnicami.
    - skrbi za poizvedbe o bilanci, rezervaciji sredstev za stave in prilivu sredstev

- **roulette service**
    - skrbi za izvedbo stav na ruleti
    - v svoji bazi hrani vse stave, ki se zgodijo v njeni domeni
    - preko REST komunicira z wallet-service, kjer rezervira in priliva sredstva

- **stats-service**
    - zbira in shranjuje podatke o stavah (vseh iger) uporabnikov, sortirane glede na uporabnika in čas
    - asinhrono komunicira preko Kafke z roulette-service, iz katere dobi informacije o stavah
      ideja tega je, da bi ob implementaciji drugih iger (npr blackjack) tudi te podatke o stavah
      preko Kafke poslale k tej storitvi.

### komunikacija
 **REST**
 - preko REST protokola poteka vsa komunikacija s klientom,
    ta je omejena na prikaz sredstev v denarnici, stave na ruleti in prikaz statistike
 - preko REST protokola poteka tudi komunikacija med roulette-service in wallet-service
    in je omejena na rezervacijo in priliv sredstev. Prav tako pa preko REST komunicira stats-service
    z wallet-service, ko poizveduje o najboljših igralcih
 **Kafka**
  - preko kafke komunicirata roulette-service (provider) in stats-service (consumer). Vse bodoče igre, bi se dodale k providerjem.

### diagram arhitekture

        Client
    /      |      \
   v       v       v
Wallet   Roulette  Stats
   ^        |        v
   |        |        |
   +        +--Kafka--+
   |                 |
   +------ REST ------+

## tehnologije
 - Java 21, Spring Boot -> backend ogrodje
 - Maven -> build sistem
 - Angular -> frontend
 - Postgres -> podatkovna baza
 - Hibernate -> ORM
 - Open Feign -> komunikacija med mikrostoritvami
 - Kafka -> komunikacija med mikrostoritvami
 - Docker
 - Kubernetes
 - Spring Boot Actuator -> preverjanje zdravja (http://localhost:8082/swagger-ui/index.html)
 - Swagger/OpenApi -> dokumentacija API endpointov
 - Azure -> deployment
 - ingress controlet

## API endpoints

### Wallet service
 - POST/wallets/create
 - GET/wallets/{userId}
 - GET/wallets
 - POST/wallets/reserve
 - POST/wallets/commit

### Roulette service
 - POST/roulette/bet
 - GET/roulette/bets

### Stats service
 - GET/stats/bets/{userId}
 - GET/stats/leaderboard
 - GET/stats/leaderboard/top5

## poženemo lokalno z
 "docker-compose up --build" vsako mikrostoritev skupaj z bazo
 frontend z "ng serve"
 kafko z "docker-compose-kafka up --build" (file se nahaja znotraj roulette-service)

## deployment
 Azure, preko kubernetes, skupaj s postgres bazo, in eventhubs kafko.
 Dostop na ip: http://4.178.14.66/ 
