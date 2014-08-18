package cnam.medimage.controller.controleur;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;
import org.dcm4che2.util.TagUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;

@org.springframework.stereotype.Controller
public class AccueilImportContr implements Controller{

	@RequestMapping(value="/import")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("Je suis dans le contrôleur de l'import");
		
		ServletContext context = request.getSession().getServletContext();
		String pathFile = context.getRealPath("/resources/dcmSamples/DEF_VEINEUX_107205/IM-0001-0001.dcm");
		DicomInputStream din = null;

		FileInputStream file = new FileInputStream(pathFile);
		System.out.println("file = " + file);
		try {

		    din = new DicomInputStream(file);
		    listMetaInfo(din.readFileMetaInformation());
		    listHeader(din.readDicomObject());
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		finally {
		    try {
		        din.close();
		    }
		    catch (IOException ignore) {
		    }
		}
		System.out.println("/////////////////" + 
	   			  "TEST CASSANDRA" + 
	   			  "/////////////////");
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		Metadata metadata = cluster.getMetadata();
		System.out.printf("Connected to cluster: %s\n", 
		metadata.getClusterName());
		for ( Host host : metadata.getAllHosts() ) {
			System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
			host.getDatacenter(), host.getAddress(), host.getRack());
		}
		return new ModelAndView("accueilImport");
	}
	
	public void listMetaInfo(DicomObject object) {
	   System.out.println("/////////////////" + 
			   			  "META INFORMATION" + 
			   			  "/////////////////");
	   Iterator<DicomElement> iter = object.fileMetaInfoIterator();
	   while(iter.hasNext()) {
	      DicomElement element = (DicomElement) iter.next();
	      int tag = element.tag();
	      try {
	         String tagName = object.nameOf(tag);
	         String tagAddr = TagUtils.toString(tag);
	         System.out.println("addr = " + tagAddr);
	         String tagVR = object.vrOf(tag).toString();
	         if (tagVR.equals("SQ")) {
	            if (element.hasItems()) {
	               System.out.println(tagAddr +" ["+  tagVR +"] "+ tagName);
	               listMetaInfo(element.getDicomObject());
	               continue;
	            }
	         }    
	         String tagValue = object.getString(tag);
	         System.out.println(tagAddr +" ["+ tagVR +"] "+ tagName +" ["+ tagValue+"]");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }  
	}
	
	public void listHeader(DicomObject object) {
		   System.out.println("/////////////////" + 
		   			  "DATASET" + 
		   			  "/////////////////");
		   Iterator<DicomElement> iter = object.datasetIterator();
		   while(iter.hasNext()) {
		      DicomElement element = (DicomElement) iter.next();
		      int tag = element.tag();
		      if(tag == Tag.PixelData)
		    	  break;
		      try {
		         String tagName = object.nameOf(tag);
		         String tagAddr = TagUtils.toString(tag);
		         String tagVR = object.vrOf(tag).toString();
		         if (tagVR.equals("SQ")) {
		            if (element.hasItems()) {
		               System.out.println(tagAddr +" ["+  tagVR +"] "+ tagName);
		               listHeader(element.getDicomObject());
		               continue;
		            }
		         }       	 
		         String tagValue = object.getString(tag);
		         System.out.println(tagAddr +" ["+ tagVR +"] "+ tagName +" ["+ tagValue+"]");
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }  
		}

}
