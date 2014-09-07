package cnam.medimage.repository;

import java.util.List;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.Tag;

public class TagRepository {

	private Persistence persistence;
	
	public List<Tag> findByNom(String nom) {
		return persistence.findByIndex("nom", nom, Tag.class);
	}

	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}


	public void save(Tag user) {
		persistence.insert(user);
	}

	public Tag findByDicomId(UUID uuid) {
		return persistence.findByKey(uuid, Tag.class);
	}
}
