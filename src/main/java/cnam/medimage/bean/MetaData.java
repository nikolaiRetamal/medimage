package cnam.medimage.bean;

public class MetaData {
	
	private String idMetadata;
	private String description;
	private String valeur;
	
	public MetaData(String idMetadata, String description, String valeur) {
		super();
		this.idMetadata = idMetadata;
		this.description = description;
		this.valeur = valeur;
	}
	
	public String getIdMetadata() {
		return idMetadata;
	}
	public void setIdMetadata(String idMetadata) {
		this.idMetadata = idMetadata;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	
	
	
}
