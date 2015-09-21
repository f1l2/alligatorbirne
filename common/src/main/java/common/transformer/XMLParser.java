package common.transformer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import common.data.configuration.XMLConfiguration;

public class XMLParser {

    public static XMLConfiguration unmarshal(final URI uri) throws JAXBException, MalformedURLException {

        final JAXBContext jaxbContext = JAXBContext.newInstance(XMLConfiguration.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (XMLConfiguration) unmarshaller.unmarshal(uri.toURL());

    }

    public static void marshal(XMLConfiguration data, final File file) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(XMLConfiguration.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.marshal(data, file);

    }

}
