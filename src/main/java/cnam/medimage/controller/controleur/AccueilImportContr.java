package cnam.medimage.controller.controleur;

import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.archimed.dicom.DDict;
import com.archimed.dicom.DicomObject;
import com.archimed.dicom.DicomReader;

@org.springframework.stereotype.Controller
public class AccueilImportContr implements Controller{

	@RequestMapping(value="/import")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("Je suis dans le contrôleur de l'import");
		
		ServletContext context = request.getSession().getServletContext();
		String pathFile = context.getRealPath("/resources/dcmSamples/DEF_VEINEUX_107205/IM-0001-0001.dcm");
		FileInputStream file = new FileInputStream(pathFile);
		DicomReader dcmReader = new DicomReader();
		DicomObject dcm = dcmReader.read(file);
		DicomObject metadata = dcm.getFileMetaInformation();
		String patientId = (String) metadata.get(DDict.dPatientID);
		System.out.println("patient ID = " + patientId);
		return new ModelAndView("home");
	}

}
