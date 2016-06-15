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
public class RegisterDataSourcesEP extends ReceiveActivity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(RegisterDataSourcesEP.class);

    private boolean deregiser = false;

    @Autowired
    private DataSourceTransformer transformer;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        EventProcessingDLO component = this.eventProcessingRepository.findByAuthority(item.getDataSink().getUrl().getAuthority());

        if (component == null) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. Event processing instance with Id couldn't be found.",
                    //
                    HttpStatus.BAD_REQUEST));
        } else if (CollectionUtils.isEmpty(component.getDataSources())) {
            if (!deregiser) {
                Set<DataSourceDLO> ds = transformer.toLocal(item.getDataSources());
                component.setDataSources(ds);
                this.eventProcessingRepository.save(component);
            }
        } else {
            Set<DataSourceDLO> ds = transformer.toLocal(item.getDataSources());
            if (!deregiser) {
                component.getDataSources().addAll(ds);
            } else {
                component.getDataSources().retainAll(ds);
            }
            this.eventProcessingRepository.save(component);
        }
        return next("OK", item);
    }

    public boolean isDeregiser() {
        return deregiser;
    }

    public void setDeregiser(boolean deregiser) {
        this.deregiser = deregiser;
    }
}
