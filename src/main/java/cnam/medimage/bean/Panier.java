package cnam.medimage.bean;

import java.util.Map;

public class Panier {
	
	private Long idPanier;
	private User user;
	private String zipPath;
	private Map<Long, Dicom> contenu;
	private Boolean publique;
	
	public Panier() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Panier(Long idPanier, User user, String zipPath,
			Map<Long, Dicom> contenu, Boolean publique) {
		super();
		this.idPanier = idPanier;
		this.user = user;
		this.zipPath = zipPath;
		this.contenu = contenu;
		this.publique = publique;
	}

	public void ajouterDicom(Dicom dicom){
		this.contenu.put(dicom.getIdDicom(), dicom);
	}

	public void supprimerDicom(Long id){
		this.contenu.remove(id);
	}
	
	public Long getIdPanier() {
		return idPanier;
	}
	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getZipPath() {
		return zipPath;
	}
	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
	public Map<Long, Dicom> getContenu() {
		return contenu;
	}
	public void setContenu(Map<Long, Dicom> contenu) {
		this.contenu = contenu;
	}
	public Boolean getPublique() {
		return publique;
	}
	public void setPublique(Boolean publique) {
		this.publique = publique;
	}
	
	
}
