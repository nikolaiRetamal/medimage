package cnam.medimage.service.xmlEvent;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * @author Jullien
 *
 */
public class StartDescriptorFilter implements EventFilter {
	

	@Override
	public boolean accept(XMLEvent event) {

		if(event.isStartElement() && "DescriptorRecord".equals(event.asStartElement().getName().getLocalPart())){			
			return true;
		}else{
			
			return false;
		}
		
	}

}
