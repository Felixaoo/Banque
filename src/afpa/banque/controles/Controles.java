package afpa.banque.controles;

import java.util.Scanner;
/**
 * Classe permettanr le contrôle de saisie de l'utilisateur. 
 * @author Félix et Thomas 
 *@since 01012019
 */
public class Controles {
	/**
	 * Méthode récursive permettant de boucler sur la saisie tant que la donnée saisie par l'utilisateur n'est pas conforme au Regex passé en paramètre. 
	 * @param toCheck: Il s'agit de la sasie utilisateur
	 * @param patternToUse: Il s'agit du Regex
	 * @param toPrint: Il s'agit de la phrae à imprimer en cas de nouvelle demande de saisie à l'utilisateur.
	 * @return une chaîne de caractère contenant la sasie de l'utilisateur validée. 
	 */
	public static String stringEntryCheck(String toCheck, String patternToUse, String toPrint) {
		if (toCheck.matches(patternToUse)) {
			return toCheck;
		}
		else {
			System.out.println("Saisie erronée");
			Scanner sc = new Scanner(System.in);
			System.out.println(toPrint);
			toCheck = sc.nextLine();
			return stringEntryCheck(toCheck, patternToUse, toPrint);
		}
		
	}

}
