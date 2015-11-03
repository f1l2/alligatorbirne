package configuration.management.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;
import common.property.SensorReservedProperty;
import configuration.management.model.EventProcessing;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.util.DataSourceUtil;

@Component
public class RegisterDataSourcesEP extends Activity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(RegisterDataSourcesEP.class);

    @Autowired
    private EventProcessingRepository repo;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        EventProcessing component = this.repo.findByAuthority(item.getConfigurationModification().getDataSink().getUrl().getAuthority());

        if ((null != component) && (null != item.getConfigurationModification().getProperties())) {

            /**
             * Special ConfigurationDelegation: REQUEST_FOR_DELIVERY
             * 
             */

            if (item.getConfigurationModification().getProperties().containsKey(SensorReservedProperty.REQUEST_FOR_DELIVERY)) {

                DataSourceUtil.saveAndUpdate(component, item.getDeviceInformation(), item.getDomainInformation());

                this.repo.save(component);
            }

            /**
             * Special ConfigurationDelegation: STOP_DELIVERY
             * 
             */

            else if (item.getConfigurationModification().getProperties().containsKey(SensorReservedProperty.STOP_DELIVERY)) {

                DataSourceUtil.delete(component, item.getDeviceInformation(), item.getDomainInformation());

                this.repo.save(component);
            }
        }

        return next("OK", item);
    }
}
