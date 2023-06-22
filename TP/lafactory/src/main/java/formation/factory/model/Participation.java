package formation.factory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name="interest", uniqueConstraints = @UniqueConstraint(columnNames = {"training_id", "trainee_id"}))
public class Participation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@ManyToOne
	@JoinColumn(name="training_id")
	private Formation formation;
	@ManyToOne
	@JoinColumn(name="trainee_id")
	private Participant participant;

	public Participation() {
		super();
	}

	public Participation(Formation formation, Participant participant) {
		super();
		this.formation = formation;
		this.participant = participant;
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

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

}
