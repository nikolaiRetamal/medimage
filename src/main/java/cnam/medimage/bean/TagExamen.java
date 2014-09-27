package cnam.medimage.bean;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tag_examen") 
public class TagExamen {
	@Id
	@Column
	private UUID id_tag_examen;
	
	@Column
	private String nom;
	
	@Column
	private UUID id_examen;
	
	public TagExamen(UUID id_tag_examen, String nom, UUID id_examen) {
		super();
		this.id_tag_examen = id_tag_examen;
		this.nom = nom;
		this.id_examen = id_examen;
	}

	public UUID getId_tag_examen() {
		return id_tag_examen;
	}

	public void setId_tag_examen(UUID id_tag_examen) {
		this.id_tag_examen = id_tag_examen;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public UUID getId_examen() {
		return id_examen;
	}

	public void setId_examen(UUID id_examen) {
		this.id_examen = id_examen;
	}
	
	
}
