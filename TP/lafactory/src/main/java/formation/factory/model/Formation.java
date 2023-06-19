package formation.factory.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Formation {
	private Long id;
	private Date dtDebut;
	private Sujet sujet;
	private Formateur formateur;
	private List<Participant> participants = new ArrayList<>();

	public Formation() {
		super();
	}

	public Formation(Long id, Date dtDebut, Sujet sujet, Formateur formateur, List<Participant> participants) {
		super();
		this.id = id;
		this.dtDebut = dtDebut;
		this.sujet = sujet;
		this.formateur = formateur;
		this.participants = participants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtDebut() {
		return dtDebut;
	}

	public void setDtDebut(Date dtDebut) {
		this.dtDebut = dtDebut;
	}

	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

}
