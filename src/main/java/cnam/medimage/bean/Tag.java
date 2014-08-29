
package cnam.medimage.bean;

import javax.persistence.Column;
import javax.persistence.Id;

import org.easycassandra.Index;

public class Tag {
	
	@Id
	@Index
	@Column(name = "id_tag")
	private String idTag;

	@Index
	@Column(name = "nom")
	private String nom;

	public Tag(String idTag, String nom) {
		super();
		this.idTag = idTag;
		this.nom = nom;
	}

	public String getIdTag() {
		return idTag;
	}
	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
}