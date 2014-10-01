package cnam.medimage.controller.controleur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.Examen;
import cnam.medimage.repository.DicomRepository;
import cnam.medimage.repository.ExamenDicomRepository;
import cnam.medimage.repository.ExamenRepository;

@Controller
public class DetailExamenContr {
	@RequestMapping(value="/detailExamen")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id_examen") String id_examen) throws Exception {
		System.out.println("Je suis dans le contrôleur detail examen");
		
		//On trouve l'examen avec l'id_examen reçu en paramètre
		ExamenRepository examRepo = new ExamenRepository();
		Examen examen = examRepo.findOne(UUID.fromString(id_examen));
		
		//On récupère la liste de dicoms associés à l'examen
		ExamenDicomRepository examDicomRepo = new ExamenDicomRepository();
		List<Dicom> dicoms = examDicomRepo.getListeDicoms(UUID.fromString(id_examen));
		System.out.println("date = " + examen.getDate_import());
		Map<String, Object> param = new HashMap<>();
		param.put("title", "Examen");
		param.put("titrePage", "Detail Examen");
		param.put("nbImages", dicoms.size());
		param.put("examen", examen);
		param.put("dicoms", dicoms);
		ModelAndView mv = new ModelAndView("detailExamen", param);
		return mv;
	}
}
