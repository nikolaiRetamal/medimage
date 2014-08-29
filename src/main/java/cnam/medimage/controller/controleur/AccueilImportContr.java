package cnam.medimage.controller.controleur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.UploadedFile;
import cnam.medimage.repository.DicomRepository;

@org.springframework.stereotype.Controller
public class AccueilImportContr implements Controller{
	
	private String dest_Path;
	private String dir_name;
	private String current_filename;
	private String usage;
	private String examen;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody
	List<UploadedFile> upload(MultipartHttpServletRequest request,
	HttpServletResponse response) throws IOException {
		//on enregistre le contexte pour enregistrer plus loin les fichiers
		dest_Path = request.getSession().getServletContext().getRealPath("/") +  "fichiers/";
		// Getting uploaded files from the request object
		Map<String, MultipartFile> fileMap = request.getFileMap();
		String user = "user011";
		this.usage = (String) request.getParameter("usage");
		this.examen = (String) request.getParameter("examen");
		if(fileMap.size() > 1){
			this.dir_name = user + "_" + usage + "_" + examen;
			boolean success = (new File(this.dest_Path + this.dir_name)).mkdirs();
			if (!success) {
				System.out.println("Erreur création dossier");
				//A coder Exception   
			}
			this.dir_name = "/" + this.dir_name + "/";
		}else
			this.dir_name = "/" + user + "_" + usage + "_" + examen + "_";
		System.out.println("usage : " + this.usage);
		System.out.println("examen : " + this.examen);
		
		//Maintain a list to send back the files info. to the client side
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
		
		for (MultipartFile multipartFile : fileMap.values()) {
			// Save the file to local disk
			this.current_filename = multipartFile.getOriginalFilename();
			sauvegardeFichier(multipartFile);
			UploadedFile fileInfo = getUploadedFileInfo(multipartFile);
			
			// Save the file info to database
			fileInfo = saveFileToDatabase(fileInfo);
			
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
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
		return new ModelAndView("accueilImport");
	}
	
	public void listMetaInfo(DicomObject object, Dicom dicom) {
	   /*System.out.println("/////////////////" + 
			   			  "META INFORMATION" + 
			   			  "/////////////////");*/
	   Iterator<DicomElement> iter = object.fileMetaInfoIterator();
	   while(iter.hasNext()) {
	      DicomElement element = (DicomElement) iter.next();
	      int tag = element.tag();
	      try {
	         String tagAddr = TagUtils.toString(tag);
	         System.out.println("addr = " + tagAddr);
	         String tagVR = object.vrOf(tag).toString();
	         if (tagVR.equals("SQ")) {
	            if (element.hasItems()) {
	               listMetaInfo(element.getDicomObject(), dicom);
	               continue;
	            }
	         }    
	         String tagValue = object.getString(tag);
	         dicom.getMetadatas().put(tagAddr, tagValue);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }  
	}
	
	public void listHeader(DicomObject object, Dicom dicom) {
		   Iterator<DicomElement> iter = object.datasetIterator();
		   while(iter.hasNext()) {
		      DicomElement element = (DicomElement) iter.next();
		      int tag = element.tag();
		      if(tag == Tag.PixelData)
		    	  break;
		      try {
		         String tagAddr = TagUtils.toString(tag);
		         String tagVR = object.vrOf(tag).toString();
		         if (tagVR.equals("SQ")) {
		            if (element.hasItems()) {
		               listHeader(element.getDicomObject(), dicom);
		               continue;
		            }
		         }       	 
		         String tagValue = object.getString(tag);
		         dicom.getMetadatas().put(tagAddr, tagValue);
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }  
		}
	
	
/**********************************************************************/
	

	
	
	private void sauvegardeFichier(MultipartFile multipartFile)
	throws IOException, FileNotFoundException {
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(getOutputFilename()));
	}
	
	private UploadedFile saveFileToDatabase(UploadedFile uploadedFile) {
		
		DicomInputStream dicomInput = null;
		File fichierDicom = new File(getOutputFilename());
		DicomRepository dicoRepo = new DicomRepository();
		try {
		    dicomInput = new DicomInputStream(fichierDicom);
		    Dicom dicom = new Dicom();
		    dicom.setId_dicom(UUID.randomUUID());
		    dicom.setDate_import(new Date());
		    listMetaInfo(dicomInput.readFileMetaInformation(), dicom);
		    listHeader(dicomInput.readDicomObject(), dicom);
		    Iterator it = dicom.getMetadatas().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		    }
		    dicoRepo.save(dicom);
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		finally {
		    try {
		    	dicomInput.close();
		    }
		    catch (IOException ignore) {
		    }
		}
		return null;
	
	}
	
	private String getOutputFilename() {
		return this.dest_Path + this.dir_name + this.current_filename;
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

	
