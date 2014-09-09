package cnam.medimage.bean;

public class ImportForm {

	private String nom_examen;
	private String nom_usage;
	private boolean publique;
	
	public String getNom_examen() {
		return nom_examen;
	}
	public void setNom_examen(String nom_examen) {
		this.nom_examen = nom_examen;
	}
	public String getNom_usage() {
		return nom_usage;
	}
	public void setNom_usage(String nom_usage) {
		this.nom_usage = nom_usage;
	}
	public boolean isPublique() {
		return publique;
	}
	public void setPublique(boolean publique) {
		this.publique = publique;
	}
	
}
