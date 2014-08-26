package cnam.medimage.bean;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "dicom") 
public class Dicom implements Serializable{
	
	private static final long serialVersionUID = 3L;
	

	@Id
	@Column(name = "id_dicom")
	private UUID idDicom;
	
	@Column(name = "id_user")
	private UUID idUser;
	
	@Column(name = "public")
	private Boolean publique;
	

	@Column(name = "date_import")
	private Timestamp dateImport;
	

	@Column(name = "nom")
	private String nom;
	
	@Column(name = "tags")
	private List<String> tags;
	
	@Column(name = "metadatas")
	private Map<String, String> metadatas;
	
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

	public Timestamp getDateImport() {
		return dateImport;
	}

	public void setDateImport(Timestamp dateImport) {
		this.dateImport = dateImport;
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
	
	public Dicom(UUID idDicom, UUID idUser, Boolean publique, Timestamp dateImport,
			String nom) {
		super();
		this.idDicom = idDicom;
		this.idUser = idUser;
		this.publique = publique;
		this.dateImport = dateImport;
		this.nom = nom;
		this.tags = new ArrayList<String>();
		this.metadatas = new HashMap<>();
	}

	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}


	
	
	
	
}