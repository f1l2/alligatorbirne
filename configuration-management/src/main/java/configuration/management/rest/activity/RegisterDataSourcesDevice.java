package configuration.management.rest.activity;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.data.Connection;
import common.data.builder.CDBuilder;
import common.data.dto.DataSourcesDTO;
import common.property.SensorReservedProperty;
import common.transformer.Transformer;
import configuration.management.model.DataSourceRO;
import configuration.management.model.Device;
import configuration.management.model.EventProcessing;
import configuration.management.repo.DataSourceTransformer;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.EventProcessingTransformer;

@Component
public class RegisterDataSourcesDevice extends Activity<String, DataSourcesDTO> {

    final static Logger logger = LoggerFactory.getLogger(RegisterDataSourcesDevice.class);

    private Long id;

    @Autowired
    private DeviceRepository repo;

    @Autowired
    private DataSourceTransformer transformer;

    @Autowired
    private EventProcessingRepository epRepo;

    @Autowired
    private EventProcessingTransformer epTransformer;

    @Autowired
    private DelegateConfigChange delegateConfigChange;

    @Override
    public ResponseEntity<String> doStep(DataSourcesDTO item) {

        Device component = this.repo.findOne(id);

        if (component == null) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. Device with Id couldn't be found.", HttpStatus.BAD_REQUEST));
        } else if (!CollectionUtils.isEmpty(component.getDataSources())) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. For device data sources already registered.", HttpStatus.BAD_REQUEST));
        } else {

            List<DataSourceRO> ds = Transformer.makeCollection(transformer.toLocal(item.getDataSources()));

            component.setDataSources(ds);
            this.repo.save(component);

            for (DataSourceRO dsRO : ds) {
                List<EventProcessing> eps = epRepo.findByDataSources(dsRO.getDevice(), dsRO.getDomain());

                for (EventProcessing ep : eps) {

                    Connection remote = epTransformer.toRemote(ep);

                    Properties properties = new Properties();
                    properties.put(SensorReservedProperty.REQUEST_FOR_DELIVERY, remote.getUrl().getAuthority());

                    CDBuilder builder = new CDBuilder();
                    builder.buildConfigurationModification(remote, properties)
                            //
                            .buildDeviceInformation(dsRO.getDevice())
                            //
                            .buildDomainInformation(dsRO.getDomain());

                    delegateConfigChange.doStep(builder.getResult());
                }
            }

        }

        return next("OK", item);
    }

    public void setId(Long id) {
        this.id = id;
    }

}
