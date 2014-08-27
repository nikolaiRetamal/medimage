package cnam.medimage.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import org.easycassandra.Index;

public class Livre implements Serializable {

    private static final long serialVersionUID = 3L;
    
    @Id
    @Column(name = "num")
    private Integer num;
    
    @Index
    @Column(name = "nom")
    private String nom;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}    
}
