package cnam.medimage.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;
import org.easycassandra.persistence.cassandra.SelectBuilder;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.MetaData;
import cnam.medimage.bean.Tag;

public class DicomRepository {

	private Persistence persistence;
	
	public List<Dicom> findByIndex(String nom) {
		return persistence.findByIndex("nom", nom, Dicom.class);
	}

	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}


	public void save(Dicom dicom) {
		MetadataRepository metaRepo = new MetadataRepository();
		TagRepository tagRepo = new TagRepository();
		
		//Sauvegarde des MetaDatas dans la table medimage.metadata
	    Iterator it = dicom.getMetadatas().entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry metadata = (Map.Entry)it.next();
	        metaRepo.save(new MetaData(dicom.getId_dicom(),
	        		(String) metadata.getKey(),(String) metadata.getValue()));
	    }
	    //Sauvegarde des tags dans la table medimage.tag
	    for(String tag:dicom.getTags()) {
	    	Tag monTag = new Tag(dicom.getId_dicom(),tag,Boolean.TRUE);
	    	
	    	//Détection des tags codifiés des non-codifiés
	    	//On split le tag sur le caractère "D" et on tente de faire un Integer avec la deuxième partie
	    	//Si le tag est de type "D0000000" ça passe, il est codifié, sinon -> non-codifié
	    	try{
	    		new Integer(tag.trim().split("D")[1]);
	    	}catch(Exception e){
	    		//Le tag n'est pas un id_tag de type "DXXXXX"
	    		monTag.setCodifie(Boolean.FALSE);
	    	}
	    	
	        tagRepo.save(monTag);
	    }
		persistence.insert(dicom);
	}

	public List<Dicom> findAll() {
		SelectBuilder<Dicom> select = persistence.selectBuilder(Dicom.class);
		return select.execute();
	}
	public Dicom findOne(UUID uuid) {
		return persistence.findByKey(uuid, Dicom.class);
	}
}
