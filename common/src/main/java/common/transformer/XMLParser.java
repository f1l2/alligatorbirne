package common.transformer;

import java.io.File;
import java.net.URI;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import common.data.configuration.XMLConfiguration;

public class XMLParser {

    public static XMLConfiguration unmarshal(final URI uri) {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(XMLConfiguration.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (XMLConfiguration) unmarshaller.unmarshal(uri.toURL());

        } catch (final Exception ex) {
            throw new DataBindingException(ex);
        }
    }

    public static void marshal(XMLConfiguration data, final File file) {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(XMLConfiguration.class);
            final Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.marshal(data, file);

        } catch (final Exception ex) {
            throw new DataBindingException(ex);
        }
    }

}
