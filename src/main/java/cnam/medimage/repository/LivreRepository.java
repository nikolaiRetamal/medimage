package cnam.medimage.repository;

import java.util.List;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.Livre;

public class LivreRepository {

	private Persistence persistence;
	
	public List<Livre> findByIndex(String nom) {
		return persistence.findByIndex("nom", nom, Livre.class);
	}

	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}


	public void save(Livre livre) {
		persistence.insert(livre);
	}


	public Livre findOne(UUID num) {
		return persistence.findByKey(num, Livre.class);
	}
	
}
