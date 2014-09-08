package cnam.medimage.controller.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cnam.medimage.bean.ImportForm;

public class AccueilContr implements Controller{
	
	@RequestMapping(value="/accueil")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Je suis dans le contrôleur Accueil");
		ImportForm form = new ImportForm();
		ModelAndView mv = new ModelAndView("accueil");
		mv.addObject("form", form);
		return mv;
	}

}
