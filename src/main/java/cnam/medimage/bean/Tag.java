
package cnam.medimage.bean;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "tag") 
public class Tag implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	@EmbeddedId
	private IdTag id;
	
	@Column
	private Boolean codifie;
	
	public Tag (UUID id_dicom, String nom, Boolean codifie){
		this.id = new IdTag(id_dicom, nom);
		this.codifie = codifie;
	}

	public Tag(IdTag id, Boolean codifie) {
		super();
		this.id = id;
		this.codifie = codifie;
	}

	public IdTag getId() {
		return id;
	}

	public void setId(IdTag id) {
		this.id = id;
	}

	public Boolean getCodifie() {
		return codifie;
	}

	public void setCodifie(Boolean codifie) {
		this.codifie = codifie;
	}
	
}