package formation.factory.repository.xml.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import formation.factory.model.Sujet;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sujets")
public class SujetWrapper {
	@XmlElement(name = "sujet")
	private List<Sujet> sujets = new ArrayList<>();

	public SujetWrapper() {
		super();
	}

	public SujetWrapper(List<Sujet> sujets) {
		super();
		this.sujets = sujets;
	}

	public List<Sujet> getSujets() {
		return sujets;
	}

	public void setSujets(List<Sujet> sujets) {
		this.sujets = sujets;
	}

}
