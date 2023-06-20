package formation.factory.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sujet {
	@XmlAttribute(name = "code")
	private String code;
	@XmlElement
	private String nom;
	@XmlElement(name = "pre-requis")
	private String preRequis;
	@XmlAttribute
	private int duree;

	public Sujet() {
		super();
	}

	public Sujet(String code, String nom, String preRequis, int duree) {
		super();
		this.code = code;
		this.nom = nom;
		this.preRequis = preRequis;
		this.duree = duree;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPreRequis() {
		return preRequis;
	}

	public void setPreRequis(String preRequis) {
		this.preRequis = preRequis;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}


}
