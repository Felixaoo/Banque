package afpa.banque.Services;

import java.util.Scanner;

import afpa.banque.controles.Controles;
import afpa.banque.entity.Pays;

/**
 * 
 * @author Félix et Thomas 
 * Classe métier permettant la création d'une entité pays via la saisie d'un formulaire.
 */
public class PaysServices {
	
	/**
	 * Permet la création d'une entité via la saisie d'un formulaire 
	 * Le code pays doit être saisie sur 2 lettres
	 * @return une entité Pays
	 */
	public Pays formulairePays() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Merci d'indiquer le pays:");
		String nomPays = sc.nextLine();
		String toPrint = "Merci d'indiquer le code pays sur deux lettres:";
		System.out.println(toPrint);
		String codePays = sc.nextLine();
		codePays = Controles.stringEntryCheck(codePays, "^[a-zA-Z]{2}$", toPrint);
		return new Pays(nomPays, codePays);
	}


}

