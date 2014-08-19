/**
 * 
 */
package cnam.medimage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import cnam.medimage.bean.TagMesh;

import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

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
	 * Fichier Mesh ouvert
	 */
	private static File mesh;
	
	/**
	 * Chemin du fichier mesh
	 */
	private String meshPath;
	
	
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
			
			meshPath = meshFilePath;
					
			mesh = new File(meshFilePath);
			
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
		return mesh != null;
	}
	
	
	
	
	
	
	
	
	public TagMesh getDescriptorUI(String saisie) throws XPathParseException, XPathEvalException, NavException{
		
		System.out.println("Entrée dans le Crawler");
		
		TagMesh tag = null;
		String idTag = null;
		String tagName = null;
		
		VTDGen vg = new VTDGen();
	    AutoPilot ap = new AutoPilot();
	    int i;
	
	    ap.selectXPath("DescriptorRecord[DescriptorUI = '"+saisie+"']");
	      
          if (vg.parseFile(meshPath, true)  ){
        	  
              VTDNav vn = vg.getNav();
              
              ap.bind(vn); // apply XPath to the VTDNav instance, you can associate ap to different vns
              // AutoPilot moves the cursor for you, as it returns the index value of the evaluated node
              while((i=ap.evalXPath())!=-1){                     	
                  
                  //on descend au prochain DescriptorUI
                  if (vn.toElement(VTDNav.FIRST_CHILD, "DescriptorUI"))
                  {
                      int j = vn.getText();
                      if (j != -1) idTag = vn.toString(j);
                      vn.toElement(VTDNav.PARENT);
                  }    
                  //on descend au prochain DescriptorName
                  if (vn.toElement(VTDNav.FIRST_CHILD, "DescriptorName"))
                  {                	  
                	  if (vn.toElement(VTDNav.FIRST_CHILD, "String"))
                      {
                          int j = vn.getText();
                          if (j != -1) tagName = vn.toString(j);
                          vn.toElement(VTDNav.PARENT);
                      }
                      vn.toElement(VTDNav.PARENT);
                  }
                  
              }
          }

         tag = new TagMesh(idTag, tagName);
         
         System.out.println(idTag+"/"+tagName);
          
  		System.out.println("Sortie du Crawler");
  		
		return tag;
		
		
	}
	
	
	private TagMesh tagBuilder(XMLEventReader xmler) {
		
		TagMesh tag = new TagMesh(null,null);
		
		
		
		
		
		
		return tag;
		
	}
	
	

}
