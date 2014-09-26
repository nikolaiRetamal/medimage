package cnam.medimage.repository;

import java.util.List;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;

import cnam.medimage.bean.Examen;
import cnam.medimage.bean.Tag;
import cnam.medimage.bean.TagExamen;
import cnam.medimage.bean.UsageExamen;

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

	public void save(Examen examen, UUID id_usage) {
		UsageExamenRepository usageExamRepo = new UsageExamenRepository();
		TagExamenRepository tagExamRepo = new TagExamenRepository();
		usageExamRepo.save(new UsageExamen(UUID.randomUUID(), id_usage, examen.getId_examen()));
		for(String tag : examen.getTags()) {
			tagExamRepo.save(new TagExamen(UUID.randomUUID(), tag, examen.getId_examen()));
	    }
		persistence.insert(examen);
	}

}
