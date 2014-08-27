package cnam.medimage.repository;

import java.util.List;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.Dicom;

public class DicomRepository {

	private Persistence persistence;
	
	public List<Dicom> findByIndex(String nom) {
		return persistence.findByIndex("nom", nom, Dicom.class);
	}

	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}


	public void save(Dicom dicom) {
		persistence.insert(dicom);
	}


	public Dicom findOne(UUID uuid) {
		return persistence.findByKey(uuid, Dicom.class);
	}
	
}
