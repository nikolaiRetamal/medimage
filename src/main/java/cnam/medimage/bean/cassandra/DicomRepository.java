package cnam.medimage.bean.cassandra;

import java.util.UUID;

import org.easycassandra.persistence.cassandra.spring.CassandraRepository;
import org.easycassandra.persistence.cassandra.spring.CassandraTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cnam.medimage.bean.Dicom;

@Repository("dicomRepository")
public class DicomRepository extends CassandraRepository<Dicom, UUID>{

	
	@Autowired
	private CassandraTemplate cassandraTemplate;
	
	@Override
	protected CassandraTemplate getCassandraTemplate() {
		return cassandraTemplate;
	}

}