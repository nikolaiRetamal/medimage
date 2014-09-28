package cnam.medimage.repository;

import java.util.List;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;
import org.easycassandra.persistence.cassandra.SelectBuilder;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.MetaDataExamen;

public class MetaDataExamenRepository {

	private Persistence persistence;
	
	public List<MetaDataExamen> findByExamen(UUID id_examen) {
		return persistence.findByIndex("id_examen", id_examen, MetaDataExamen.class);
	}
	
	public List<MetaDataExamen> findByKeyValue(String key, String value) {
		SelectBuilder<MetaDataExamen> select = persistence.selectBuilder(MetaDataExamen.class).eq("key", key).eq("value", value);
		return select.execute();
	}
	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}

	public void save(MetaDataExamen metadataExamen) {
		persistence.insert(metadataExamen);
	}
	public List<MetaDataExamen> findAll() {
		SelectBuilder<MetaDataExamen> select = persistence.selectBuilder(MetaDataExamen.class);
		return select.execute();
	}
}
