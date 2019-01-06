package afpa.banque.Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import afpa.banque.controles.Controles;
import afpa.banque.entity.Agence;
import afpa.banque.entity.Banque;
import afpa.banque.entity.Client;
import afpa.banque.entity.Compte;
import afpa.util.entity.CreationPDF;
import afpa.util.entity.SendMail;

/**
 * Classe permettant de génerer les Service gérées par l'application Banque:
 *   - Création d'une agence
 *   - Création d'un client
 *   - Création d'un compte 
 *   - Recherche de comptes 
 *   - Recherche de client 
 *   - Affichage de la liste des comptes 
 *   - Retrait d'argent 
 * @author Félix et Thomas
 * @since 01012019
 * A l'ouverture de l'application, les fichiers clients et comptes sont chargées pour les inclures dans les tableaux listClients et listCompte.
 */


public class BanqueServices {
	private Banque b = new Banque();
	private AgenceServices aser;
	private ClientServices cser;
	private CompteServices cpteser;
	final private String FICHIERBANQUE = "C:\\filRouge\\Banque\\Banque.txt";
	final private String FICHIERCENTRAL = "C:\\filRouge\\Central\\";
	final private static String FICHIERTRANSACBANQUE = "C:\\filRouge\\Banque\\transactions\\";


	/**
	 * Initialisation des objets services nécessaires pour l'application.
	 * Chargment de l'objet banque depuis un fichier afin de charger les informations précedemment enregistrées relatives à la banque. 
	 */
	public void initialisation() {
		aser = new AgenceServices();
		cser = new ClientServices();
		cpteser = new CompteServices();
		try {
			FileInputStream f = new FileInputStream(FICHIERBANQUE);
			ObjectInputStream ois = new ObjectInputStream(f);
			b = (Banque) ois.readObject();
			ois.close();
		}
		catch (Exception e) {
			System.out.println("Erreure" + e);
		} 
	}


	/**
	 * Méthode qui est appelée lorsque l'on quitte l'application. 
	 * On enregistre l'objet Banque dans un fichier afin de pouvoir réutiliser les informations de la Banque à l'ouverture suivante de l'application.
	 */
	public void sauvegardeBanque() {
		try {
			FileOutputStream f = new FileOutputStream(FICHIERBANQUE);
			ObjectOutputStream oos = new ObjectOutputStream(f);
			oos.writeObject(b);
			oos.close();
		}catch (Exception e) {
			System.out.println("Erreure" + e);
		}
	}

	/**
	 * Menu qui appelle chaque méthode correspondante au chiffre sélectionné par l'utilisateur. 
	 * A l'ouverture du menu, l'objet Banque est chargé.
	 * Lorsqu'on quitte l'application, l'objet Banque est chargé.
	 * @throws Throwable
	 */
	public void menu() throws Throwable {
		initialisation();
		Scanner sc=new Scanner(System.in);
		boolean onQuitte=false;
		do {
			System.out.println("            ||====================================================================||\r\n" + 
					"            ||//$\\\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\//$\\\\||\r\n" + 
					"            ||(100)==================| FEDERAL RESERVE NOTE |================(100)||\r\n" + 
					"            ||\\\\$//        ~         '------========--------'                \\\\$//||\r\n" + 
					"            ||<< /        /$\\              // ____ \\\\                         \\ >>||\r\n" + 
					"            ||>>|  12    //L\\\\            // ///..) \\\\         L38036133B   12 |<<||\r\n" + 
					"            ||<<|        \\\\ //           || <||  >\\  ||                        |>>||\r\n" + 
					"            ||>>|         \\$/            ||  $$ --/  ||        One Hundred     |<<||\r\n" + 
					"            ||<<|      L38036133B        *\\\\  |\\_/  //* series                 |>>||\r\n" + 
					"            ||>>|  12                     *\\\\/___\\_//*   1989                  |<<||\r\n" + 
					"            ||<<\\      Treasurer     ______/Franklin\\________     Secretary 12 />>||\r\n" + 
					"            ||//$\\                 ~|UNITED STATES OF AMERICA|~               /$\\\\||\r\n" + 
					"            ||(100)===================  ONE HUNDRED DOLLARS =================(100)||\r\n" + 
					"            ||\\\\$//\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\\\$//||\r\n" + 
					"            ||====================================================================||\r\n" + 
					"            ||                                                                    ||                  \r\n" + 
					"            ||                           Menu Principal                           ||  \r\n" + 
					"            ||                                                                    ||\r\n" + 
					"            ||                        1- Créer une agence                         ||\r\n" + 
					"            ||                                                                    ||      \r\n" + 
					"            ||                        2- Créer un client                          ||  \r\n" + 
					"            ||                                                                    ||\r\n" + 
					"            ||                      3- Créer un compte bancaire                   ||  \r\n" + 
					"            ||                                                                    ||\r\n" + 
					"            ||                 4- Recherche de compte (numéro de compte)          ||\r\n" + 
					"            ||                                                                    ||    \r\n" + 
					"            ||            5- Recherche de client(nom,numero de compte, idClient)  ||\r\n" + 
					"            ||                                                                    ||  \r\n" + 
					"            ||              6- Afficher la liste de compte du client (idClient)   ||\r\n" + 
					"            ||                                                                    ||      \r\n" + 
					"            ||                  7- Imprimer les infos client (idClient)           ||  \r\n" + 
					"            ||                                                                    ||  \r\n" +
					"            ||                       8- Retirer argent                            ||  \r\n" + 
					"            ||                                                                    ||  \r\n" + 
					"            ||                          9- Quitter le programme                   ||  \r\n" + 
					"            ||                                                                    ||  \r\n" + 
					"            ||====================================================================||");
			String input=sc.nextLine();
			switch(input) {
			case "1":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				creerAgence();
				break;
			case "2":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				creerClient();
				break;
			case "3":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				creerCompte();
				break;
			case "4":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				rechercheCompte();
				break;
			case "5":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				rechercheClient();
				break;
			case "6":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				afficherListeComptesClient();
				break;
			case "7":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				imprimerInfosClient();
				break;
			case "8":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				retirerArgent();
				break;
			case "9":
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				onQuitte=true;
				sauvegardeBanque();
				break;				
			default:
				System.out.println("commande non trouvée");
				break;
			}
		}while(onQuitte==false);
	}

	/**
	 * Création d'une agence.
	 * Appel de la classe et du service correspondant.
	 * Une fois que l'agence est créée, elle est éjoutée à la liste des agences de la banque. 
	 */
	public void creerAgence() {
		Agence a = aser.creerAgence();
		b.getBanqueAgences().add(a);
	}

	
	/**
	 * Création d'un nouveau client dans la banque
	 * Appel de la classe et du service correspondant.
	 * Si le client a bien été créé, on appele la classe et le service correspondant pour obtenir le code de l'agence à laquelle le client sera rattaché, puis on ajoute le client à la liste de client de cette agebce. 
	 * 
	 */
	public void creerClient() {
		Client c = cser.creerClient(b);
		if (! "".equals(c.getIdClient())) {
			String codeAgence = aser.codeAgenceForNewCustomer(b);
			for (int i =0; i < b.getBanqueAgences().size(); i++ ) {
				if (b.getBanqueAgences().get(i).getCodeAgence().equals(codeAgence)) {
					b.getBanqueAgences().get(i).getListeClient().add(c);
				}
			}
		}
		
	}


	/**
	 * Création d'un compte
	 * Appel de la classe et du service correspondant.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void creerCompte() throws InterruptedException, IOException {
		cpteser.creationCompte(b);
	}

	/**
	 * Recherce de comptes.
	 * Appel de la classe et du service correspondant.
	 */
	public void rechercheCompte() {
		cpteser.rechercheCompte(b);
	}

	/**
	 * Sous menu qui est proposé à l'utilisateur pour affiner la recher de client. 
	 * @throws Throwable
	 * @throws IOException
	 */
	public void rechercheClient() throws Throwable, IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("            ||====================================================================||\r\n" + 
				"            ||//$\\\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\//$\\\\||\r\n" + 
				"            ||(100)==================| FEDERAL RESERVE NOTE |================(100)||\r\n" + 
				"            ||\\\\$//        ~         '------========--------'                \\\\$//||\r\n" + 
				"            ||<< /        /$\\              // ____ \\\\                         \\ >>||\r\n" + 
				"            ||>>|  12    //L\\\\            // ///..) \\\\         L38036133B   12 |<<||\r\n" + 
				"            ||<<|        \\\\ //           || <||  >\\  ||                        |>>||\r\n" + 
				"            ||>>|         \\$/            ||  $$ --/  ||        One Hundred     |<<||\r\n" + 
				"            ||<<|      L38036133B        *\\\\  |\\_/  //* series                 |>>||\r\n" + 
				"            ||>>|  12                     *\\\\/___\\_//*   1989                  |<<||\r\n" + 
				"            ||<<\\      Treasurer     ______/Franklin\\________     Secretary 12 />>||\r\n" + 
				"            ||//$\\                 ~|UNITED STATES OF AMERICA|~               /$\\\\||\r\n" + 
				"            ||(100)===================  ONE HUNDRED DOLLARS =================(100)||\r\n" + 
				"            ||\\\\$//\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\\\$//||\r\n" + 
				"            ||====================================================================||\r\n" + 
				"            ||                                                                    ||                  \r\n" + 
				"            ||                   Veuillez determiner le critère de recherche      ||  \r\n" + 
				"            ||                                                                    ||\r\n" + 
				"            ||                        1- Nom                                      ||\r\n" + 
				"            ||                                                                    ||      \r\n" + 
				"            ||                        2- Numéro de compte                         ||  \r\n" + 
				"            ||                                                                    ||\r\n" + 
				"            ||                        3- Identifiant client                       ||  \r\n" + 
				"            ||                                                                    ||\r\n" + 
				"            ||====================================================================||");
		String choice=sc.nextLine();
		switch (choice) {
		case "1":
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			rechercheParNom();
			break;
		case "2":
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			rechercheParCompte();
			break;
		case "3":
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			rechercheParId();
			break;
		default:
			System.out.println("ce choix n'existe pas");
			break;

		}
	}

	/**
	 * Recherche d'un client par son nom.
	 * Appel de la classe et du service correspondant.
	 */
	public void rechercheParNom() {
		cser.rechercheParNom(b);
	}

	/**
	 * Recherche d'un client par le numéro de compte.
	 * Appel de la classe et du service correspondant.
	 */
	public void rechercheParCompte() {
		cpteser.rechercheParCompte(b);
	}

	/**
	 * Recherche d'un client par son identifiant.  
	 * Appel de la classe et du service correspondant.
	 */
	public void rechercheParId() {
		cser.rechercheParId(b);
	}

	/**
	 * Affichage de la liste des comptes d'une client. 
	 * Appel de la classe et du service correspondant.
	 */
	public void afficherListeComptesClient() {
		cpteser.afficherListeComptesClient(b);
	}
	
	/**
	 * Permet d'effectuer un retrait d'argent sur le compte lié à une carte bancaire.
	 * Appel de la classe et du service correspondant (une instance Banque doit être passée en paramètre.).
	 * @throws IOException 
	 */
	public void retirerArgent() throws IOException {
		cpteser.retirerArgent(b);
	}
	
	/**
	 * Permet d'enregistrer une transaction dans le dossier banque. 
	 * @param totalTTC 
	 * @param numCb: numéro de carte du client qui sera affecté par cette transaction.
	 * @throws IOException
	 */
	public static void fichierTransaction(double totalTTC, String numCb) throws IOException {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"); // reformatage de la date
		String dateDef = date.format(formatters);
		LocalDate parsedDate = LocalDate.parse(dateDef, formatters);
		FileWriter fw=new FileWriter(FICHIERTRANSACBANQUE+"transaction"+"_"+numCb+"_"+dateDef+".txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write("D; Retrait; "+String.format(Locale.ROOT, "%.2f", totalTTC)+"; "+dateDef); 
		bw.newLine();
		bw.close();
	}
	
	/**
	 * Permet de créer un PDF avec le relevé de compte du client (lui indiquant son solde actuel pour chaque compte, le solde et les opérations à venir et les opérations comptabilisées).
	 * Le PDF contient également un QRCode avec les informations de bases du relevés de comptes. 
	 * Permet d'envoyer ce PDF avec les informations par email à l'adresse email qui a été indiqué lors de la création du compte client. 
	 * Afin de choisir un client pour lequel les informaitons doivent être imprimées, il faut saisr son identifiant composé de 2 lettres majuscules et 6 chiffres. 
	 * @throws IOException
	 * @throws DocumentException
	 * @throws WriterException
	 * @throws URISyntaxException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void imprimerInfosClient() throws IOException, DocumentException, WriterException, URISyntaxException, AddressException, MessagingException {
		Scanner sc=new Scanner(System.in);
		String toPrint = "Veuillez entrer l'ID du client donc vous souhaitez afficher les informations";
		System.out.println(toPrint);
		String idClient=sc.nextLine();
		idClient = Controles.stringEntryCheck(idClient, "[A-Z]{2}[0-9]{6}", toPrint);

		boolean exist=false;
		Client c = new Client();
		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				if (idClient.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getIdClient())) {
					c = b.getBanqueAgences().get(i).getListeClient().get(j);
					exist = true;
					break;
				}
			}
		}
		if(!exist) {
			System.out.println("Nous n'avons pas de client ayant cet identifiant dans notre base de données.");
			return;
		}

		exist = true;
		LocalDate date = c.getDateNaissance();
		String concatForQRCode = "ID du client : "+c.getIdClient()                                + "\r\n" + 
				"Nom du client : "+c.getNom()+"                                 " + "\r\n" + 
				"Prénom du client : "+c.getPrenom()+"                                 "+ "\r\n" + 
				"Date de naissance : "+date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\r\n\n" +                             
				"Liste de comptes et soldes: " + "\r\n\n";
				

		String concatForPDF = "                     Fiche Client                              " + "\r\n\n" + 
				"ID du client : "+c.getIdClient()                                + "\r\n" + 
				"Nom du client : "+c.getNom()+"                                 " + "\r\n" + 
				"Prénom du client : "+c.getPrenom()+"                                 "+ "\r\n" + 
				"Date de naissance : "+date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\r\n\n" +                             
				"_______________________________________________________________" + "\r\n" + 
				"Liste de comptes                                               " + "\r\n" + 
				"_______________________________________________________________"+ "\r\n" + 
				"Numero de compte :             Solde :                     "+ "\r\n" + 
				"_______________________________________________________________"+ "\r\n";

		double soldeCompte = 0;
		String smiley = "";
		
		// une fois que les opérations comptabilisées auront été déduites ou ajoutées au solde, on enregistre temporairement le montant du solde afin de calculer le future solde avec les opérations à venir.
		ArrayList<Double> tempSoldeCompte = new ArrayList<Double>(); 

		for (int i = 0; i < c.getComptesClient().size(); i++) {
			if (c.getComptesClient().get(i).getSoldeCompte() >= 0) {
				smiley =":-)";
			}
			else {
				smiley=":-(";
			}
			for (int j = 0; j < c.getComptesClient().get(i).getNumCb().size(); j++) {
				soldeCompte = updateSolde(c, c.getComptesClient().get(i).getNumCb().get(j)); //met à jour le solde avec les transactions enregistrées dans le banque. 
			}
			tempSoldeCompte.add(soldeCompte);
			String soldeCompteToPrint = String.format(Locale.ROOT, "%.2f", soldeCompte) ;
			int taille = 25 - soldeCompteToPrint.length();
			String spaceString = "";
			for (int j = 0; j < taille; j++) {
				spaceString += " ";
			}
			concatForPDF += c.getComptesClient().get(i).getNumCompte()+ spaceString +soldeCompteToPrint+"                      "+smiley + "\r\n";
			concatForQRCode += c.getComptesClient().get(i).getNumCompte()+ "\r\n" + soldeCompteToPrint+" Euros    "+smiley + "\r\n";
		}
		concatForPDF +=  "\r\n" + 
				"A venir:" + "\r\n";

		for (int i = 0; i < c.getComptesClient().size(); i++) {
			String[] incomingTransactions = {"", ""}; // le premier String permet de concatener les opérations à venir pour le compte et le deuxièmre permet de récupérer le solde à venir. 
			soldeCompte = tempSoldeCompte.get(i);
			incomingTransactions = addIncomingTransactions(c.getComptesClient().get(i), soldeCompte); 
			concatForPDF += "Future balance: " + "\r\n" + "Compte: " + 
					c.getComptesClient().get(i).getNumCompte() + "                             " + incomingTransactions[1] + " Euros" + "\r\n" + incomingTransactions[0] + "\r\n"; 
		}
		concatForPDF +=  "Comptabilisées:" + "\r\n";
		String transactionAlreadyDone = addtransactionsToAccount(c);
		concatForPDF += transactionAlreadyDone; 

		String OutputFileName = "C:\\filRouge\\Banque\\fiche\\" +"fiche_" + c.getIdClient()+"_.pdf";
		
		//Appel du service de création de PDF dans la librairie util.
		CreationPDF.creationPdf(concatForPDF, OutputFileName, concatForQRCode);
		
		String dateDuJour = String.valueOf(LocalDate.now());
		String messageText = "Bonjour, vous trouverez en pièce jointe votre relevé de comptes en date du " + dateDuJour ;	
		//Appel du service d'envoi de mail dans la librairie util.
		SendMail.envoieMail(c.getEmail(), "Relevé Comptes", "Relevé.pdf", OutputFileName, messageText);
		System.out.println(concatForPDF); 

	}

	/**
	 * Permet de mettre à jour le solde d'un compte en parcourant toutes les transactions du dossier banque liées au numéro de carte passée en paramètre. 
	 * @param c
	 * @param numCb
	 * @return le solde mis à jour. 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public double updateSolde(Client c, String numCb) throws NumberFormatException, IOException {
		double updatedSolde = 0;
		for (int i =0; i < c.getComptesClient().size(); i++) {
			for (int j = 0; j < c.getComptesClient().get(i).getNumCb().size(); j++) {
				if (c.getComptesClient().get(i).getNumCb().get(j).equals(numCb)) {
					updatedSolde = c.getComptesClient().get(i).getSoldeCompte();
					break;
				}
			}
		}

		File folder = new File(FICHIERTRANSACBANQUE);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String s =listOfFiles[i].getName();
				if (numCb.equals(s.substring(12, 20))) {
					FileReader fr = new FileReader(FICHIERTRANSACBANQUE+s);
					BufferedReader br = new BufferedReader(fr);
					while (br.ready()) {
						String[] tab = br.readLine().split(";");
						double amount = Double.parseDouble(tab[2]);
						String letter = tab[0];
						if ("D".equals(letter)) {
							updatedSolde -= amount;
						}
						else if ("C".equals(letter)) {
							updatedSolde -= amount; // - et - font +
						}
						else {
							System.out.println("Erreur lors de la lecture de la transaction");
						}
					}
					br.close();
				}
			}

		}
		return updatedSolde;
	}


	/**
	 *Ajout des opérations sur le relevé de compte du client en concatenant toute les opérations liées à toutes les cartes de tous les comptes dans une String qui sera ajouté au PDF.
	 *La méthode effectue un parcours des fichiers des transactions dans le dossier Banque et récupère les opérations liées aux cartes des comptes du client. 
	 * @param designatedClient
	 * @return une String avec les opérations effectuées par les cartes des comptes du client. 
	 * @throws IOException
	 */
	public String addtransactionsToAccount (Client designatedClient) throws IOException {
		String concat = "";
		for (int i= 0; i < designatedClient.getComptesClient().size(); i++ ) {
			for (int j=0; j < designatedClient.getComptesClient().get(i).getNumCb().size(); j++) {
				File folder = new File(FICHIERTRANSACBANQUE);
				File[] listOfFiles = folder.listFiles();
				for (int k = 0; k < listOfFiles.length; k++) {
					if (listOfFiles[k].isFile()) {
						String s =listOfFiles[k].getName();
						if (designatedClient.getComptesClient().get(i).getNumCb().get(j).equals(s.substring(12, 20))) {
							FileReader fr = new FileReader(FICHIERTRANSACBANQUE+s);
							BufferedReader br = new BufferedReader(fr);
							while (br.ready()) {
								String[] tab = br.readLine().split(";");
								String spaceField = "";
								int taille = 29- tab[1].length();
								for (int  l =0; l < taille;l++) {
									spaceField += " ";
								}
								if ("D".equals(tab[0])) {
									concat += tab[3].substring(1, 3) + "/" + tab[3].substring(3, 5) + "/" + tab[3].substring(5, 9) + "                    " + tab[1] + spaceField + "-" + Double.parseDouble(tab[2]) + "\r\n";
								}
								else {
									concat += tab[3].substring(1, 3) + "/" + tab[3].substring(3, 5) + "/" + tab[3].substring(5, 9) + "                    " + tab[1] + spaceField + "+" + tab[2].substring(2) + "\r\n";
								}
							}

						}				
					}
				}
			}
		}
		return concat;
	}

	/**
	 * Ajout des opérations futures pour toutes les cartes par compte des clients. 
	 * La méthode accède au dossier central et récupère les informations des opérations liées aux cartes des clients. 
	 * La méthode ajoute les opérations à venir dans la première case d'un tableau de String.
	 * Le solde à venir, calculé en fonction des opérations à vneir est ajouté dans une seconde String dans la deuxième case du tableau de String. 
	 * @param compte
	 * @param soldeCompte (Solde après que les opérations comptabilisées ait été prises en comptes).
	 * @return une String avec les opérations à evnir par les cartes d'un comptes du client. 
	 * @throws IOException
	 */
	public String[] addIncomingTransactions(Compte compte, double soldeCompte) throws IOException {
		String[] concat = {"",""};
		for (int i = 0 ; i < compte.getNumCb().size(); i++) {
			File folder = new File(FICHIERCENTRAL);
			File[] listOfFiles = folder.listFiles();
			for (int j = 0; j < listOfFiles.length; j++) {
				if (listOfFiles[j].isFile()) {
					String s =listOfFiles[j].getName();
					if (compte.getNumCb().get(i).equals(s.substring(12, 20))) {
						FileReader fr = new FileReader(FICHIERCENTRAL+s);
						BufferedReader br = new BufferedReader(fr);
						while (br.ready()) {
							String[] tab = br.readLine().split(";");
							double amount = Double.parseDouble(tab[2]);
							double amountCompte = soldeCompte;
							double futureBalance = amountCompte - amount;
							soldeCompte = soldeCompte - amount;
							concat[1] = String.format(Locale.ROOT, "%.2f", futureBalance);
							String spaceField = "";
							int taille = 29- tab[1].length();
							for (int  k =0; k < taille; k++) {
								spaceField += " ";
							}
							if ("D".equals(tab[0])) {
								concat[0] += tab[3].substring(1, 3) + "/" + tab[3].substring(3, 5) + "/" + tab[3].substring(5, 9) + "                    " + tab[1] + spaceField + "-" + Double.parseDouble(tab[2]) + "\r\n";
							}
							else {
								concat[0] += tab[3].substring(1, 3) + "/" + tab[3].substring(3, 5) + "/" + tab[3].substring(5, 9) +  "                    " + tab[1] + spaceField + "+" + tab[2].substring(2) + "\r\n";
							}
						}

					}				
				}
			}
		}
		if ("".equals(concat[1])) {
			concat[1] = String.format(Locale.ROOT, "%.2f", soldeCompte);
		}
		return concat;
	}
}