package afpa.banque.Services;

import java.util.Scanner;

import afpa.banque.controles.Controles;
import afpa.banque.entity.Adresse;
import afpa.banque.entity.Pays;
/**
 * 
 * @author F�lix et Thomas
 * Classe utilisant la saisie d'un formulaire pour cr�er une entit� adresse. 
 */
public class AdresseServices {
	private PaysServices p;

	/*
	 * Instantation du service Pays n�cessaire � la cr�ation de l'adresse
	 */
	public AdresseServices() {
		p = new PaysServices();
	}

	/**
	 * Saisie formulaire pour la cr�ation d'une entit� adressse. 
	 * Le code postal doit �tre saisie sur 5 chiffres. 
	 * @return une nouvelle adresse
	 */
	public Adresse formulaireAdresse() {
		Pays pays = p.formulairePays();
		Scanner sc = new Scanner(System.in);
		System.out.println("Merci d'indiquer le num�ro de rue:");
		String numRue = sc.nextLine();
		System.out.println("Merci d'indiquer le nom de rue:");
		String nomRue = sc.nextLine();
		System.out.println("Merci d'indiquer le nom de ville:");
		String nomVille = sc.nextLine();
		String toPrint = "Merci d'indiquer le code postale:";
		System.out.println(toPrint);
		String codePostal = sc.nextLine();
		codePostal = Controles.stringEntryCheck(codePostal, "^[0-9]{5}$", toPrint);
		return new Adresse(numRue, nomRue, nomVille, codePostal, pays);
	}


}

