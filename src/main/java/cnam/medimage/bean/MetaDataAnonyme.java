package cnam.medimage.bean;

public class MetaDataAnonyme extends MetaData{

	private String nom;
	
	public MetaDataAnonyme(String idMetadata, String description,
			String valeur, String nom, String valeur2) {
		super(idMetadata, description, valeur);
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}	
	
}
