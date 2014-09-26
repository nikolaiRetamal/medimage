package cnam.medimage.repository;

import java.util.List;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.UsageExamen;

public class UsageExamenRepository {

	private Persistence persistence;
	
	public List<UsageExamen> findByUsage(UUID id_usage) {
		return persistence.findByIndex("id_usage", id_usage, UsageExamen.class);
	}
	
	public List<UsageExamen> findByExamen(UUID id_examen) {
		return persistence.findByIndex("id_examen", id_examen, UsageExamen.class);
	}
	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}

	public void save(UsageExamen examen) {
		persistence.insert(examen);
	}
}
