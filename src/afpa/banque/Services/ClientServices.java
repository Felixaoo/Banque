package afpa.banque.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;



import afpa.banque.controles.Controles;
import afpa.banque.entity.Banque;
import afpa.banque.entity.Client;
import afpa.banque.entity.Compte;
/**
 * Classe permettant la création d'un client ainsi que la recherche d'une client. 
 * @author Félix et Thomas 
 *@since 01012019
 */
public class ClientServices {
	
	/**
	 * 
	 * Permet de créer un client via la saisie d"un formulaire. 
	 * La saisie de l'identifiant client se fait avec 2 majuscules + 6 chiffres.
	 * La saisie de la date de naissance doit être une date valide (cependant, ne prend pas en compte les années bissextile).
	 * La saisie d'un email doit être: une ou plusieur lettres et/ou points; un @, un nom de domaine entre 5 et 10 lettres, un point et enfin une extension sur 2 ou 3 lettres. 
	 * @param b: il faut une entité Banque à passer en paramètre de la méthode. 
	 * @return une entité Client. 
	 */
	public Client creerClient(Banque b) {
		Scanner sc= new Scanner(System.in);
		String toPrint = "Veuillez entrer l'id du client composé de 2 majuscules + 6 chiffres comme AA0000";
		System.out.println(toPrint);
		String idClient=sc.nextLine();
		idClient = Controles.stringEntryCheck(idClient, "[A-Z]{2}[0-9]{6}", toPrint);
		for (int i = 0; i < b.getBanqueAgences().size(); i ++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				if (b.getBanqueAgences().get(i).getListeClient().get(j).getIdClient().equals(idClient)) {
					System.out.println("Opéraiton impossible, client déjà existant");
					return new Client();
				}
			}
		}
		
		
		
		
		
		System.out.println("Veuillez entrer le nom du client");
		String nomClient=sc.nextLine();
		System.out.println("Veuillez entrer le prénom du client");
		String prenomClient=sc.nextLine();
		toPrint = "Veuillez entrer la date de naissance du client, selon le modèle JJMMAAAA";
		System.out.println(toPrint);
		String dateNaissance=sc.nextLine();
		dateNaissance = Controles.stringEntryCheck(dateNaissance, "^(3[01]|[12][0-9]|0[1-9])(1[0-2]|0[1-9])[0-9]{4}$", toPrint);
		toPrint = "Veuillez entrer le mail du client";
		System.out.println(toPrint);
		String mailClient=sc.nextLine();
		mailClient = Controles.stringEntryCheck(mailClient, "^[a-zA-Z]{1}"+"(.[\\w]+.)*"+"@"+"[a-zA-Z]{5,10}" + "\\." + "[a-z]{2,3}$", toPrint);
		ArrayList<Compte> comptesClient= new ArrayList<Compte>();
		return new Client(idClient, nomClient, prenomClient, LocalDate.of(Integer.parseInt(dateNaissance.substring(4, 8)), Integer.parseInt(dateNaissance.substring(2, 4)), Integer.parseInt(dateNaissance.substring(0,2))), mailClient, comptesClient);

	}

	/**
	 * Permet de rechercher un client via son nom et d'afficher les informations correspondantes. 
	 * @param b: il faut une entité Banque à passer en paramètre de la méthode. 
	 */
	
	public void rechercheParNom(Banque b) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Veuillez entrer le nom du client que vous cherchez");
		String nomClient=sc.nextLine();

		boolean exist=false;
		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				if (nomClient.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getNom())) {
					System.out.println(b.getBanqueAgences().get(i).getListeClient().get(j));
					exist = true;
					break;
				}
			}
		}
		if(!exist)
			System.out.println("Nous n'avons pas de cient à ce nom dans notre base de données.");
	}

		/**
		  * Permet de rechercher un client via son ID et d'afficher les informations correspondantes. 
		  * La saisie de l'identifiant se fait sur 2 lettres majuscules et 6 chiffres.
		  * @param b: il faut une entité Banque à passer en paramètre de la méthode. 
		 */
	public void rechercheParId(Banque b) {
		Scanner sc=new Scanner(System.in);		
		String toPrint = "Veuillez entrer l'id du client que vous recherchez.";
		System.out.println("Veuillez entrer l'id du client que vous recherchez.");
		String idClient=sc.nextLine();
		idClient = Controles.stringEntryCheck(idClient, "[A-Z]{2}[0-9]{6}", toPrint);

		boolean exist=false;

		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				if (idClient.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getIdClient())) {
					System.out.println(b.getBanqueAgences().get(i).getListeClient().get(j));
					exist = true;
					break;
				}
			}
		}
		if(!exist)
			System.out.println("Nous n'avons pas de client ayant cet identifiant dans notre base de données.");
	}
	
}

