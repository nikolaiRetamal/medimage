package cnam.medimage.controller.controleur;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccueilContr {
	
	@RequestMapping(value="/accueil")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Je suis dans le contrôleur Accueil");
		Map<String, Object> param = new HashMap<>();
		boolean connexionExiste = request.getSession().getAttribute("user")!= null;
		param.put("title", "Accueil");
		param.put("titrePage", "MEDIMAGE");
		param.put("connexionExiste", connexionExiste);
		ModelAndView mv = new ModelAndView("accueil");
		mv.addAllObjects(param);
		return mv;
	}

}
