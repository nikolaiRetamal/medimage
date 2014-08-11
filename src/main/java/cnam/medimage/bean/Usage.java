package cnam.medimage.bean;

import java.util.HashMap;
import java.util.Map;

public class Usage {

	private Long idUsage;
	private String nom;
	private Map<Long, User> users;
	private Map<Long, Dicom> dicoms;
	
	public Usage() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Usage(Long idUsage, String nom) {
		super();
		this.idUsage = idUsage;
		this.nom = nom;
		this.users = new HashMap<>();
		this.dicoms = new HashMap<>();
	}

	public void ajouterUser(User user){
		this.users.put(user.getIdUser(),user);
	}
	
	public void supprimerUser(Long id){
		this.users.remove(id);
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
	public Map<Long, User> getUsers() {
		return users;
	}
	public void setUsers(Map<Long, User> users) {
		this.users = users;
	}
	public Map<Long, Dicom> getDicoms() {
		return dicoms;
	}
	public void setDicoms(Map<Long, Dicom> dicoms) {
		this.dicoms = dicoms;
	}
	
	
}
