package cnam.medimage.bean;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "usage_examen") 
public class UsageExamen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private UUID id_usage_examen;
	
	@Column
	private UUID id_usage;
	
	@Column
	private UUID id_examen;

	public UsageExamen(UUID id_usage_examen, UUID id_usage, UUID id_examen) {
		super();
		this.id_usage_examen = id_usage_examen;
		this.id_usage = id_usage;
		this.id_examen = id_examen;
	}

	public UUID getId_usage_examen() {
		return id_usage_examen;
	}

	public void setId_usage_examen(UUID id_usage_examen) {
		this.id_usage_examen = id_usage_examen;
	}

	public UUID getId_usage() {
		return id_usage;
	}

	public void setId_usage(UUID id_usage) {
		this.id_usage = id_usage;
	}

	public UUID getId_examen() {
		return id_examen;
	}

	public void setId_examen(UUID id_examen) {
		this.id_examen = id_examen;
	}

}
