package common.transformer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.SAXException;

import common.data.setting.XMLSetting;

public class XMLParser {

    // private static final String PATH_TO_XSD = "src/main/xsd/setting.xsd";

    public static XMLSetting unmarshal(final URI uri) throws JAXBException, MalformedURLException, SAXException {

        // TODO schema validation

        // final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        // final Schema schema = sf.newSchema(new File(PATH_TO_XSD));

        final JAXBContext jaxbContext = JAXBContext.newInstance(XMLSetting.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        // unmarshaller.setSchema(schema);

        return (XMLSetting) unmarshaller.unmarshal(uri.toURL());

    }

    public static void marshal(XMLSetting data, final File file) throws JAXBException, SAXException {
        // TODO schema validation

        // final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        // final Schema schema = sf.newSchema(new File(PATH_TO_XSD));

        final JAXBContext jaxbContext = JAXBContext.newInstance(XMLSetting.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "setting ../src/main/xsd/setting.xsd");
        // marshaller.setSchema(schema);

        marshaller.marshal(data, file);

    }

}
