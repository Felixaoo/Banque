package afpa.banque.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import afpa.banque.controles.Controles;
import afpa.banque.entity.Banque;
import afpa.banque.entity.Client;
import afpa.banque.entity.Compte;
/**
 * 
 * @author F�lix et Thomas 
 * @since 01012019
 * Classe permettant la cr�ation d'un compte, la recherche et l'affichage de compte, le retrait d'argent. 
 */
public class CompteServices {

	/**
	 * Cr�ation d'un compte via la saisie d'un formulaire. 
	 * Un client ne peut avoir que 3 comptes maximum.
	 * La saisie de l'identifiant client auquel le compte doit �tre rattach� doit �tre de 2 majuscules + 6 chiffres.
	 * La saisie du num�ro de compte doit �tre de 11 chiffres. 
	 * La saisie du num�ro d'agence pour lequel le compte doit �tre rattahc� doit �tre saisie de 3 chiffres.
	 * La saisie du montant � cr�diter au compte � l'initialisation est un double. 
	 * La saisie du num�ro de carte doit �tre constitu� de 8 chiffres. 
	 * @param b: il faut une entit� banque en param�tre de la m�thode.
	 */
	public void creationCompte(Banque b) {
		Scanner sc= new Scanner(System.in).useLocale(Locale.US);

		Client c = new Client();
		String toPrint = "Veuillez entrer l'id du client compos� de 2 majuscules + 6 chiffres comme AA0000";
		System.out.println(toPrint);
		String idClient=sc.nextLine();
		idClient = Controles.stringEntryCheck(idClient, "[A-Z]{2}[0-9]{6}", toPrint);
		boolean clientExiste=false;
		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				if (idClient.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getIdClient()))  {
					clientExiste = true;
					c = b.getBanqueAgences().get(i).getListeClient().get(j);
					if (c.getComptesClient().size() >= 3) {
						System.out.println("Operation impossible, le client poss�de d�j� le maximum de compte limite");
						return;
					}
					break;
				}
			}
		}
		if (! clientExiste) {
			System.out.println(" Client inconnu de notre base de donn�e");
			return;
		}



		toPrint = "Veuillez entrer le numero du compte : il sera compos� de 11 chiffres"; 
		System.out.println(toPrint);
		String numCompte=sc.nextLine();
		numCompte = Controles.stringEntryCheck(numCompte, "[0-9]{11}", toPrint);
		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				for (int k =0; k < b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().size(); k++) {
					if (numCompte.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k).getNumCompte())) {
						System.out.println("Operation impossible, le compte existe d�j�");
						return;
					}
				}
			}
		}


		toPrint = "Veuillez entrer l'ID de l'agence � laquelle est ratach� ce compte";
		System.out.println(toPrint);
		String idAgence=sc.nextLine();
		idAgence = Controles.stringEntryCheck(idAgence, "[0-9]{3}", toPrint);
		boolean agenceExiste=false;
		for (int i = 0 ; i < b.getBanqueAgences().size(); i++) {
			if (b.getBanqueAgences().get(i).getCodeAgence().equals(idAgence)) {
				agenceExiste = true;
				break;
			}
		}
		if (!agenceExiste) {
			System.out.println(" Agence inconnue de notre base de donn�e");
			return;
		}

		toPrint = "Ajouter montant au solde du compte ?";
		System.out.println(toPrint);
		double montant=sc.nextDouble(); sc.nextLine();
		String montantAfterChecking = Controles.stringEntryCheck(Double.toString(montant), "\\d+\\.\\d+", toPrint);
		montant = Double.parseDouble(montantAfterChecking);

		toPrint = "Veuillez d�terminer si le d�couvert est autoris� ou non : O pour oui, N pour non";
		System.out.println(toPrint);
		String decouvertAutorise=sc.nextLine();
		decouvertAutorise = Controles.stringEntryCheck(decouvertAutorise, "[ON]{1}", toPrint );


		ArrayList<String> cartes = new ArrayList<String>();
		toPrint = "Veuillez entrer le num�ro de carte sur 8 chiffres";
		System.out.println(toPrint);
		String numCb=sc.nextLine();
		numCb = Controles.stringEntryCheck(numCb, "[0-9]{8}", toPrint);
		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				for (int k =0; k < b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().size(); k++) {
					for (int l = 0; l < b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k).getNumCb().size(); l++) {
						if (numCb.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k).getNumCb().get(l))) {
							System.out.println("Operation impossible, cette carte existe d�j�");
							return;
						}				
					}
				}
			}
		}
		cartes.add(numCb);
		


		Compte cpte = new Compte(numCompte, idAgence, c, montant, decouvertAutorise, cartes);

		c.getComptesClient().add(cpte);	
	}

	
	/**
	 * Permet de rechercher un compte existant et d'imprimer les informations correspondantes � ce compte. 
	 * La saisie du compte � chercher se fait sur 11 chiffres. 
	 * @param b: il faut une entit� banque en param�tre de la m�thode.
	 */
	public void rechercheCompte(Banque b) {

		Scanner sc=new Scanner(System.in);		
		String toPrint = "Veuillez entrer le numero de compte a rechercher";
		System.out.println(toPrint);
		String numCompte=sc.nextLine();
		numCompte = Controles.stringEntryCheck(numCompte, "[0-9]{11}", toPrint);

		boolean existe=false;
		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				for (int k=0; k < b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().size(); k++) {
					if (numCompte.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k).getNumCompte())) {
						System.out.println(b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k));
						existe = true;
						break;
					}
				}
			}
		}
		if (!existe) {
			System.out.println("Le compte "+numCompte+" n'existe pas dans notre base de donn�es");
		}

	}

	/**
	 * Permet de rechercher un client via un num�ro de compte et d'afficher les informations du client correspondant. 
	 * La saisie du compte � chercher se fait sur 11 chiffres. 
	 * @param b: il faut une entit� banque en param�tre de la m�thode.
	 */
	public void rechercheParCompte(Banque b) {
		Scanner sc=new Scanner(System.in);	
		String toPrint = "Veuillez entrer le numero de compte a rechercher";
		System.out.println(toPrint);
		String numCompte=sc.nextLine();
		numCompte = Controles.stringEntryCheck(numCompte, "[0-9]{11}", toPrint);


		boolean exist=false;

		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				for (int k=0; k < b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().size(); k++) {
					if (numCompte.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k).getNumCompte())) {
						System.out.println(b.getBanqueAgences().get(i).getListeClient().get(j));
						exist = true;
						break;
					}
				}
			}
		}
		if (!exist) {
			System.out.println("Nous n'avons pas de client ayant ce num�ro de compte dans notre base de donn�es.");
		}
	}
	
	/**
	 * Permet d'afficher la liste des comptes d'un client � partir de son identifiant. 
	 * La saisie de l'identifiant client se fait sur 2 lettre majuscule et 6 chifffres. 
	 * @param b: il faut une entit� banque en param�tre de la m�thode.
	 */
	public void afficherListeComptesClient(Banque b) {
		Scanner sc=new Scanner(System.in);	
		String toPrint = "Veuillez saisir l'id du client donc vous souhaitez afficher la liste de comptes.";
		System.out.println(toPrint);
		String idClient=sc.nextLine();
		idClient = Controles.stringEntryCheck(idClient, "[A-Z]{2}[0-9]{6}", toPrint);
		
		boolean exist=false;
		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				if (idClient.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getIdClient())) {
					exist = true;
					for (int k = 0; k < b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().size(); k++) {
						System.out.println(b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k));
					}
				}
				break;
			}
		}
				
		if(!exist)
			System.out.println("Ce num�ro de client n'existe pas");
	}
	
	/**
	 * Permet de faire un retrai d'argent sur le compte d'un client via une des cartes bancaires rattach�s � ce compte. 
	 * Il faut saisir le num�ro de carte sur 8 chiffres. 
	 * D�s que le compte est trouv� il faut sasir le montant qui est un double. 
	 * L'autorisation de d�couvert est ensuite v�rifi�e en cas de retrait sup�rieur au fond disponible par le client. 
	 * A la fin, un appel d'une m�thode de la Banque permet d'enregistrer la transaction
	 * @param b: il faut une entit� banque en param�tre de la m�thode.
	 * @throws IOException 
	 */
	public void retirerArgent(Banque b) throws IOException {
		Scanner sc = new Scanner(System.in);
		String toPrint = "Veuillez entrer le num�ro de carte sur 8 chiffres utilis�e pour le retrait";
		System.out.println(toPrint);
		String numCb=sc.nextLine();
		numCb = Controles.stringEntryCheck(numCb, "[0-9]{8}", toPrint);
		boolean found = false; 
		Compte c = new Compte();
		for (int i = 0; i < b.getBanqueAgences().size(); i++) {
			for (int j = 0; j < b.getBanqueAgences().get(i).getListeClient().size(); j++) {
				for (int k =0; k < b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().size(); k++) {
					for (int l = 0; l < b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k).getNumCb().size(); l++) {
						if (numCb.equals(b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k).getNumCb().get(l))) {
							c = b.getBanqueAgences().get(i).getListeClient().get(j).getComptesClient().get(k);
							found = true;
							break;
						}				
					}
				}
			}
		}
		
		if (! found) {
			System.out.println("Operation impossible, cette carte est inconnue");
			return;
		}
		
		toPrint = "Merci de saisir le montant retir�";
		System.out.println(toPrint);
		double montant=sc.nextDouble(); sc.nextLine();
		String montantAfterChecking = Controles.stringEntryCheck(Double.toString(montant), "\\d+\\.\\d+", toPrint);
		montant = Double.parseDouble(montantAfterChecking);
		
		if ("N".equals(c.getDecouvertAutorise())) {
			if (c.getSoldeCompte() - montant < 0) {
				System.out.println("Operation impossible, D�ocuvert non autoris�");
				return;
			}
			else {
				BanqueServices.fichierTransaction(montant, numCb);
			}
		}
		else {
			BanqueServices.fichierTransaction(montant, numCb);
		}
		
		

	}
	
	
	
}

