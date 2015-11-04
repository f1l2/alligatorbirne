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
import configuration.management.model.Device;
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

    @Autowired
    private DelegateDeliveryChange delegateDeliveryChange;

    @Override
    public ResponseEntity<String> doStep(DataSourcesDTO item) {

        Device component = this.repo.findOne(id);

        if (component == null) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. Device with ID couldn't be found.", HttpStatus.BAD_REQUEST));
        } else if (!CollectionUtils.isEmpty(component.getDataSources())) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. For device data sources already registered.", HttpStatus.BAD_REQUEST));
        } else {

            Set<DataSourceRO> ds = transformer.toLocal(item.getDataSources());

            component.setDataSources(ds);

            this.repo.save(component);

            // /**
            // * Loop all data sources, which can be provided by device
            // */
            //
            // Map<String, EventProcessing> eps = new HashMap<String, EventProcessing>();
            //
            // for (DataSourceRO dsDevice : ds) {
            // for (EventProcessing ep : epRepo.findByDataSources(dsDevice.getDevice(), dsDevice.getDomain())) {
            //
            // Connection remote = epTransformer.toRemote(ep);
            // remote.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
            //
            // DSBuilder dsBuilder = new DSBuilder();
            // dsBuilder.buildDataSource(dsDevice.getDevice(), dsDevice.getDomain());
            //
            // delegateDeliveryChange.setStart(true);
            // delegateDeliveryChange.doStep(dsBuilder.getResult());
            //
            // }
            // }
            //
            // /**
            // * Loop all interested EPs
            // */
            //
            // for (EventProcessing ep : new ArrayList<EventProcessing>(eps.values())) {
            //
            // for (DataSourceRO dsEP : ep.getDataSources()) {
            //
            // if (dsEP.getSensorData() == null) {
            //
            // logger.error("SensorData are null.");
            // continue;
            // }
            //
            // CDBuilder builder = new CDBuilder();
            // builder.buildDeviceInformation(dsEP.getDevice())
            // //
            // .buildDomainInformation(dsEP.getDomain());
            //
            // for (String sensorData : dsEP.getSensorData()) {
            //
            // Properties properties = new Properties();
            // properties.putAll(dsEP.getProperties());
            // properties.put(SensorReservedProperty.REQUEST_FOR_DELIVERY.getName(), sensorData);
            //
            // builder.buildConfigurationModification(remote, properties);
            //
            // delegateConfigChange.doStep(builder.getResult());
            //
            // }
            // }
            // }
        }

        return next("OK", item);
    }

    public void setId(Long id) {
        this.id = id;
    }

}
