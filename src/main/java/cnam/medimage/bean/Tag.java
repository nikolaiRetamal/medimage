package cnam.medimage.bean;

public class Tag {
	
	private String idTag;
	private String nom;

	public Tag(String idTag, String nom) {
		super();
		this.idTag = idTag;
		this.nom = nom;
	}

	public String getIdTag() {
		return idTag;
	}
	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
}
