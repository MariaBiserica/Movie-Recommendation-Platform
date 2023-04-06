# Platforma de recomandat filme
Acest proiect implementează o aplicație de recomandare a filmelor cu mai multe roluri, construită folosind IntelliJ IDEA un cadru Java, și SceneBuilder, un instrument de aspect vizual pentru JavaFX, creând o interfață grafică lină, ușor de utilizat, facilitând proiectarea și personalizarea aspectului platformei. Dispunând de o pagină de conectare / înregistrare, un client își poate asuma fie rolul unui utilizator, fie al unui administrator (manager al setului de date filme). Platforma oferă un flux de recomandări personalizate bazate pe istoricul vizionărilor și evaluările utilizatorului, ajutându-l să descopere filme noi de care este posibil să se bucure.

Un user poate aprecia filme dintr-un dataset dispoinibil (fisier). Apoi pe baza filmelor recomandate sistemul recomanda (pe baza unei prioritati genre-likes) filme asemanatoare in feedul userului.

Feed-ul initial (cand nu a apreciat nici un film) este compus din filme aleatorii. Apo pe baza aprecierilor se vor aduaga la inceputul listei filme noi asemanatoare cu cele apreciate.

Aplicatia prezinta o interfata grafica care se deschide cu o pagina de Login/Register. Aici putem alege dintre două tipuri de utilizatori (Admin / User), care au roluri diferite:

Admin:
-	Poate dauga filme in fisier

User:
-	Poate cauta in fisier toate filmele
-	Poate aprecia un film
-	Isi poate vizualiza feed-ul lui
