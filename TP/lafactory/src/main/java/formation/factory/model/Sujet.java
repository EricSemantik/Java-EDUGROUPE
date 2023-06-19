package formation.factory.model;

public class Sujet {
	private String code;
	private String nom;
	private String preRequis;
	private int duree;

	public Sujet() {
		super();
	}

	public Sujet(String code, String nom, String preRequis, int duree) {
		super();
		this.code = code;
		this.nom = nom;
		this.preRequis = preRequis;
		this.duree = duree;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPreRequis() {
		return preRequis;
	}

	public void setPreRequis(String preRequis) {
		this.preRequis = preRequis;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

}
