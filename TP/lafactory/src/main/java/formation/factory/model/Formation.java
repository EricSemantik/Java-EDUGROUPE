package formation.factory.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "training")
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date dtDebut;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_code")
	private Sujet sujet;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainer_id")
	private Formateur formateur;
//	@ManyToMany
//	@JoinTable(name = "training_trainee", joinColumns = @JoinColumn(name = "training_id"), inverseJoinColumns = @JoinColumn(name = "trainee_id"))
//	private List<Participant> participants = new ArrayList<>();
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Salle salle;
	@OneToMany(mappedBy = "id.formation")
	private List<Participation> participations = new ArrayList<>();

	public Formation() {
		super();
	}

	public Formation(Date dtDebut, Sujet sujet, Formateur formateur, Salle salle) {
		this(null, dtDebut, sujet, formateur, salle);
	}

	public Formation(Long id, Date dtDebut, Sujet sujet, Formateur formateur, Salle salle) {
		super();
		this.id = id;
		this.dtDebut = dtDebut;
		this.sujet = sujet;
		this.formateur = formateur;
		this.salle = salle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

//	public List<Participant> getParticipants() {
//		return participants;
//	}
//
//	public void setParticipants(List<Participant> participants) {
//		this.participants = participants;
//	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

}
