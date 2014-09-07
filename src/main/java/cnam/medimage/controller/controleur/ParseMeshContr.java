package cnam.medimage.controller.controleur;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cnam.medimage.bean.TagMesh;
import cnam.medimage.repository.TagMeshRepository;
import cnam.medimage.service.ServiceMeshCrawler;

@org.springframework.stereotype.Controller
public class ParseMeshContr  implements Controller{


	@RequestMapping(value="/parseMesh")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		
		System.out.println("Parser de la totalité du Mesh");
		
		HttpSession session =  request.getSession(false);
		ServletContext context = session.getServletContext();
		
						
		ServiceMeshCrawler serviceMeshCrawler = ServiceMeshCrawler.getInstance(request);

		TagMeshRepository meshRepo = new TagMeshRepository();
		
		ArrayList<TagMesh> fullMesh = serviceMeshCrawler.parseThemAll();
		
		for(TagMesh tag : fullMesh){
			try{
				meshRepo.save(tag);		
			}catch(Exception e){
				System.out.println("Erreur à l'insertion CQL de : "+tag.getIdTag()+"/"+tag.getNom());
			}
		}
		
		System.out.println("Parsing fini : "+fullMesh.size()+" Tags référencés.");
				
		ModelAndView mav = new ModelAndView();
        mav.setViewName("parseMesh");
        mav.addObject("message", "Le Mesh est en base de donnée...");
		return mav;
	} 
	
}