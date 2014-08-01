package cnam.medimage.bean;

import java.util.ArrayList;
import java.util.List;

public class TagMesh extends Tag{
	private List<String> synonymes;
	private List<String> categories;
	
	public TagMesh(String idTag, String nom) {
		super(idTag, nom);
		this.synonymes = new ArrayList<String>();
		this.categories = new ArrayList<String>();
	}

	public List<String> getSynonymes() {
		return synonymes;
	}

	public void setSynonymes(List<String> synonymes) {
		this.synonymes = synonymes;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

}
