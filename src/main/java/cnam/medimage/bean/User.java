package cnam.medimage.bean;

import java.util.Date;

public class User {
	private Long idUser;
	private String nom;
	private String prenom;
	private Date creation;
	private String entite;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(Long idUser, String nom, String prenom, String entite) {
		super();
		this.idUser = idUser;
		this.nom = nom;
		this.prenom = prenom;
		this.entite = entite;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public String getEntite() {
		return entite;
	}
	public void setEntite(String entite) {
		this.entite = entite;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	
}
