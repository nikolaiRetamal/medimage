package cnam.medimage.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "metadata")
public class MetaData implements Serializable{
	
	private static final long serialVersionUID = 3L;
	@Id
	@Column
	private String id_dicom;
	@Id
	@Column
	private String key;
	@Id
	@Column
	private String value;
	
	
	public String getId_dicom() {
		return id_dicom;
	}
	public void setId_dicom(String id_dicom) {
		this.id_dicom = id_dicom;
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
