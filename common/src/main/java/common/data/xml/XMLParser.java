package common.data.xml;

import java.io.File;
import java.net.URI;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import common.data.configuration.Configuration;

public class XMLParser {
	
	public static Configuration unmarshal(final URI uri) {
		  try {
	            final JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
	            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	            return (Configuration) unmarshaller.unmarshal(uri.toURL());
	            
	        } catch (final Exception ex) {
	            throw new DataBindingException(ex);
	        }
	}
	
	public static void marshal(Configuration data, final File file) {
		  try {
	            final JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
	            final Marshaller marshaller = jaxbContext.createMarshaller();
	            
	            marshaller.marshal(data, file);
	            
	        } catch (final Exception ex) {
	            throw new DataBindingException(ex);
	        }
	}

}
