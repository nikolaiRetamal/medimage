package cnam.medimage.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "examen") 
public class Examen implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	@Id
	@Column
	private UUID id_examen;
	@Column
	private Date date_import;
	@Column
	private UUID id_user;
	@Column
	private String nom;
	@ElementCollection
	@Column
	private List<String> tags;
	@ElementCollection
	@Column
	private Map<String, String> metadatas;
	
	public Examen() {
		this.tags = new LinkedList<>();
		this.metadatas = new HashMap<>();
	}

	public Examen(UUID id_examen, Date date_import, UUID id_user, String nom,
			List<String> tags, Map<String, String> metadatas) {
		super();
		this.id_examen = id_examen;
		this.date_import = date_import;
		this.id_user = id_user;
		this.nom = nom;
		this.tags = tags;
		this.metadatas = metadatas;
	}


	public UUID getId_examen() {
		return id_examen;
	}

	public void setId_examen(UUID id_examen) {
		this.id_examen = id_examen;
	}

	public Date getDate_import() {
		return date_import;
	}

	public void setDate_import(Date date_import) {
		this.date_import = date_import;
	}

	public UUID getId_user() {
		return id_user;
	}

	public void setId_user(UUID id_user) {
		this.id_user = id_user;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getMetadatas() {
		return metadatas;
	}

	public void setMetadatas(Map<String, String> metadatas) {
		this.metadatas = metadatas;
	}
}
