package cnam.medimage.repository;

import org.easycassandra.persistence.cassandra.ClusterInformation;
import org.easycassandra.persistence.cassandra.EasyCassandraManager;
import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.Livre;

public enum CassandraManager {
	INSTANCE;
	private EasyCassandraManager easyCassandraManager;
	private Persistence persistence;
	{
		easyCassandraManager = new EasyCassandraManager(ClusterInformation.create().addHost("localhost").withKeySpace("medimage"));
		easyCassandraManager.addFamilyObject(Livre.class);
		persistence = easyCassandraManager.getPersistence();
	}
	
	public Persistence getPersistence() {
		return persistence;
	}
}
