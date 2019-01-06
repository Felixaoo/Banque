package afpa.banque.entity;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Classe entité Agence. Chaque Agence contient une liste de client. 
 * @author Félix et Thomas
 * @since 0101209
 */
public class Agence implements Serializable{
	
	private String codeAgence;
	private String nomAgence;
	private Adresse adresse;
	private ArrayList<Client> listeClient;
	
	public Agence() {
		super();
		this.codeAgence = "";
		this.nomAgence = "";
		this.adresse = new Adresse();
		this.listeClient = new ArrayList<Client>();
	}

	public Agence(String codeAgence, String nomAgence, Adresse adresse, ArrayList<Client> listeClient) {
		super();
		this.codeAgence = codeAgence;
		this.nomAgence = nomAgence;
		this.adresse = adresse;
		this.listeClient = listeClient;
	}

	public String getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence(String codeAgence) {
		this.codeAgence = codeAgence;
	}

	public String getNomAgence() {
		return nomAgence;
	}

	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public ArrayList<Client> getListeClient() {
		return listeClient;
	}

	public void setListeClient(ArrayList<Client> listeClient) {
		this.listeClient = listeClient;
	}

}
