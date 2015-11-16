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
import configuration.management.model.DataSourceRO;

@Component
public class DataSourceTransformer extends Transformer<DataSourceRO, DataSource> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public DataSourceRO toLocal(DataSource remote) {

        DataSourceRO item = new DataSourceRO();

        /**
         * 
         * Everything to lower case to be not case sensitive.
         */

        String name = remote.getDeviceInformation().getName();
        if (null != name) {
            item.setDevice(name.toLowerCase());
        }
        name = remote.getDomainInformation().getName();
        if (null != name) {
            item.setDomain(name.toLowerCase());
        }

        return item;
    }

    @Override
    public DataSource toRemote(DataSourceRO local) {

        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(local.getDevice());

        DomainInformation domInfo = new DomainInformation();
        domInfo.setName(local.getDomain());

        DataSource item = new DataSource();
        item.setDeviceInformation(devInfo);
        item.setDomainInformation(domInfo);

        return item;
    }

    public Set<DataSourceRO> toLocal(Set<DataSource> remotes) {

        Set<DataSourceRO> locals = new HashSet<DataSourceRO>();

        for (DataSource remote : remotes) {
            locals.add(toLocal(remote));
        }

        return locals;
    }

    public List<DataSource> toRemote(Set<DataSourceRO> locals) {

        List<DataSource> remotes = new ArrayList<DataSource>();

        for (DataSourceRO local : locals) {
            remotes.add(toRemote(local));
        }

        return remotes;
    }

}