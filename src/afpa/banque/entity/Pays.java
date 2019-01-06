package afpa.banque.entity;

import java.io.Serializable;
/**
 * Classe entité Pays
 * @author Thomas et Félix
 * @since 01012019
 *
 */
public class Pays implements Serializable {
	private String nom;
	private String code;

	public Pays(String nom, String code) {
		super();
		this.nom = nom;
		this.code = code;
	}

	public Pays() {
		super();
		// TODO Auto-generated constructor stub
		nom = "";
		code = "";
	}	

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Pays [nom=" + nom + ", code=" + code + "]";
	}
}
