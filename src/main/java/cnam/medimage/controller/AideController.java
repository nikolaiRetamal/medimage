package cnam.medimage.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cnam.medimage.bean.TagMesh;
import cnam.medimage.repository.DicomRepository;
import cnam.medimage.repository.TagMeshRepository;
import cnam.medimage.service.ServiceMeshCrawler;

@org.springframework.stereotype.Controller
public class AideController implements Controller{


	@RequestMapping(value="/aide")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		
		System.out.println("Je suis dans le contrôleur de l'aide");
										
		ServiceMeshCrawler serviceMeshCrawler = ServiceMeshCrawler.getInstance(request);
		
		ArrayList<String> reponse = serviceMeshCrawler.getNuageRechercheFromDescriptorUI("D001990");
		
		for(String s:reponse){
			System.out.println(s);
		}
				
		ModelAndView mav = new ModelAndView();
        mav.setViewName("aide");
        mav.addObject("message", "Bienvenue Nikolaï");
		return mav;
	}
}
