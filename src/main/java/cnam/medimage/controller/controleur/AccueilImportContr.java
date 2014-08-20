package cnam.medimage.controller.controleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;
import org.dcm4che2.util.TagUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cnam.medimage.bean.UploadedFile;
import cnam.medimage.service.CassandraConnection;

import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;

@org.springframework.stereotype.Controller
public class AccueilImportContr implements Controller{
	
	private String dest_Path;
	private String dir_name;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody
	List<UploadedFile> upload(MultipartHttpServletRequest request,
	HttpServletResponse response) throws IOException {
		//on enregistre le contexte pour enregistrer plus loin les fichiers
		dest_Path = request.getSession().getServletContext().getRealPath("/") +  "fichiers/";
		// Getting uploaded files from the request object
		Map<String, MultipartFile> fileMap = request.getFileMap();
		String user = "user011";
		String usage = (String) request.getParameter("usage");
		String examen = (String) request.getParameter("examen");
		if(fileMap.size() > 1){
			this.dir_name = user + "_" + usage + "_" + examen;
			boolean success = (new File(this.dest_Path + this.dir_name)).mkdirs();
			if (!success) {
				System.out.println("Erreur création dossier");
			    
			}else{
				System.out.println("Nouveau dossier = " + this.dir_name);
				//A coder Exception
			}
			this.dir_name = "/" + this.dir_name + "/";
		}else
			this.dir_name = "/" + user + "_" + usage + "_" + examen + "_";
		System.out.println("usage : " + usage);
		System.out.println("examen : " + examen);
		
		// Maintain a list to send back the files info. to the client side
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
		// Iterate through the map
		for (MultipartFile multipartFile : fileMap.values()) {
			// Save the file to local disk
			
			sauvegardeFichier(multipartFile);
			UploadedFile fileInfo = getUploadedFileInfo(multipartFile);
			
			// Save the file info to database
			//fileInfo = saveFileToDatabase(fileInfo);
			
			// adding the file info to the list
			uploadedFiles.add(fileInfo);
		}
	   System.out.println("/////////////////" + 
		   			  "Chargééés!" + 
		   			  "/////////////////");
		return uploadedFiles;
	}
	
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
		/*System.out.println("/////////////////" + 
	   			  "TEST CASSANDRA" + 
	   			  "/////////////////");*/
		CassandraConnection.getInstance();
		Metadata metadata = CassandraConnection.getCluster().getMetadata();
		System.out.printf("Connected to cluster: %s\n", 
		metadata.getClusterName());
		for ( Host host : metadata.getAllHosts() ) {
			System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
			host.getDatacenter(), host.getAddress(), host.getRack());
		}
		return new ModelAndView("accueilImport");
	}
	
	public void listMetaInfo(DicomObject object) {
	   /*System.out.println("/////////////////" + 
			   			  "META INFORMATION" + 
			   			  "/////////////////");*/
	   Iterator<DicomElement> iter = object.fileMetaInfoIterator();
	   while(iter.hasNext()) {
	      DicomElement element = (DicomElement) iter.next();
	      int tag = element.tag();
	      try {
	         String tagName = object.nameOf(tag);
	         String tagAddr = TagUtils.toString(tag);
	         //System.out.println("addr = " + tagAddr);
	         String tagVR = object.vrOf(tag).toString();
	         if (tagVR.equals("SQ")) {
	            if (element.hasItems()) {
	               //System.out.println(tagAddr +" ["+  tagVR +"] "+ tagName);
	               listMetaInfo(element.getDicomObject());
	               continue;
	            }
	         }    
	         String tagValue = object.getString(tag);
	         //System.out.println(tagAddr +" ["+ tagVR +"] "+ tagName +" ["+ tagValue+"]");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }  
	}
	
	public void listHeader(DicomObject object) {
		   /*System.out.println("/////////////////" + 
		   			  "DATASET" + 
		   			  "/////////////////");*/
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
		               //System.out.println(tagAddr +" ["+  tagVR +"] "+ tagName);
		               listHeader(element.getDicomObject());
		               continue;
		            }
		         }       	 
		         String tagValue = object.getString(tag);
		         //System.out.println(tagAddr +" ["+ tagVR +"] "+ tagName +" ["+ tagValue+"]");
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }  
		}
	
	
/**********************************************************************/
	

	
	/*@RequestMapping(value = { "/list" })
	public String listBooks(Map<String, Object> map) {
	map.put("fileList", uploadService.listFiles());
	
	// will be resolved to /views/listFiles.jsp
	return "/listFiles";
	}*/
	
	/*@RequestMapping(value = "/get/{fileId}", method = RequestMethod.GET)
	public void getFile(HttpServletResponse response, @PathVariable Long fileId) {
	
		UploadedFile dataFile = uploadService.getFile(fileId);
		
		File file = new File(dataFile.getLocation(), dataFile.getName());
		
		try {
		response.setContentType(dataFile.getType());
		response.setHeader("Content-disposition", "attachment; filename=\""
		                           + dataFile.getName() + "\"");
		
		FileCopyUtils.copy(FileUtils.readFileToByteArray(file),response.getOutputStream());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	private void sauvegardeFichier(MultipartFile multipartFile)
	throws IOException, FileNotFoundException {
	
		String outputFileName = getOutputFilename(multipartFile);
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
	}
	
	/*private UploadedFile saveFileToDatabase(UploadedFile uploadedFile) {
	
	return uploadService.saveFile(uploadedFile);
	
	}*/
	
	private String getOutputFilename(MultipartFile multipartFile) {
	
		return this.dest_Path + this.dir_name +multipartFile.getOriginalFilename();
	}
	
	private UploadedFile getUploadedFileInfo(MultipartFile multipartFile)throws IOException {
	
		UploadedFile fileInfo = new UploadedFile();
		fileInfo.setName(multipartFile.getOriginalFilename());
		fileInfo.setSize(multipartFile.getSize());
		fileInfo.setType(multipartFile.getContentType());
		fileInfo.setLocation(this.dest_Path);
		return fileInfo;
	}
}

	
