package cnam.medimage.controller.controleur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cnam.medimage.bean.Dicom;
import cnam.medimage.repository.DicomRepository;

@Controller
public class DetailExamenContr {
	@RequestMapping(value="/detailExamen")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Je suis dans le contrôleur detail examen");
		Map<String, Object> param = new HashMap<>();
		param.put("title", "Examen");
		param.put("titrePage", "Detail Examen");
		DicomRepository dicoRepo = new DicomRepository();	
		List<Dicom> dicoms = dicoRepo.findAll();
		param.put("dicoms", dicoms);
		ModelAndView mv = new ModelAndView("detailExamen", param);
		return mv;
	}
}
