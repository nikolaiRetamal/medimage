package cnam.medimage.repository;

import java.util.List;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.Examen;

public class ExamenRepository {

	private Persistence persistence;
	
	public List<Examen> findByExam(UUID id_examen) {
		return persistence.findByIndex("id_examen", id_examen, Examen.class);
	}
	
	public List<Examen> findByDicom(UUID id_dicom) {
		return persistence.findByIndex("id_dicom", id_dicom, Examen.class);
	}
	
	{
		this.persistence = CassandraManager.INSTANCE.getPersistence();
	}

	public void save(Examen examen) {
		persistence.insert(examen);
	}

}
