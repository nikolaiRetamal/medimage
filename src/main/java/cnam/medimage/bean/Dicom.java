package cnam.medimage.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Dicom {
	
	private Long idDicom;
	private User user;
	private Boolean publique;
	private Date dateImport;
	private String nom;
	private Map<String, Tag> tags;
	private Map<String, MetaData> metadatas;
	
	public Dicom() {
		// TODO Auto-generated constructor stub
	}
	
	public Dicom(Long idDicom, User user, Boolean publique, Date dateImport,
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

	public Long getIdDicom() {
		return idDicom;
	}

	public void setIdDicom(Long idDicom) {
		this.idDicom = idDicom;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
}