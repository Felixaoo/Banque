package afpa.banque.entity;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Classe entité Compte. Chaque compte peut avoir plusieurs cartes bancaires. 
 * @author Félix et Thomas
 * @since 01012019
 */
public class Compte implements Serializable{
	
	private String numCompte;
	private String codeAgence;
	private Client client;
	private double soldeCompte;
	private String decouvertAutorise;
	private ArrayList<String> numCb;
	
	public Compte() {
		super();
		this.numCompte = "";
		this.codeAgence = "";
		this.client = new Client();
		this.soldeCompte = 0;
		this.decouvertAutorise = "";
		this.numCb = new ArrayList<String>();
	}

	public Compte(String numCompte, String codeAgence, Client client, double soldeCompte, String decouvertAutorise,
			ArrayList<String> numCb) {
		super();
		this.numCompte = numCompte;
		this.codeAgence = codeAgence;
		this.client = client;
		this.soldeCompte = soldeCompte;
		this.decouvertAutorise = decouvertAutorise;
		this.numCb = numCb;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public String getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence(String codeAgence) {
		this.codeAgence = codeAgence;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getSoldeCompte() {
		return soldeCompte;
	}

	public void setSoldeCompte(double soldeCompte) {
		this.soldeCompte = soldeCompte;
	}

	public String getDecouvertAutorise() {
		return decouvertAutorise;
	}

	public void setDecouvertAutorise(String decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}

	public ArrayList<String> getNumCb() {
		return numCb;
	}

	public void setNumCb(ArrayList<String> numCb) {
		this.numCb = numCb;
	}

	@Override
	public String toString() {
		return "numCompte=" + numCompte + ", codeAgence=" + codeAgence + ", client=" + client.getNom() + " " + client.getPrenom() + ", soldeCompte="
				+ soldeCompte + ", decouvertAutorise=" + decouvertAutorise + ", numCb=" + numCb.toString() + "\r\n";
	}

	
	
}
