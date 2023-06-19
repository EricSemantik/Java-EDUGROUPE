package formation.factory.model;

import java.util.Date;

public class Participant extends Personne {
	private Date dtNaissance;
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
