package afpa.banque.Services;

import java.util.Scanner;

import afpa.banque.controles.Controles;
import afpa.banque.entity.Pays;

/**
 * 
 * @author F�lix et Thomas 
 * Classe m�tier permettant la cr�ation d'une entit� pays via la saisie d'un formulaire.
 */
public class PaysServices {
	
	/**
	 * Permet la cr�ation d'une entit� via la saisie d'un formulaire 
	 * Le code pays doit �tre saisie sur 2 lettres
	 * @return une entit� Pays
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

