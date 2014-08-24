package cnam.medimage.bean;

import java.io.Serializable;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "dicom") 
public class Dicom implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "id_dicom")
	private UUID idDicom;
	
	private User user;
	
	@Column(name = "public")
	private Boolean publique;
	

	@Column(name = "date_import")
	private Date dateImport;
	

	@Column(name = "nom")
	private String nom;
	
	private Map<String, Tag> tags;
	
	private Map<String, MetaData> metadatas;
	
	public Dicom() {
		// TODO Auto-generated constructor stub
	}

	public UUID getIdDicom() {
		return idDicom;
	}

	public void setIdDicom(UUID idDicom) {
		this.idDicom = idDicom;
	}

	public Boolean getPublique() {
		return publique;
	}

	public void setPublique(Boolean publique) {
		this.publique = publique;
	}

	public Date getDateImport() {
		return dateImport;
	}

	public void setDateImport(Date dateImport) {
		this.dateImport = dateImport;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Tag> getTags() {
		return tags;
	}

	public void setTags(Map<String, Tag> tags) {
		this.tags = tags;
	}

	public Map<String, MetaData> getMetadatas() {
		return metadatas;
	}

	public void setMetadatas(Map<String, MetaData> metadatas) {
		this.metadatas = metadatas;
	}
	
	public Dicom(UUID idDicom, User user, Boolean publique, Date dateImport,
			String nom) {
		super();
		this.idDicom = idDicom;
		this.user = user;
		this.publique = publique;
		this.dateImport = dateImport;
		this.nom = nom;
		this.tags = new HashMap<String, Tag>();
		this.metadatas = new HashMap<String, MetaData>();
	}


	
	
	
	
}