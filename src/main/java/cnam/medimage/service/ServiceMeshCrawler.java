/**
 * 
 */
package cnam.medimage.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import cnam.medimage.bean.TagMesh;
import cnam.medimage.service.xmlEvent.StartDescriptorFilter;

/**
 * @author Jullien
 *
 */
public class ServiceMeshCrawler extends Service {
	
	/**
	* Attribut singleton qui permet de n'avoir qu'une seule instance de la classe Service
	* grâce à un appel unique du constructeur
	* 
	*/
	protected static ServiceMeshCrawler singleton;	
	
	static {
			singleton = new ServiceMeshCrawler();		
	}
	
	
	/**
	 * Le XMLInputFactory qui permettra de parser le fichier Mesh
	 * Il est statique est protégéafin d'assurer l'ouverture du fichier Mesh une fois pour toute
	 * et de s'assurer de sa disponibilité.
	 */
	private static XMLInputFactory xmlif;
	
	/**
	 * Le Event Reader ouvert sur le fichier Mesh
	 */
	private static XMLEventReader  xmler;

	/**
	 * Fichier Mesh ouvert
	 */
	private static File mesh;
	
	
	/**
	* Méthode permettant d'accéder à l'unique instance de la classe Service
	* 
	* @return l'instance de la classe Service
	*/
	public static ServiceMeshCrawler getInstance() {
		
			
		return (ServiceMeshCrawler) singleton;
	}

	/**
	 * 
	 * Crée la Factory et lui donne le fichier MeSH 2014 à digérer
	 * 
	 */
	public void init(HttpServletRequest request, boolean force) throws XMLStreamException, FileNotFoundException {
				
		
		//On va chercher le fichier Mesh
		if(mesh == null || force){		

			HttpSession session =  request.getSession(false);
			ServletContext context = session.getServletContext();
			
			String meshFilePath = context.getInitParameter("MESH_FILEPATH");
			meshFilePath = context.getRealPath(meshFilePath);
					
			mesh = new File(meshFilePath);
			
		}
		
		//Si l'input Factory est null on l'instancie
		if(xmlif == null || force){		
			xmlif = XMLInputFactory.newInstance();
			xmlif.setProperty("javax.xml.stream.isCoalescing",Boolean.TRUE);
			xmlif.setProperty("javax.xml.stream.isReplacingEntityReferences", Boolean.TRUE);
			
		}

		//Si le streamReader n'a pas encore été ouvert vers le fichier Mesh
		if(xmler == null || force){				
			xmler = xmlif.createXMLEventReader(new FileInputStream(mesh),"UTF-8");
		}
		
		if(isInit()){
			System.out.println("Initialisation ServiceMeshCrawler() OK !");
		}else{
			System.out.println("Initialisation ServiceMeshCrawler() NOK !");
		}
		
	}
	
	
	/**
	 * 
	 * Contrôle l'initialisation de la classe de service avec le fichier Mesh
	 * 
	 * @return
	 */
	private boolean isInit() {
		return mesh != null && xmlif != null && xmler != null;
	}
	
	
	
	
	
	
	
	
	public List<TagMesh> getMotClef(String saisie) throws XMLStreamException, FileNotFoundException{
		
		System.out.println("Entrée dans le Crawler");
		
		List<TagMesh> tags = new ArrayList<TagMesh>() ;
		xmler = xmlif.createXMLEventReader(new FileInputStream(mesh),"UTF-8");
		
		XMLEventReader reader = xmlif.createFilteredReader(xmler, new StartDescriptorFilter());
		
		int compteur = 0;
		
		StartElement event;
		Stack<XMLEvent> tagStack = new Stack();
		
		while (reader.hasNext() && compteur < 2000) {

			event = (StartElement)reader.nextEvent();	
			
			
			if (event.isCharacters()) {						
				if (!event.asCharacters().isWhiteSpace()) {
					System.out.println("\t>" + event.asCharacters().getData());
				}					
			}
		    
		    compteur++;
		    
		}
		
		System.out.println("Parcours du fichier fini "+compteur);
		
		return null;
		
	}
	
	
	private TagMesh tagBuilder(XMLEventReader xmler) {
		
		TagMesh tag = new TagMesh(null,null);
		
		
		
		
		
		
		return tag;
		
	}
	
	

}
