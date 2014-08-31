package cnam.medimage.controller.controleurAjax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cnam.medimage.bean.TagMesh;
import cnam.medimage.service.ServiceMeshCrawler;

import com.ximpleware.XPathParseException;

@Controller
public class AjxRechercheMesh {
	
	List<TagMesh> data = new ArrayList<TagMesh>();
	 
	AjxRechercheMesh() {
		// init data for testing
		data.add(new TagMesh("1", "ruby"));
		data.add(new TagMesh("2", "rails"));
		data.add(new TagMesh("3", "c / c++"));
		data.add(new TagMesh("4", ".net"));
		data.add(new TagMesh("5", "python"));
		data.add(new TagMesh("6", "java"));
		data.add(new TagMesh("7", "javascript"));
		data.add(new TagMesh("8", "jscript"));
 
	}
 
 
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody
	List<TagMesh> getTags(@RequestParam String tagName) {
		
		System.out.println("Ajax de parse du mesh");
		return simulateSearchResult(tagName);
 
	}
 
	private List<TagMesh> simulateSearchResult(String tagName) {

		
		ServiceMeshCrawler serviceMeshCrawler = ServiceMeshCrawler.getInstance();
 
		List<TagMesh> result = new ArrayList<TagMesh>();
 
		// iterate a list and filter by tagName
		for (TagMesh tag : data) {
			//if (tag.getIdTag().contains(tagName)) {
				result.add(tag);
			//}
		}
 
		return result;
	}
	



}
