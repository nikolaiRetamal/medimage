package cnam.medimage.controller.controleur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cnam.medimage.bean.Dicom;
import cnam.medimage.repository.ExamenDicomRepository;

@Controller
public class TelechargerExamContr {

	private ArrayList<String> listeFichiers;
	private String pathFichierSortie;
	private String nomFichierSortie;
	private String dossier;
	private String realPath;
	
	TelechargerExamContr(){
		listeFichiers = new ArrayList<String>();
    }
	
	@RequestMapping(value = "/telechargerExamen", method = RequestMethod.GET)
	public void getFile(@RequestParam("id_examen") String id_examen, 
	HttpServletResponse response, HttpServletRequest request) {
		try {
			//on Trouve la liste de dicoms pour l'examen
	        ExamenDicomRepository examDicomRepo = new ExamenDicomRepository();
	        List<Dicom> dicoms = examDicomRepo.getListeDicoms(UUID.fromString(id_examen));
	        System.out.println("dicom path = " + dicoms.get(0).getFile_path());
	        realPath = request.getSession().getServletContext().getRealPath("/") +  "fichiers";
	        dossier = dicoms.get(0).getFile_path();
	        dossier = dossier.replace("/"+dicoms.get(0).getNom(), "");
	        System.out.println("dir path = " + dossier);
	        nomFichierSortie =  dicoms.get(0).getNom_examen() + ".zip";
	        System.out.println("nomFichierSortie = " + nomFichierSortie);
	        pathFichierSortie = realPath + dossier + "/" + nomFichierSortie;
	        pathFichierSortie = pathFichierSortie.replace("/", "\\");
	        System.out.println("pathFichierSortie = " + pathFichierSortie);
	        for(Dicom dicom : dicoms){
	        	
	        	String pathFichierDicom = realPath + dicom.getFile_path();
	        	pathFichierDicom = pathFichierDicom.replace("/", "\\");
	        	listeFichiers.add(pathFichierDicom);
	        	System.out.println("pathFichierDicom = " + pathFichierDicom);
	        }
	        this.compresser(pathFichierSortie);
	        System.out.println("Je vais ouvrir le fichier " + nomFichierSortie);
			File fichier = new File(pathFichierSortie);
			response.setHeader("Content-Disposition", "attachment; filename=" + nomFichierSortie);
			FileInputStream fis = new FileInputStream(fichier);
			// copy it to response's OutputStream
			org.apache.commons.io.IOUtils.copy(fis, response.getOutputStream());
			response.flushBuffer();
			fis.close();
			if(fichier.delete()){
    			System.out.println(fichier.getName() + " a été effacé");
    		}else{
    			System.out.println("echec de la suppression de " + nomFichierSortie);
    		}
		} catch (Exception ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}
	
	/**
	 * Zip it
	 * @param zipFile output ZIP file location
	 */
	public void compresser(String zipFile){
 
    	byte[] buffer = new byte[1024];
 
    	try{
    		/*FileOutputStream fos = new FileOutputStream(zipFile);
    		ZipOutputStream zos = new ZipOutputStream(fos);
 
    		System.out.println("Output to Zip : " + zipFile);
 
    		for(String file : this.listeFichiers){
    			System.out.println("File Added : " + file);
    			ZipEntry ze= new ZipEntry(file);
    			zos.putNextEntry(ze);
    			FileInputStream fis = 
    					new FileInputStream(file);
    			int len;
    			while ((len = fis.read(buffer)) > 0) {
    				zos.write(buffer, 0, len);
    			}
    			zos.closeEntry();
    			fis.close();
    		}*/
	         BufferedInputStream origin = null;
	         FileOutputStream dest = new 
	           FileOutputStream(zipFile);
	         ZipOutputStream out = new ZipOutputStream(new 
	           BufferedOutputStream(dest));
	         //out.setMethod(ZipOutputStream.DEFLATED);
	         // get a list of files from current directory
	         String files[] = new String[listeFichiers.size()];
	         files = listeFichiers.toArray(files);
	         for (int i=0; i<files.length; i++) {
	            System.out.println("Adding: "+files[i]);
	            FileInputStream fi = new 
	              FileInputStream(files[i]);
	            origin = new 
	              BufferedInputStream(fi, 1024);
	            ZipEntry entry = new ZipEntry(files[i]);
	            out.putNextEntry(entry);
	            int count;
	            while((count = origin.read(buffer, 0, 1024)) != -1) {
	               out.write(buffer, 0, count);
	            }
	            out.closeEntry();
	            origin.close();
	            
	         }
	        out.close();
    		//remember close it
    		//zos.close();
    		//fos.close();
    		System.out.println("Done");
    	}catch(IOException ex){
    		ex.printStackTrace();   
    	}
	}
}
