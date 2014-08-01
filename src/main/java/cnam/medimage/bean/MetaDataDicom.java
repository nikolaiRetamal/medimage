package cnam.medimage.bean;

public class MetaDataDicom extends MetaData{

	private String vrType;
	
	public MetaDataDicom(String idMetadata, String description, String valeur,
			String vrType) {
		super(idMetadata, description, valeur);
		this.vrType = vrType;
	}

	public String getVrType() {
		return vrType;
	}

	public void setVrType(String vrType) {
		this.vrType = vrType;
	}
	
	

}
