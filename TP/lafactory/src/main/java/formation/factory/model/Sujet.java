package formation.factory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="subject")
public class Sujet {
	@XmlAttribute(name = "code")
	@Id
	private String code;
	@XmlElement
	@Column(name="name", length = 100)
	private String nom;
	@XmlElement(name = "pre-requis")
	@Column(name="prerequisite", length = 255)
	private String preRequis;
	@XmlAttribute
	@Column(name="duration")
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
