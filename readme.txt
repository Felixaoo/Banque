Création des chemins nécessaires pour l'utilisation de l'application banque: 
--> Création de fiche clients: C:\filRouge\Banque\fiche
--> Création de fichiers transactions: C:\filRouge\Banque\transactions
--> Création du QR Code: C:\Test
--> Eventuelles futures transactions: C:\filRouge\Central

Librairies nécessaires: 
Les librairies nécessaires au fonctionnement de l'application se situent dans le dossier librairies du ZIP.

Avant de lancer l'application pour la première fois: 
Placer le fichier Banque.txt (inclus dans le dossier zip) dans le dossier C:\filRouge\Banque

Règles de gestion pour la saisie: 
- Le code pays se fait sur 2 lettres;
- Le code postal se fait sur 5 chiffres. 
- Le code d'agence se fait sur 3 chiffres.
- ID client: 2 lettres Majuscules et 6 chiffres comme AA0000;
- Datedenaissance du client: doit être une date valide (cependant, ne prends pas en compte les années bissextile);
- Mail du client: une ou plusieur lettres et/ou points; un @, un nom de domaine entre 5 et 10 lettres, un point et enfin une extension sur 2 ou 3 lettres. 
- Le nméro de compte se fait sur 11 chiffres. 
- La saisie du sole initial pour un compte est un double.
- Le numéro de carte est sur 8 chiffres. 

Jeu de données existantes: 
--> idClient=AB012345, nom=Grevedon, prenom=Thomas, dateNaissance=1989-09-21, email=thomas.grevedon@gmail.com, comptesClient=[numCompte=12345678965, codeAgence=123, client=Grevedon Thomas, soldeCompte=500.0, decouvertAutorise=O, numCb=[12345678]
, numCompte=12345678945, codeAgence=123, client=Grevedon Thomas, soldeCompte=800.0, decouvertAutorise=O, numCb=[12345676]
]
-->idClient=AB123456, nom=Grevedon, prenom=Thomas, dateNaissance=1989-09-21, email=thomas.grevedon@gmail.com, comptesClient=[numCompte=12345678910, codeAgence=123, client=Grevedon Thomas, soldeCompte=1000.0, decouvertAutorise=O, numCb=[12345671]
, numCompte=12345678911, codeAgence=123, client=Grevedon Thomas, soldeCompte=1000.0, decouvertAutorise=N, numCb=[12345679]
]