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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cnam.medimage.bean.Dicom;
import cnam.medimage.bean.ImportForm;
import cnam.medimage.bean.UploadedFile;
import cnam.medimage.repository.DicomRepository;
import cnam.medimage.repository.ExamenRepository;

import cnam.medimage.repository.MetadataRepository;
import cnam.medimage.repository.UsageRepository;
import cnam.medimage.service.ServiceMeshCrawler;

@org.springframework.stereotype.Controller
public class AccueilImportContr implements Controller{
	
	private String dest_Path;
	private String dir_name;
	private String current_filename;
	private UUID id_user;
	private ImportForm importForm;

	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody
	List<UploadedFile> upload(MultipartHttpServletRequest request,
	HttpServletResponse response, @ModelAttribute ImportForm form) throws IOException {
		importForm = form;
		//on enregistre le contexte pour enregistrer plus loin les fichiers
		dest_Path = request.getSession().getServletContext().getRealPath("/") +  "fichiers/";
		//on récupère les fichiers soumis pour les enregistrer
		Map<String, MultipartFile> fileMap = request.getFileMap();
		System.out.println("imagePublique : " + importForm.isPublique());
		String user = "user011";
		id_user = UUID.randomUUID();
		importForm.getExamen().setId_examen(UUID.randomUUID());
		importForm.getExamen().setId_user(id_user);
		importForm.setUsage((String) request.getParameter("usage"));
		importForm.getExamen().setNom_examen((String) request.getParameter("examen"));
		if(fileMap.size() > 1){
			this.dir_name = user + "_" + importForm.getUsage() + "_" + importForm.getExamen().getNom_examen();
			boolean success = (new File(this.dest_Path + this.dir_name)).mkdirs();
			if (!success) {
				System.out.println("Erreur création dossier");
				//A coder Exception   
			}
			this.dir_name = "/" + this.dir_name + "/";
		}else
			this.dir_name = "/" + user + "_" + importForm.getUsage() + "_" + importForm.getExamen().getNom_examen() + "_";
		
		//Maintain a list to send back the files info. to the client side
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
		
		for (MultipartFile multipartFile : fileMap.values()) {
			// Sauvegarde physique
			this.current_filename = multipartFile.getOriginalFilename();
			sauvegardeFichier(multipartFile);
			UploadedFile fileInfo = getUploadedFileInfo(multipartFile);
			
			// Sauvegarde en base
			sauvegardeEnBase();
			
			// adding the file info to the list
			uploadedFiles.add(fileInfo);
		}
		
		ExamenRepository examRepo = new ExamenRepository();
		examRepo.save(importForm.getExamen());
		return uploadedFiles;
	}
	
	@RequestMapping(value="/import")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Je suis dans le contrôleur de l'import");
		
		/* Initialisation de l'instance de ServiceMesh */
		ServiceMeshCrawler serviceMeshCrawler = ServiceMeshCrawler.getInstance(request);

		
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
		ImportForm form = new ImportForm();
		ModelAndView mv = new ModelAndView("accueilImport");
		mv.addObject("form", form);
		return mv;
	}
	
	public void listMetaInfo(DicomObject object, Dicom dicom) {
	   Iterator<DicomElement> iter = object.fileMetaInfoIterator();
	   while(iter.hasNext()) {
	      DicomElement element = (DicomElement) iter.next();
	      int tag = element.tag();
	      try {
	         String tagAddr = TagUtils.toString(tag);
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
		      //Si on a terminé de lire l'en tête en qu'on
		      //passe à la partie binaire du fichier
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
	
	private void sauvegardeFichier(MultipartFile multipartFile)
	throws IOException, FileNotFoundException {
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(getOutputFilename()));
	}
	
	private void sauvegardeEnBase() {
		
		DicomInputStream dicomInput = null;
		File fichierDicom = new File(getOutputFilename());
		DicomRepository dicoRepo = new DicomRepository();		
		try {
		    dicomInput = new DicomInputStream(fichierDicom);
		    Dicom dicom = new Dicom();
		    //Données de base
		    dicom.setId_dicom(UUID.randomUUID());
		    dicom.setDate_import(new Date());
		    dicom.setFile_path(this.dir_name + this.current_filename);
		    dicom.setId_user(this.id_user);

		    dicom.setPublique(importForm.isPublique());
		    dicom.setId_examen(importForm.getExamen().getId_examen());
		    dicom.setNom_examen(importForm.getExamen().getNom_examen());
		    //récupération des métadonnées
		    listMetaInfo(dicomInput.readFileMetaInformation(), dicom);
		    listHeader(dicomInput.readDicomObject(), dicom);
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
