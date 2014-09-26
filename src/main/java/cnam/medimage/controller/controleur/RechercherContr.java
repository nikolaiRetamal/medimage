package cnam.medimage.controller.controleur;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cnam.medimage.bean.AccueilForm;

@Controller
public class RechercherContr {

	@RequestMapping(value = "/recherche", method = RequestMethod.POST)
	public void effectuerRecherche(HttpServletRequest request,
	HttpServletResponse response, @ModelAttribute AccueilForm form) throws IOException {
		
		AccueilForm monForm = form;
		System.out.println("usage : " + monForm.getUsage());
		System.out.println("usageConnu: " + monForm.getUsageConnu());
		System.out.println("metas : " + monForm.getMetadatas());
		System.out.println("tags : " + monForm.getChosenTagsValue());
		/*Recherche par usage*/
		if(!"".equals(monForm.getUsageConnu())){
			
		}
		
		
		/*recherche par metadonnes*/
		
		
		
		/*recherche par Tags*/
		
	}
	
}
