package cnam.medimage.controller.controleur;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("nom")
public class ControlIdentificationContr {
	@RequestMapping(value="/controle_identification")
	public ModelAndView controleIdentification(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String nom) throws Exception {
		System.out.println("nom = " + nom);
		Map<String, Object> param = new HashMap<>();
		param.put("title", "Accueil");
		param.put("titrePage", "MEDIMAGE");
		param.put("nom", nom);
		ModelAndView mv = new ModelAndView("accueil");
		mv.addAllObjects(param);
		return mv;
	}
}
