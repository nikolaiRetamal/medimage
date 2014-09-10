
package cnam.medimage.bean;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.easycassandra.Index;

@Entity(name = "tag") 
public class Tag implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	@Id
	@Column
	private UUID id_dicom;
	@Index
	@Column
	private String nom;
	@Column
	private Boolean codifie;
	
	public Tag(UUID id_dicom, String nom, Boolean codifie) {
		super();
		this.id_dicom = id_dicom;
		this.nom = nom;
		this.codifie = codifie;
	}

	public UUID getId_dicom() {
		return id_dicom;
	}

	public void setId_dicom(UUID id_dicom) {
		this.id_dicom = id_dicom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getCodifie() {
		return codifie;
	}

	public void setCodifie(Boolean codifie) {
		this.codifie = codifie;
	}
	
}