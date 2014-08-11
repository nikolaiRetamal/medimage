package cnam.medimage.controller.controleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@org.springframework.stereotype.Controller
public class AccueilImportContr implements Controller{

	@RequestMapping(value="/import")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("Je suis dans le contrôleur de l'import");
		
		ServletContext context = request.getSession().getServletContext();
		String pathFile = context.getRealPath("/resources/dcmSamples/DEF_VEINEUX_107205/IM-0001-0001.dcm");
		DicomObject dcmObj;
		DicomInputStream din = null;
		try {
		    din = new DicomInputStream(new File(pathFile));
		    dcmObj = din.readDicomObject();
		    String uid = dcmObj.getString(Tag.ReferencedSOPInstanceUID);
		    System.out.println("UID= " + uid);
		}
		catch (IOException e) {
		    e.printStackTrace();
		    return null;
		}
		finally {
		    try {
		        din.close();
		    }
		    catch (IOException ignore) {
		    }
		}
		return new ModelAndView("home");
	}

}
