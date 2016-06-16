package configuration.management.rest.activity.receive;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.data.ConfigurationDelegation;
import configuration.management.model.DataSourceDLO;
import configuration.management.model.EventProcessingDLO;
import configuration.management.repo.DataSourceTransformer;

@Component
public class DeregisterDataSourcesEP extends ReceiveActivity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(DeregisterDataSourcesEP.class);

    @Autowired
    private DataSourceTransformer transformer;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        EventProcessingDLO component = eventProcessingRepository.findByAuthority(item.getDataSink().getUrl().getAuthority());

        if (component == null) {
            setErrorResponse(new ResponseEntity<String>("Deregistration of data sources failed. Event processing instance with Id couldn't be found.", HttpStatus.BAD_REQUEST));
        } else if (!CollectionUtils.isEmpty(component.getDataSources())) {
            Set<DataSourceDLO> ds = transformer.toLocal(item.getDataSources());
            component.getDataSources().retainAll(ds);
            eventProcessingRepository.save(component);
        }
        return next("OK", item);
    }
}
