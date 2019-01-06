package afpa.banque.Main;

import afpa.banque.Services.BanqueServices;

/**
 * Classe Main permettant le lancement de l'application Banque avec les services: 
 *   - Création d'une agence
 *   - Création d'un client
 *   - Création d'un compte 
 *   - Recherche de comptes 
 *   - Recherche de client 
 *   - Affichage de la liste des comptes 
 *   - Retrait d'argent 
 * @author Thomas et Félix.
 * @since 01012019
 */
public class Main {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		BanqueServices bs=new BanqueServices();
		bs.menu();	
	}



}


