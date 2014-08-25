package cnam.medimage.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cnam.medimage.service.ServiceMeshCrawler;

@org.springframework.stereotype.Controller
public class AideController implements Controller{


	@RequestMapping(value="/aide")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		
		System.out.println("Je suis dans le contrôleur de l'aide");
		
		HttpSession session =  request.getSession(false);
		ServletContext context = session.getServletContext();
		
		String meshFilePath = context.getInitParameter("MESH_FILEPATH");
		meshFilePath = context.getRealPath(meshFilePath);
				
		System.out.println("Mesh : "+meshFilePath);
		
		ServiceMeshCrawler serviceMeshCrawler = ServiceMeshCrawler.getInstance();
		serviceMeshCrawler.init(request, true);
		
		serviceMeshCrawler.getDescriptorUI("D000355");
				
		ModelAndView mav = new ModelAndView();
        mav.setViewName("aide");
        mav.addObject("message", "Bienvenue Nikolaï");
		return mav;
	}
}
