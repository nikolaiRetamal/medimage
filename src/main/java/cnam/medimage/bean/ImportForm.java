package cnam.medimage.bean;

public class ImportForm {

	private Examen examen;
	private String usage;
	private boolean publique;
	
	
	public ImportForm() {
		this.examen = new Examen();
		publique = false;

	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public boolean isPublique() {
		return publique;
	}
	public void setPublique(boolean publique) {
		this.publique = publique;
	}
	
	
}
