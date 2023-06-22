package formation.factory.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "interest")
public class Participation {

	@EmbeddedId
	private ParticipationPK id;

	@Version
	private int version;

	public Participation() {
		super();
	}

	public Participation(Formation formation, Participant participant) {
		super();
		this.id = new ParticipationPK(formation, participant);
	}

	public ParticipationPK getId() {
		return id;
	}

	public void setId(ParticipationPK id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
