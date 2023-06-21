package formation.factory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("participant")
public class Participant extends Personne {
	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	private Date dtNaissance;
	@Column(name="rating")
	private int note;

	public Participant() {
		super();
	}

	public Participant(Civilite civilite, String nom, String prenom, Date dtNaissance, int note) {
		this(null, civilite, nom, prenom, dtNaissance, note);
	}

	public Participant(Long id, Civilite civilite, String nom, String prenom, Date dtNaissance, int note) {
		super(id, civilite, nom, prenom);
		this.dtNaissance = dtNaissance;
		this.note = note;
	}

	public Date getDtNaissance() {
		return dtNaissance;
	}

	public void setDtNaissance(Date dtNaissance) {
		this.dtNaissance = dtNaissance;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

}
