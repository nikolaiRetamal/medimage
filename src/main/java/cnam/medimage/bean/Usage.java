package cnam.medimage.bean;

import java.util.ArrayList;
import java.util.List;

public class Usage {

	private Long idUsage;
	private String nom;
	private List<User> users;
	private List<Dicom> dicoms;
	
	public Usage() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Usage(Long idUsage, String nom) {
		super();
		this.idUsage = idUsage;
		this.nom = nom;
		this.users = new ArrayList<>();
		this.dicoms = new ArrayList<>();
	}

	public void ajouterUser(User user){
		this.users.add(user);
	}
	
	public void supprimerUser(Long id){

	}

	public Long getIdUsage() {
		return idUsage;
	}
	public void setIdUsage(Long idUsage) {
		this.idUsage = idUsage;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Dicom> getDicoms() {
		return dicoms;
	}
	public void setDicoms(List<Dicom> dicoms) {
		this.dicoms = dicoms;
	}
	
	
}
