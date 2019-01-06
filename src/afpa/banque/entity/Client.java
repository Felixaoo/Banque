package afpa.banque.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Classe entité Client. Chaque client contient une liste de comptes. 
 * @author Félix et Thomas
 * @since 01012019
 *
 */
public class Client implements Serializable {
	
	private String idClient;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String email;
	private ArrayList<Compte> comptesClient;

	
	public Client() {
		super();
		this.idClient = "";
		this.nom = "";
		this.prenom = "";
		this.email = "";
		this.comptesClient = new ArrayList<Compte>();
	}

	public Client(String idClient, String nom, String prenom, LocalDate dateNaissance, String email,
			ArrayList<Compte> comptesClient) {
		super();
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.comptesClient = comptesClient;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Compte> getComptesClient() {
		return comptesClient;
	}

	public void setComptesClient(ArrayList<Compte> comptesClient) {
		this.comptesClient = comptesClient;
	}

	@Override
	public String toString() {
		return "idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
				+ dateNaissance + ", email=" + email + ", comptesClient=" + comptesClient;
	}

	
	
}
