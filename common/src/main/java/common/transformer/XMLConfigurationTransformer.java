package common.transformer;

import common.data.DeviceInformation;
import common.data.DomainInformation;
import common.data.DataSource;
import common.data.configuration.XMLDataSource;
import common.data.configuration.XMLDeviceInformation;
import common.data.configuration.XMLDomainInformation;

public class XMLConfigurationTransformer extends Transformer<XMLDataSource, DataSource> {

    private XMLDeviceInformationTranformer transformerDeviceInformation = new XMLDeviceInformationTranformer();

    private XMLDomainInformationTransformer transformerDomain = new XMLDomainInformationTransformer();

    @Override
    public DataSource toRemote(XMLDataSource local) {

        DataSource measurementPoint = new DataSource();

        DeviceInformation remoteDev = transformerDeviceInformation.toRemote(local.getDeviceInformation());

        measurementPoint.setDeviceInformation(remoteDev);

        DomainInformation remoteDomain = transformerDomain.toRemote(local.getDomainInformation());

        measurementPoint.setDomain(remoteDomain);

        return measurementPoint;
    }

    @Override
    public XMLDataSource toLocal(DataSource remote) {
        XMLDataSource xMLDataSource = new XMLDataSource();

        XMLDeviceInformation localDev = transformerDeviceInformation.toLocal(remote.getDeviceInformation());

        xMLDataSource.setDeviceInformation(localDev);

        XMLDomainInformation localDomain = transformerDomain.toLocal(remote.getDomain());

        xMLDataSource.setDomainInformation(localDomain);

        return xMLDataSource;
    }

}
