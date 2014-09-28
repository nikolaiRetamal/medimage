package cnam.medimage.repository;

import java.util.List;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;
import org.easycassandra.persistence.cassandra.SelectBuilder;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.Usage;

public class UsageRepository {

	private Persistence persistence;
	
	public List<Usage> find(String key, String value) {
		return persistence.selectBuilder(Usage.class).eq("key", key).eq("value", value).execute();
	}
	
	public List<Usage> findById(UUID id_usage) {
		return persistence.findByIndex("id_usage", id_usage, Usage.class);
	}
	
	public List<Usage> findAll() {
		SelectBuilder<Usage> select = persistence.selectBuilder(Usage.class);
		return select.execute();
	}
	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}

	public void save(Usage usage) {
		persistence.insert(usage);
	}
}
