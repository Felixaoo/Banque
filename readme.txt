Cr�ation des chemins n�cessaires pour l'utilisation de l'application banque: 
--> Cr�ation de fiche clients: C:\filRouge\Banque\fiche
--> Cr�ation de fichiers transactions: C:\filRouge\Banque\transactions
--> Cr�ation du QR Code: C:\Test
--> Eventuelles futures transactions: C:\filRouge\Central

Librairies n�cessaires: 
Les librairies n�cessaires au fonctionnement de l'application se situent dans le dossier librairies du ZIP.

Avant de lancer l'application pour la premi�re fois: 
Placer le fichier Banque.txt (inclus dans le dossier zip) dans le dossier C:\filRouge\Banque

R�gles de gestion pour la saisie: 
- Le code pays se fait sur 2 lettres;
- Le code postal se fait sur 5 chiffres. 
- Le code d'agence se fait sur 3 chiffres.
- ID client: 2 lettres Majuscules et 6 chiffres comme AA0000;
- Datedenaissance du client: doit �tre une date valide (cependant, ne prends pas en compte les ann�es bissextile);
- Mail du client: une ou plusieur lettres et/ou points; un @, un nom de domaine entre 5 et 10 lettres, un point et enfin une extension sur 2 ou 3 lettres. 
- Le nm�ro de compte se fait sur 11 chiffres. 
- La saisie du sole initial pour un compte est un double.
- Le num�ro de carte est sur 8 chiffres. 

Jeu de donn�es existantes: 
--> idClient=AB012345, nom=Grevedon, prenom=Thomas, dateNaissance=1989-09-21, email=thomas.grevedon@gmail.com, comptesClient=[numCompte=12345678965, codeAgence=123, client=Grevedon Thomas, soldeCompte=500.0, decouvertAutorise=O, numCb=[12345678]
, numCompte=12345678945, codeAgence=123, client=Grevedon Thomas, soldeCompte=800.0, decouvertAutorise=O, numCb=[12345676]
]
-->idClient=AB123456, nom=Grevedon, prenom=Thomas, dateNaissance=1989-09-21, email=thomas.grevedon@gmail.com, comptesClient=[numCompte=12345678910, codeAgence=123, client=Grevedon Thomas, soldeCompte=1000.0, decouvertAutorise=O, numCb=[12345671]
, numCompte=12345678911, codeAgence=123, client=Grevedon Thomas, soldeCompte=1000.0, decouvertAutorise=N, numCb=[12345679]
]