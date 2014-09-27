package cnam.medimage.bean;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.easycassandra.Index;

@Entity(name = "metadata_examen") 
public class MetaDataExamen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private UUID id_metadata_examen;
	
	@Column
	@Index
	private UUID id_examen;
	
	@Column
	@Index
	private String key;

	@Column
	@Index
	private String value;

	public MetaDataExamen(UUID id_metadata_examen, UUID id_examen, String key,
			String value) {
		super();
		this.id_metadata_examen = id_metadata_examen;
		this.id_examen = id_examen;
		this.key = key;
		this.value = value;
	}

	public UUID getId_metadata_examen() {
		return id_metadata_examen;
	}

	public void setId_metadata_examen(UUID id_metadata_examen) {
		this.id_metadata_examen = id_metadata_examen;
	}

	public UUID getId_examen() {
		return id_examen;
	}

	public void setId_examen(UUID id_examen) {
		this.id_examen = id_examen;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
