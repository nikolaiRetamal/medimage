package cnam.medimage.repository;

import java.util.List;

import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.TagMesh;

public class TagMeshRepository {
	
	private Persistence persistence;
	
	public List<TagMesh> findByIndex(String id_tag) {
		return persistence.findByIndex("id_tag", id_tag, TagMesh.class);
	}

	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}


	public void save(TagMesh tagmesh) {
		persistence.insert(tagmesh);
	}


	public TagMesh findOne(String idTag) {
		return persistence.findByKey(idTag, TagMesh.class);
	}
}