package configuration.management.rest.activity;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.data.dto.DataSourcesDTO;
import configuration.management.model.DataSourceRO;
import configuration.management.model.EventProcessing;
import configuration.management.repo.DataSourceTransformer;
import configuration.management.repo.EventProcessingRepository;

@Component
public class RegisterDataSourcesEP extends Activity<String, DataSourcesDTO> {

    final static Logger logger = LoggerFactory.getLogger(RegisterDataSourcesEP.class);

    private Long id;

    private boolean deregiser = false;

    @Autowired
    private EventProcessingRepository repo;

    @Autowired
    private DataSourceTransformer transformer;

    @Override
    public ResponseEntity<String> doStep(DataSourcesDTO item) {

        EventProcessing component = this.repo.findOne(id);

        if (component == null) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. Event processing instance with Id couldn't be found.", HttpStatus.BAD_REQUEST));
        } else if (CollectionUtils.isEmpty(component.getDataSources())) {

            if (!deregiser) {

                Set<DataSourceRO> ds = transformer.toLocal(item.getDataSources());
                component.setDataSources(ds);
                this.repo.save(component);
            }

        } else {

            Set<DataSourceRO> ds = transformer.toLocal(item.getDataSources());

            if (!deregiser) {
                component.getDataSources().addAll(ds);
            } else {
                component.getDataSources().retainAll(ds);
            }

            this.repo.save(component);
        }

        return next("OK", item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeregiser() {
        return deregiser;
    }

    public void setDeregiser(boolean deregiser) {
        this.deregiser = deregiser;
    }
}
