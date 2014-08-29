package cnam.medimage.bean;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.ArrayList;
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

import org.easycassandra.Index;


@Entity(name = "dicom") 
public class Dicom implements Serializable{
	
	private static final long serialVersionUID = 3L;
	

	@Id
	@Column
	private UUID id_dicom;
	
	@Column
	private UUID id_user;
	
	@Column(name = "public")
	private Boolean publique;
	

	@Column
	private Date date_import;
	
	@Index
	@Column(name = "nom")
	private String nom;
	
	@ElementCollection
	@Column(name = "tags")
	private List<String> tags;
	
	@ElementCollection
	@Column
	private Map<String, String> metadatas;
	
	public Dicom() {
		this.tags = new LinkedList<>();
		this.metadatas = new HashMap<>();
	}

	public UUID getId_dicom() {
		return id_dicom;
	}

	public void setId_dicom(UUID id_dicom) {
		this.id_dicom = id_dicom;
	}

	public UUID getId_user() {
		return id_user;
	}

	public void setId_user(UUID id_user) {
		this.id_user = id_user;
	}

	public Boolean getPublique() {
		return publique;
	}

	public void setPublique(Boolean publique) {
		this.publique = publique;
	}

	public Date getDate_import() {
		return date_import;
	}

	public void setDate_import(Date date_import) {
		this.date_import = date_import;
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