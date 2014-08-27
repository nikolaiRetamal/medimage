package cnam.medimage.bean;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Id;

import org.easycassandra.Index;

public class Livre implements Serializable {

    private static final long serialVersionUID = 3L;
    
    @Id
    @Column(name = "num")
    private UUID num;
    
    @Index
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "public")
    private Boolean publique;
    
	@Column(name = "date_import")
	private Date dateImport;
	
	@ElementCollection
	@Column(name = "tags")
	private List<String> tags;

	public UUID getNum() {
		return num;
	}

	public void setNum(UUID num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getPublique() {
		return publique;
	}

	public void setPublique(Boolean publique) {
		this.publique = publique;
	}

	public Date getDateImport() {
		return dateImport;
	}

	public void setDateImport(Date dateImport) {
		this.dateImport = dateImport;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
