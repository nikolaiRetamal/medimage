package cnam.medimage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AideController {

	@RequestMapping(value="/aide")
	public ModelAndView aide(HttpServletResponse response) throws IOException{
		System.out.println("Je suis dans le contrôleur de l'aide");
		ModelAndView mav = new ModelAndView();
        mav.setViewName("aide");
        mav.addObject("message", "Bienvenue Nikolaï");
		return mav;
	}
}
