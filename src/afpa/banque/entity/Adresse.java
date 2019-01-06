package afpa.banque.entity;

import java.io.Serializable;
/**
 * 
 * @author Félix et Thomas 
 * Classe permettant de créer des entités adresses 
 */
public class Adresse implements Serializable {

	private String numRue;
	private String rue;
	private String ville;
	private String codePostale;
	private Pays pays;
	
	public Adresse() {
		super();
		this.numRue = "";
		this.rue = "";
		this.ville = "";
		this.codePostale= "";
		this.pays = new Pays();
	}
	

	public Adresse(String numRue, String rue, String ville, String codePostale, Pays pays) {
		super();
		this.numRue = numRue;
		this.rue = rue;
		this.ville = ville;
		this.codePostale = codePostale;
		this.pays = pays;
	}

	public String getNumRue() {
		return numRue;
	}

	public void setNumRue(String numRue) {
		this.numRue = numRue;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	@Override
	public String toString() {
		return "Adresse [numRue=" + numRue + ", rue=" + rue + ", ville=" + ville + ", codePostale=" + codePostale
				+ ", pays=" + pays + "]";
	}
}
