/**
 * 
 */
package cnam.medimage.model.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
		try {
			singleton = new ServiceMeshCrawler();
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Le XMLInputFactory qui permettra de parser le fichier Mesh
	 * Il est statique est protégé afin d'assurer l'ouverture du fichier Mesh une fois pour toute
	 * et de s'assurer de sa disponibilité.
	 */
	protected static XMLInputFactory xmlif;
	
	/**
	 * Le Stream Reader ouvert sur le fichier Mesh
	 */
	protected static XMLStreamReader xmlsr;

	/**
	 * Chemin du fichier Mesh
	 */
	static File mesh;
	
	
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
	public ServiceMeshCrawler() throws XMLStreamException, FileNotFoundException {
		
		//Initialisation de la classe Service
		super();
		
		//Si l'input Factory est null on l'instancie
		if(xmlif == null){
			xmlif = XMLInputFactory.newInstance();
			xmlif.setProperty("javax.xml.stream.isCoalescing",Boolean.TRUE);
			xmlif.setProperty("javax.xml.stream.isReplacingEntityReferences", Boolean.TRUE);
		}

		//Si le streamReader n'a pas encore été ouvert vers le fichier Mesh
		if(xmlsr == null){			
			xmlsr = xmlif.createXMLStreamReader(new FileReader(mesh));
		}
		
		
	}
	
	
	

}
