package common.transformer;

import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;
import common.data.setting.XMLDataSource;
import common.data.setting.XMLDeviceInformation;
import common.data.setting.XMLDomainInformation;

public class XMLDataSourceTransformer extends Transformer<XMLDataSource, DataSource> {

    private XMLDeviceInformationTranformer transformerDeviceInformation = new XMLDeviceInformationTranformer();

    private XMLDomainInformationTransformer transformerDomain = new XMLDomainInformationTransformer();

    @Override
    public DataSource toRemote(XMLDataSource local) {

        if (null == local) {
            return null;
        }

        DataSource dataSource = new DataSource();

        DeviceInformation deviceInformation = transformerDeviceInformation.toRemote(local.getDeviceInformation());

        dataSource.setDeviceInformation(deviceInformation);

        DomainInformation domainInformation = transformerDomain.toRemote(local.getDomainInformation());

        dataSource.setDomainInformation(domainInformation);

        return dataSource;
    }

    @Override
    public XMLDataSource toLocal(DataSource remote) {

        if (null == remote) {
            return null;
        }

        XMLDataSource xMLDataSource = new XMLDataSource();

        XMLDeviceInformation xMLDeviceInformation = transformerDeviceInformation.toLocal(remote.getDeviceInformation());

        xMLDataSource.setDeviceInformation(xMLDeviceInformation);

        XMLDomainInformation xMLDomainInformation = transformerDomain.toLocal(remote.getDomainInformation());

        xMLDataSource.setDomainInformation(xMLDomainInformation);

        return xMLDataSource;
    }

}
