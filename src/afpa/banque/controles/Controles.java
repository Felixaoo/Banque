package afpa.banque.controles;

import java.util.Scanner;
/**
 * Classe permettanr le contr�le de saisie de l'utilisateur. 
 * @author F�lix et Thomas 
 *@since 01012019
 */
public class Controles {
	/**
	 * M�thode r�cursive permettant de boucler sur la saisie tant que la donn�e saisie par l'utilisateur n'est pas conforme au Regex pass� en param�tre. 
	 * @param toCheck: Il s'agit de la sasie utilisateur
	 * @param patternToUse: Il s'agit du Regex
	 * @param toPrint: Il s'agit de la phrae � imprimer en cas de nouvelle demande de saisie � l'utilisateur.
	 * @return une cha�ne de caract�re contenant la sasie de l'utilisateur valid�e. 
	 */
	public static String stringEntryCheck(String toCheck, String patternToUse, String toPrint) {
		if (toCheck.matches(patternToUse)) {
			return toCheck;
		}
		else {
			System.out.println("Saisie erron�e");
			Scanner sc = new Scanner(System.in);
			System.out.println(toPrint);
			toCheck = sc.nextLine();
			return stringEntryCheck(toCheck, patternToUse, toPrint);
		}
		
	}

}
