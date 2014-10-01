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
import cnam.medimage.repository.DicomRepository;

@Controller
public class DetailImageContr {
	@RequestMapping(value="/detailImage")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id_dicom") String id_dicom) throws Exception {
		System.out.println("Je suis dans le contrôleur detail examen");
		
		//On trouve l'examen avec l'id_examen reçu en paramètre
		DicomRepository dicomRepo = new DicomRepository();
		Dicom dicom = dicomRepo.findOne(UUID.fromString(id_dicom));
		Map<String, Object> param = new HashMap<>();
		param.put("title", "Image");
		param.put("titrePage", "Detail Image");
		param.put("dicom", dicom);
		ModelAndView mv = new ModelAndView("detailImage", param);
		return mv;
	}

}
