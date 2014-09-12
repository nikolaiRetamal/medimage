package cnam.medimage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.Tag;
import cnam.medimage.bean.TagMesh;
import cnam.medimage.repository.DicomRepository;
import cnam.medimage.repository.TagMeshRepository;
import cnam.medimage.repository.TagRepository;
import cnam.medimage.repository.TagRepository;
import cnam.medimage.service.ServiceMeshCrawler;

@org.springframework.stereotype.Controller
public class AideController implements Controller{


	@RequestMapping(value="/aide")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		
		System.out.println("Je suis dans le contrôleur de l'aide");
										
		DicomRepository dicomRepo = new DicomRepository();
		TagRepository tagRepo = new TagRepository();
		
		List<Tag> tags = tagRepo.find("D007251");
		
		System.out.println("Combien de tags ? "+tags.size());
		
		for(Tag t:tags){
			System.out.println("Tag null ? "+(t==null?"OUI":"NON"));
		}
		
//		ArrayList<Dicom> reponse = (ArrayList<Dicom>)dicomRepo.findDicomByTagNom("D007251");
//		
//		for(Dicom d:reponse){
//			System.out.println(d.getNom());
//		}
				
		ModelAndView mav = new ModelAndView();
        mav.setViewName("aide");
        mav.addObject("message", "Bienvenue Nikolaï");
		return mav;
	}
}
