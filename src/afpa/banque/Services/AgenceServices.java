package afpa.banque.Services;

import java.util.ArrayList;
import java.util.Scanner;

import afpa.banque.controles.Controles;
import afpa.banque.entity.Adresse;
import afpa.banque.entity.Agence;
import afpa.banque.entity.Banque;
import afpa.banque.entity.Client;
/**
 * Classe permettant la création d'une agence et de récupérer un code agence lors de l'ajout d'un nouveau client à la banque.
 * @author Félix et Thomas
 *@since 01012019
 */
public class AgenceServices {
	private AdresseServices as;
	/**
	 * Instanciation d'un objet AdresseServices afin de récupérer une adresse pour l'agence. 
	 */
	public AgenceServices() {
		as = new AdresseServices();
	}
	
	/**
	 * Formulaire de saisie pour la crétion d'une agence. 
	 * Le code agence doit s'écrire sur 3 chiffres.
	 * @return
	 */
	public Agence creerAgence() {
		Adresse adresse = as.formulaireAdresse();
		Scanner sc=new Scanner(System.in);
		System.out.println("Veuillez entrer le nom de l'agence");
		String nomAgence=sc.nextLine();
		boolean valid = false;
		String toPrint = "Veuillez entrer le code de l'agence";
		System.out.println(toPrint);
		String codeAgence=sc.nextLine();
		codeAgence = Controles.stringEntryCheck(codeAgence, "[0-9]{3}", toPrint);
		ArrayList<Client> listeClients = new ArrayList<Client>();
		return new Agence(codeAgence, nomAgence, adresse, listeClients) ;
	}
	
	/**
	 * Méthode permettant la récupération d'un code agence pour l'ajout d'un nouveau client à la banque. 
	 * @param b: il faut un objet Banque en paramètre. 
	 * @return le codeAgence. 
	 */
	public String codeAgenceForNewCustomer(Banque b) {
		Scanner sc=new Scanner(System.in);
		String toPrint = "Veuillez entrer le code de l'agence de rattachement du client";
		System.out.println(toPrint);
		String codeAgence=sc.nextLine();
		codeAgence = Controles.stringEntryCheck(codeAgence, "[0-9]{3}", toPrint);
		boolean contains = false;
		for (int i = 0 ; i < b.getBanqueAgences().size(); i++) {
			if (b.getBanqueAgences().get(i).getCodeAgence().equals(codeAgence)) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			System.out.println("Agence inexistante, merci de renouveler l'opération");
			codeAgenceForNewCustomer(b);
		}
		return codeAgence;
	}
}
