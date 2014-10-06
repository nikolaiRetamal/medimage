package cnam.medimage.controller.controleur;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class AccueilContr {
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("Je suis dans le contrôleur Accueil");
		Map<String, Object> param = new HashMap<>();
		String user = "";
		if(session.getAttribute("user") !=null){
			System.out.println("USER = " + session.getAttribute("user").toString());
			user = session.getAttribute("user").toString();
		}
		param.put("title", "Accueil");
		param.put("titrePage", "MEDIMAGE");		
		param.put("user", user);
		ModelAndView mv = new ModelAndView("accueil");
		mv.addAllObjects(param);
		return mv;
	}
}
