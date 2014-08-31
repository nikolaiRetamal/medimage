package cnam.medimage.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.MetaData;

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
	    Iterator it = dicom.getMetadatas().entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry metadata = (Map.Entry)it.next();
	        metaRepo.save(new MetaData(dicom.getId_dicom(),
	        		(String) metadata.getKey(),(String) metadata.getValue()));
	    }
		persistence.insert(dicom);
	}


	public Dicom findOne(UUID uuid) {
		return persistence.findByKey(uuid, Dicom.class);
	}
}
