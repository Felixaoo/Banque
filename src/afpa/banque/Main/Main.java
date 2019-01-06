package afpa.banque.Main;

import afpa.banque.Services.BanqueServices;

/**
 * Classe Main permettant le lancement de l'application Banque avec les services: 
 *   - Cr�ation d'une agence
 *   - Cr�ation d'un client
 *   - Cr�ation d'un compte 
 *   - Recherche de comptes 
 *   - Recherche de client 
 *   - Affichage de la liste des comptes 
 *   - Retrait d'argent 
 * @author Thomas et F�lix.
 * @since 01012019
 */
public class Main {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		BanqueServices bs=new BanqueServices();
		bs.menu();	
	}



}


