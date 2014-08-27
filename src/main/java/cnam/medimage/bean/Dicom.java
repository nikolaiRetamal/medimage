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
	@Column(name = "id_dicom")
	private UUID idDicom;
	
	@Column(name = "id_user")
	private UUID idUser;
	
	@Column(name = "public")
	private Boolean publique;
	

	@Column(name = "date_import")
	private Date dateImport;
	
	@Index
	@Column(name = "nom")
	private String nom;
	
	@ElementCollection
	@Column(name = "tags")
	private List<String> tags;
	
	@ElementCollection
	@Column(name = "metadatas")
	private Map<String, String> metadatas;
	
	public Dicom() {
		this.tags = new LinkedList<>();
		this.metadatas = new HashMap<>();
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
	
	public Dicom(UUID idDicom, UUID idUser, Boolean publique, Date dateImport,
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