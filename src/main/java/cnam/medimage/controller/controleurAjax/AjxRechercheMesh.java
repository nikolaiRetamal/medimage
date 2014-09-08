package cnam.medimage.controller.controleurAjax;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cnam.medimage.bean.IndexMesh;
import cnam.medimage.bean.TagMesh;
import cnam.medimage.service.ServiceMeshCrawler;

import com.ximpleware.NavException;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

@Controller
public class AjxRechercheMesh {
	
	List<TagMesh> data = new ArrayList<TagMesh>();
	 
 
	@RequestMapping(value = "/getTags", method = RequestMethod.GET, headers="Accept=application/json", produces = "application/json" )
	public @ResponseBody
	List<TagMesh> getTags(@RequestParam("term") String tagName) {
		
		System.out.println("Ajax de parse du mesh");
		List<TagMesh> result = null;
		
		try{

			//result = searchResultFromXml(tagName);
			result = searchResultFromBase(tagName);
			
		}catch (Exception e){
			//traiter recherche vide
			result = new ArrayList<TagMesh>();
		}
		
		
		//On affiche les résultats par curiosité
		for(TagMesh t:result){
			System.out.println(t.getNom());
		}
 
		return result;
	}
 

	/**
	 * 
	 * Appelle le serviceMesh
	 * 
	 * @param tagName
	 * @return
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 * @throws XMLStreamException 
	 * @throws FileNotFoundException 
	 */
	private List<TagMesh> searchResultFromXml(String tagName) throws XPathParseException, XPathEvalException, NavException, FileNotFoundException, XMLStreamException {
		
		//On ne passe pas le request, l'initialisation a déjà été effectuée dans AccueilImportContr
		ServiceMeshCrawler serviceMeshCrawler = ServiceMeshCrawler.getInstance(null);
 
		data = serviceMeshCrawler.getListTagJsonFromXML(tagName);
 
		
		System.out.println("Sortie de searchResultFromXml : "+tagName);
		
		return data;
	}
	
	/**
	 * 
	 * Appelle le serviceMesh
	 * 
	 * @param tagName
	 * @return
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 * @throws XMLStreamException 
	 * @throws FileNotFoundException 
	 */
	private List<TagMesh> searchResultFromBase(String tagName) throws XPathParseException, XPathEvalException, NavException, FileNotFoundException, XMLStreamException {
		
		data = new ArrayList<TagMesh>();
		
		//On ne passe pas le request, l'initialisation a déjà été effectuée dans AccueilImportContr
		ServiceMeshCrawler serviceMeshCrawler = ServiceMeshCrawler.getInstance(null);
		 
		List<IndexMesh> indexes = serviceMeshCrawler.getListTagJsonFromBase(tagName);
 
		// iterate a list and filter by tagName
		for (IndexMesh index : indexes) {
				data.add(new TagMesh(index.getIdTag(), index.getNom()));
		}

		System.out.println("Sortie de searchResultFromBase : "+tagName);
		
		return data;
	}



}
