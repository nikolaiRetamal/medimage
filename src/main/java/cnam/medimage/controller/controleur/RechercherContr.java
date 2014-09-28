package cnam.medimage.controller.controleur;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dcm4che2.util.TagUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cnam.medimage.bean.AccueilForm;
import cnam.medimage.bean.UsageExamen;
import cnam.medimage.repository.UsageExamenRepository;

@Controller
public class RechercherContr {

	@RequestMapping(value = "/recherche", method = RequestMethod.POST)
	public void effectuerRecherche(HttpServletRequest request,
	HttpServletResponse response, @ModelAttribute AccueilForm form) throws IOException {
		
		AccueilForm monForm = form;
		System.out.println("usage : " + monForm.getUsage());
		System.out.println("usageConnu: " + monForm.getUsageConnu() + " , " + UUID.fromString(monForm.getUsageConnu()));
		System.out.println("metas : " + monForm.getMetadatas());
		System.out.println("tags : " + monForm.getChosenTagsValue());
		
		List<UsageExamen> usageExamens;
		Set<UUID> examens = new HashSet<>();
		/*Recherche par usage*/
		if(!"".equals(monForm.getUsageConnu())){
			UsageExamenRepository usageExamRepo = new UsageExamenRepository();
			usageExamens = usageExamRepo.findByUsage(UUID.fromString(monForm.getUsageConnu()));
			//usageExamens = usageExamRepo.findAll();
			System.out.println("exams trouvé : " +usageExamens.size());
			for(UsageExamen usageExam : usageExamens){
				examens.add(usageExam.getId_examen());
				System.out.println("exam trouvé : " + usageExam.getId_examen());
			}
		}
		
		/*recherche par metadonnes*/
		if(!"".equals(monForm.getUsageConnu())){
	        Gson gson = new GsonBuilder().create();
	        List<metadataJSON> metaList = gson.fromJson(monForm.getMetadatas(), new TypeToken<List<metadataJSON>>(){}.getType());
	        for(metadataJSON meta : metaList){
	        	System.out.println(meta.getKey() +"-"+meta.getValue());
	        	System.out.println(TagUtils.toString(Integer.parseInt(meta.getKey())));
	        }
		}
		
		/*recherche par Tags*/
		
	}
	
	public class metadataJSON{
		private String key;
		private String value;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}		
	}
}
