package formation.factory;

public class Produit {
	private String nom;
	private double prixHT;
	private double taux;

	public Produit() {
		super();
	}

	public Produit(String nom, double prixHT, double taux) {
		super();
		this.nom = nom;
		this.prixHT = prixHT;
		this.taux = taux;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(double prixHT) {
		this.prixHT = prixHT;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public static Produit parse(String ligne) {	
		String[] items = ligne.split(";");
		
		return new Produit(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]));
	}

	@Override
	public String toString() {
		return "Produit [nom=" + nom + ", prixHT=" + prixHT + ", taux=" + taux + "]";
	}
	
	
}
