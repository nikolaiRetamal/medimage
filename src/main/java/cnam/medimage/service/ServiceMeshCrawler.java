/**
 * 
 */
package cnam.medimage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
		String tagName = null;
		String idTag = null;
		VTDGen vg = new VTDGen();
	    AutoPilot ap = new AutoPilot();
	    int i;
	
	    //Xpath de détection d'un DescriptorUI précis
	    ap.selectXPath("DescriptorRecord[DescriptorUI = '"+saisie+"']");
	      
          if (vg.parseFile(meshPath, true)  ){
        	  
              VTDNav vn = vg.getNav();
              
              ap.bind(vn); // apply XPath to the VTDNav instance, you can associate ap to different vns
              // AutoPilot moves the cursor for you, as it returns the index value of the evaluated node
              while((i=ap.evalXPath())!=-1){                	  
            	  tag = tagBuilder(vn);           	       
            	  
            	}          	 
            	
          }

         
          if(tag != null){
        	  System.out.println("Tag : "+tag.getIdTag()+" / "+tag.getNom());
        	  if(tag.getSynonymes() != null){
        		  for(String s : tag.getSynonymes())System.out.println(s);
        	  }else{
            	  System.out.println("Synonymes null...");        		  
        	  }
        		  
          }else{
        	  System.out.println("Tag est null...");
          }
          
          
  		System.out.println("Sortie du Crawler");
  		
		return tag;
		
           
                  
		
	}
	
	/**
	 * @throws NavException 
	 * @throws XPathEvalException 
	 * 
	 */
	private TagMesh tagBuilder(VTDNav vn) throws XPathParseException, NavException, XPathEvalException {
		
		TagMesh tag = new TagMesh(null,null);
		String idTag = null;
		String tagName = null;
		ArrayList<String> synonymes = new ArrayList<String>(); 
		ArrayList<String> hierarchies = new ArrayList<String>();
		int i,j;
		
		
		VTDNav vnClone = vn.cloneNav();
		

		//Xpath du DescriptorUI
	    //AutoPilot ap = new AutoPilot();
        //ap.selectXPath("DescriptorUI");
		//Xpath du DescriptorName
	   // AutoPilot ap2 = new AutoPilot();
       // ap2.selectXPath("DescriptorName");
		//Xpath de détection de la liste des termes/synonymes
	    AutoPilot ap3 = new AutoPilot();
        ap3.selectXPath("ConceptList/Concept/TermList/Term/String");
               
       
        //On remonte au DescriptorRecord
        if(vnClone.toElement(VTDNav.PARENT,"Descriptor")){ 
        	
       	  
            //on descend au prochain DescriptorUI
            if (vnClone.toElement(VTDNav.FIRST_CHILD, "DescriptorUI"))
            {
                j = vnClone.getText();
                if (j != -1) idTag = vnClone.toString(j);
                vnClone.toElement(VTDNav.PARENT);
            }    
            //on descend au prochain DescriptorName
            if (vnClone.toElement(VTDNav.FIRST_CHILD, "DescriptorName"))
            {                	  
          	  if (vnClone.toElement(VTDNav.FIRST_CHILD, "String"))
                {
                    j = vnClone.getText();
                    if (j != -1) tagName = vnClone.toString(j);
                    vnClone.toElement(VTDNav.PARENT);
                }
          	vnClone.toElement(VTDNav.PARENT);
            }  
		        
		        //Récupération des synonymes
		        ap3.bind(vnClone);
		        while(ap3.evalXPath()!=-1){
		        	
		            j = vnClone.getText();
	                if (j != -1) synonymes.add(vnClone.toString(j));
		        	
		        }

		        vnClone.toElement(VTDNav.PARENT);  
		        
        }else{
        	
        	throw new XPathParseException("Le TagBuilder n'est pas dans un DescriptorRecord.");
        	
        }		
        
        tag.setIdTag(idTag);
        tag.setNom(tagName);
        tag.setSynonymes(synonymes);
        
		
		return tag;
		
	}
	
	

}
