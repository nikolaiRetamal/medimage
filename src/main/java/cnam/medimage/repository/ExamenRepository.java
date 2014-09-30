package cnam.medimage.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.easycassandra.persistence.cassandra.Persistence;
import org.easycassandra.persistence.cassandra.SelectBuilder;

import cnam.medimage.bean.Examen;
import cnam.medimage.bean.MetaDataExamen;
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

	public List<Examen> findAll() {
		SelectBuilder<Examen> select = persistence.selectBuilder(Examen.class);
		return select.execute();
	}
	
	public Examen findOne(UUID uuid) {
		return persistence.findByKey(uuid, Examen.class);
	}
	
	public void save(Examen examen, UUID id_usage) {
		//Sauvergarde en base de l'association usage-exam dans la table USAGE_EXAMEN
		UsageExamenRepository usageExamRepo = new UsageExamenRepository();
		usageExamRepo.save(new UsageExamen(UUID.randomUUID(), id_usage, examen.getId_examen()));
		
		//Sauvegarde des tags dans la table TAG_EXAMEN
		TagExamenRepository tagExamRepo = new TagExamenRepository();
		for(String tag : examen.getTags()) {
			tagExamRepo.save(new TagExamen(UUID.randomUUID(), tag, examen.getId_examen()));
	    }

		//Sauvegarde des metadonnées dans la table METADATA_EXAMEN
		MetaDataExamenRepository metadataExamenRepo = new MetaDataExamenRepository();
		for (Map.Entry<String, String> entry : examen.getMetadatas().entrySet()){
			metadataExamenRepo.save(new MetaDataExamen(UUID.randomUUID(), examen.getId_examen(), entry.getKey(), entry.getValue()));
		}

		//Sauvegarde en base de l'examen
		persistence.insert(examen);
	}

}
