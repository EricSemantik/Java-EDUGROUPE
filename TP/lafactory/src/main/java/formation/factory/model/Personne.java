package formation.factory.model;

public abstract class Personne {
	private Long id;
	private Civilite civilite;
	private String nom;
	private String prenom;
	private Adresse adresse;

	public Personne() {
		super();
	}

	public Personne(Civilite civilite, String nom, String prenom) {
		this(null, civilite, nom, prenom);
	}

	public Personne(Long id, Civilite civilite, String nom, String prenom) {
		super();
		this.id = id;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
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

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}
