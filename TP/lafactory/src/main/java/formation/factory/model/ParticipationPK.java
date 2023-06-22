package formation.factory.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ParticipationPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "training_id")
	private Formation formation;
	@ManyToOne
	@JoinColumn(name = "trainee_id")
	private Participant participant;

	public ParticipationPK() {
		super();
	}

	public ParticipationPK(Formation formation, Participant participant) {
		super();
		this.formation = formation;
		this.participant = participant;
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

	@Override
	public int hashCode() {
		return Objects.hash(formation, participant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipationPK other = (ParticipationPK) obj;
		return Objects.equals(formation, other.formation) && Objects.equals(participant, other.participant);
	}

}
