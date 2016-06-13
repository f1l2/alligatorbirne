package configuration.management.repo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.DataSource;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.transformer.Transformer;
import configuration.management.model.DataSourceDLO;

@Component
public class DataSourceTransformer extends Transformer<DataSourceDLO, DataSource> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public DataSourceDLO toLocal(DataSource remote) {

        DataSourceDLO item = new DataSourceDLO();

        /**
         * 
         * Everything to lower case to be not case sensitive.
         */

        String deviceInformation = remote.getDeviceInformation().getName();
        if (null != deviceInformation) {
            item.setDevice(deviceInformation.toLowerCase());
        }

        String domainInformation = remote.getDomainInformation().getName();
        if (null != domainInformation) {
            item.setDomain(domainInformation.toLowerCase());
        }
        return item;
    }

    @Override
    public DataSource toRemote(DataSourceDLO local) {

        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(local.getDevice());

        DomainInformation domInfo = new DomainInformation();
        domInfo.setName(local.getDomain());

        DataSource item = new DataSource();
        item.setDeviceInformation(devInfo);
        item.setDomainInformation(domInfo);

        return item;
    }

    public Set<DataSourceDLO> toLocal(Set<DataSource> remotes) {

        Set<DataSourceDLO> locals = new HashSet<DataSourceDLO>();

        for (DataSource remote : remotes) {
            locals.add(toLocal(remote));
        }

        return locals;
    }

    public List<DataSource> toRemote(Set<DataSourceDLO> locals) {

        List<DataSource> remotes = new ArrayList<DataSource>();

        for (DataSourceDLO local : locals) {
            remotes.add(toRemote(local));
        }

        return remotes;
    }

}
