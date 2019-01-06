package afpa.banque.entity;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Classe entitié Banque. Chque Banque contient une liste d'agence. 
 * @author Félix et Thomas 
 * @since 01012019
 */
public class Banque implements Serializable{

	
	private String nom;
	private Adresse adresse;
	private ArrayList<Agence> banqueAgences;
	
	public Banque() {
		super();
		this.nom = "ParisBanque";
		Pays p = new Pays ("France", "FR");
		Adresse a = new Adresse("12", "Champs Elysées", "Paris", "75000", p);
		this.banqueAgences = new ArrayList<Agence>();
	}

	public Banque(String nom, Adresse adresse, ArrayList<Agence> banqueAgences) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.banqueAgences = banqueAgences;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public ArrayList<Agence> getBanqueAgences() {
		return banqueAgences;
	}

	public void setBanqueAgences(ArrayList<Agence> banqueAgences) {
		this.banqueAgences = banqueAgences;
	}

	@Override
	public String toString() {
		return "Banque [nom=" + nom + ", adresse=" + adresse + ", banqueAgences=" + banqueAgences + "]";
	}

	
}
