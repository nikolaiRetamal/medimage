package cnam.medimage.bean;

import java.util.UUID;

import javax.persistence.Column;



public class IdTag{

	@Column
	private UUID id_dicom;

	@Column
	private String nom;

	public IdTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IdTag(UUID id_dicom, String nom) {
		super();
		this.id_dicom = id_dicom;
		this.nom = nom;
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
	
}
