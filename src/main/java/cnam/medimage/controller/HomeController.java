package cnam.medimage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String test(HttpServletResponse response) throws IOException{
		return "redirect:/import";
	}
	@RequestMapping(value="/cacahouetes")
	public String cacahouetes(HttpServletResponse response) throws IOException{
		return "redirect:/aide";
	}
}
