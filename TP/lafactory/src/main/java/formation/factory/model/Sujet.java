package formation.factory.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "subject", uniqueConstraints = @UniqueConstraint(columnNames = { "code", "duration" }))
public class Sujet {
	@XmlAttribute(name = "code")
	@Id
	private String code;
	@Version
	private int version;
	@XmlElement
	@Column(name = "name", length = 100)
	private String nom;
	@XmlElement(name = "pre-requis")
	@Column(name = "prerequisite", length = 255)
	private String preRequis;
	@XmlAttribute
	@Column(name = "duration")
	private int duree;
	@OneToMany(mappedBy = "sujet", fetch = FetchType.LAZY)
	private List<Formation> formations = new ArrayList<>();

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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

}
