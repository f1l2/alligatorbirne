package common.transformer;

import common.data.Configuration;
import common.data.configuration.XMLConfiguration;
import common.data.configuration.XMLConnections;
import common.data.configuration.XMLDataSources;

public class XMLConfigurationTransformer extends Transformer<XMLConfiguration, Configuration> {

    private XMLConnectionTransformer connectionTransformer = new XMLConnectionTransformer();
    private XMLDataSourceTransformer dataSourceTransformer = new XMLDataSourceTransformer();

    @Override
    public XMLConfiguration toLocal(Configuration remote) {

        XMLConfiguration xmlConfiguration = new XMLConfiguration();

        XMLDataSources xmlDataSources = new XMLDataSources();
        xmlDataSources.getDataSource().addAll(dataSourceTransformer.toLocal(remote.getDataSources()));

        XMLConnections xmlConnections = new XMLConnections();
        xmlConnections.getConnection().addAll(connectionTransformer.toLocal(remote.getConnections()));

        xmlConfiguration.setDataSources(xmlDataSources);
        xmlConfiguration.setConnections(xmlConnections);

        return xmlConfiguration;
    }

    @Override
    public Configuration toRemote(XMLConfiguration local) {

        Configuration configuration = new Configuration();

        configuration.setConnections(connectionTransformer.toRemote(local.getConnections().getConnection()));
        configuration.setDataSources(dataSourceTransformer.toRemote(local.getDataSources().getDataSource()));

        return configuration;
    }

}
