package formation.factory.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends Personne {
	private int experience;
	private boolean referent;

	public Formateur() {
		super();
	}

	public Formateur(Civilite civilite, String nom, String prenom, int experience, boolean referent) {
		this(null, civilite, nom, prenom, experience, referent);
	}

	public Formateur(Long id, Civilite civilite, String nom, String prenom, int experience, boolean referent) {
		super(id, civilite, nom, prenom);
		this.experience = experience;
		this.referent = referent;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public boolean isReferent() {
		return referent;
	}

	public void setReferent(boolean referent) {
		this.referent = referent;
	}

}
