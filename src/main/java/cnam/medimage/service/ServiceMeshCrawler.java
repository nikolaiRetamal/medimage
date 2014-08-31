/**
 * 
 */
package cnam.medimage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * Le VTDGen en train de parser le fichier
	 */
	private VTDGen vg;
	
	
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
				

		if(!isInit() || force){		

			HttpSession session =  request.getSession(false);
			ServletContext context = session.getServletContext();

			//On va chercher le fichier Mesh
			String meshFilePath = context.getInitParameter("MESH_FILEPATH");
			meshFilePath = context.getRealPath(meshFilePath);
			
			//Sauvegarde du chemin et du fichier Mesh
			meshPath = meshFilePath;					
			mesh = new File(meshFilePath);
			
			//Initialisation du parser
			vg = new VTDGen();
			vg.parseFile(meshPath, true) ;
			
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
	
	
	
	
	
	
	
	/**
	 * 
	 * Ramène un TagMesh à partir de son DescriptorUI
	 * 
	 * @param saisie
	 * @return
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 */
	public TagMesh getDescriptorUI(String saisie) throws XPathParseException, XPathEvalException, NavException{
		
		System.out.println("Entrée dans le Crawler");
		
		TagMesh tag = null;
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
            	  tag = tagBuilder(vn,i);   
            	}     
              
          } 
              
              
          if(tag != null){
        	  System.out.println("Tag : "+tag.getIdTag()+" / "+tag.getNom());
        	  if(tag.getSynonymes() != null){
        		  for(String s : tag.getSynonymes())System.out.println(s);
        	  }else{
            	  System.out.println("Synonymes null...");        		  
        	  } 
        	  if(tag.getCategories() != null){
        		  for(String s : tag.getCategories())System.out.println(s);
        	  }else{
            	  System.out.println("Categories null...");        		  
        	  }
        		  
          }else{
        	  System.out.println("Tag est null...");
          }
          
          
  		System.out.println("Sortie du Crawler");
  		
		return tag;        
		
	}
	
	
	/**
	 * 
	 * Ramène tous les TagMesh du fichier XML
	 * 
	 * @param saisie
	 * @return
	 * @throws XPathParseException
	 * @throws XPathEvalException
	 * @throws NavException
	 */
	public ArrayList<TagMesh> parseThemAll() throws XPathParseException, XPathEvalException, NavException{
		
		 ArrayList<TagMesh> listeMesh = new ArrayList<TagMesh>();
		 
	     AutoPilot ap = new AutoPilot();
	     int i;
	
	    //Xpath de détection de tous les DescriptorRecord
	    ap.selectXPath("DescriptorRecord");
	   
        	  
	      VTDNav vn = vg.getNav();
	      
	      ap.bind(vn); // apply XPath to the VTDNav instance, you can associate ap to different vns
	      // AutoPilot moves the cursor for you, as it returns the index value of the evaluated node
	      while((i=ap.evalXPath())!=-1){  
	    		try{
	          	  listeMesh.add(tagBuilder(vn,i)); 
				}catch(Exception e){
					System.out.println("Erreur de parsing...");
				}  
	    	}     
		 
		 return listeMesh;
		
	}
	
	
	
	public ArrayList<String> getListTagJson(String query) throws XPathParseException, XPathEvalException, NavException   {
		
		System.out.println("Entrée dans getListTagJson()");
	
		ArrayList<String> reponseList = new ArrayList<String> ();
		
	    AutoPilot ap = new AutoPilot();
	    int i,j;
	
	    //Xpath de détection d'un DescriptorUI précis
	    ap.selectXPath("DescriptorRecord/DescriptorName[starts-with(String,'"+query+"')]");
	    
	    VTDNav vn = vg.getNav();
        
        ap.bind(vn); // apply XPath to the VTDNav instance, you can associate ap to different vns
        // AutoPilot moves the cursor for you, as it returns the index value of the evaluated node
        while((i=ap.evalXPath())!=-1){          
        	
        	System.out.println(vn.toString(i));
        	
        	TagMesh tag = tagBuilder(vn, i);
        	reponseList.add(tag.getNom());
            
        }     
        

		System.out.println("Sortie de getListTagJson()");
	      
		return reponseList;
		
		
	}
	
	
	/**
	 * @throws NavException 
	 * @throws XPathEvalException 
	 * 
	 */
	private TagMesh tagBuilder(VTDNav vn,int xPathEval) throws XPathParseException, NavException, XPathEvalException {
		
		TagMesh tag = new TagMesh(null,null);
		String idTag = null;
		String tagName = null;
		ArrayList<String> synonymes = new ArrayList<String>(); 
		ArrayList<String> categories = new ArrayList<String>();
		int i,j;
		
		
		VTDNav vnClone = vn.cloneNav();		
		
		//Xpath du DescriptorUI
	    AutoPilot ap = new AutoPilot();
        ap.selectXPath("DescriptorUI");
		//Xpath du DescriptorName
	    AutoPilot ap2 = new AutoPilot();
        ap2.selectXPath("DescriptorName/String");
		//Xpath de détection de la liste des termes/synonymes
	    AutoPilot ap3 = new AutoPilot();
        ap3.selectXPath("ConceptList/Concept/TermList/Term/String");
		//Xpath de détection de la liste des categories
	    AutoPilot ap4 = new AutoPilot();
        ap4.selectXPath("TreeNumberList/TreeNumber");
       
        //On remonte au DescriptorRecord
        if("DescriptorRecord".equals(vnClone.toString(xPathEval))){         	
       	  
            //on descend au prochain DescriptorUI
        	ap.bind(vnClone);
        	while((i=ap.evalXPath())!=-1){ 
            
        		 j = vnClone.getText();
                 if (j != -1) idTag = vnClone.toString(j);
            	
            }    
            
            //on descend au prochain DescriptorName
        	ap2.bind(vnClone);
            while((i=ap2.evalXPath())!=-1){ 
                            	  
            	 j = vnClone.getText();
                 if (j != -1)  tagName = vnClone.toString(j).split("\\[")[0];
          	  
            }  
		        
	        //Récupération des synonymes
	        ap3.bind(vnClone);
	        while(ap3.evalXPath()!=-1){
	        	
	            j = vnClone.getText();
                if (j != -1) synonymes.add(vnClone.toString(j));
	        	
	        }
	        
	        
	        //Récupération de la categories
	        ap4.bind(vnClone);
	        while(ap4.evalXPath()!=-1){
	        	
	            j = vnClone.getText();
                if (j != -1) categories.add(vnClone.toString(j));
	        	
	        }
		        
        }else{
        	
        	throw new XPathParseException("Le TagBuilder n'est pas dans un DescriptorRecord.");
        	
        }		
        
        tag.setIdTag(idTag);
        tag.setNom(tagName);
        tag.setSynonymes(synonymes);
        tag.setCategories(categories);
        
		
		return tag;
		
	}
	
	

}
