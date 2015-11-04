package configuration.management.rest.activity;

import java.util.Arrays;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.data.Connection;
import common.data.builder.CDBuilder;
import common.data.dto.DataSourcesDTO;
import common.data.type.COMPONENT_TYPE;
import configuration.management.model.DataSourceRO;
import configuration.management.model.Device;
import configuration.management.model.EventProcessing;
import configuration.management.repo.DataSourceTransformer;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.DeviceTransformer;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.EventProcessingTransformer;
import configuration.management.task.SetConfigDelegation;
import configuration.management.task.StartDeliveryDelegation;

@Component
public class RegisterDataSourcesDevice extends Activity<String, DataSourcesDTO> {

    final static Logger logger = LoggerFactory.getLogger(RegisterDataSourcesDevice.class);

    private Long id;

    @Autowired
    private DeviceRepository devRepo;

    @Autowired
    private EventProcessingRepository epRepo;

    @Autowired
    private DataSourceTransformer transformer;

    @Autowired
    private DeviceTransformer devTransformer;

    @Autowired
    private EventProcessingTransformer epTransformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public ResponseEntity<String> doStep(DataSourcesDTO item) {

        Device component = this.devRepo.findOne(id);

        Connection componentConnection = devTransformer.toRemote(component);

        if (component == null) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. Device with ID couldn't be found.", HttpStatus.BAD_REQUEST));
        } else if (!CollectionUtils.isEmpty(component.getDataSources())) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. For device data sources already registered.", HttpStatus.BAD_REQUEST));
        } else {

            Set<DataSourceRO> ds = transformer.toLocal(item.getDataSources());

            component.setDataSources(ds);

            this.devRepo.save(component);

            /**
             * Loop all data sources, which can be provided by device
             */

            for (DataSourceRO dsDevice : ds) {
                for (EventProcessing ep : epRepo.findByDataSources(dsDevice.getDevice(), dsDevice.getDomain())) {

                    Connection remote = epTransformer.toRemote(ep);
                    remote.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);

                    CDBuilder builder = new CDBuilder();
                    builder.addDataSource(dsDevice.getDevice(), dsDevice.getDomain())//
                            .buildDataSink(remote);

                    taskExecutor.execute(new StartDeliveryDelegation(builder.getResult(), Arrays.asList(componentConnection)));

                    ep.getDataSources().contains(dsDevice);

                    if (!CollectionUtils.isEmpty(ep.getDataSources())) {

                        DataSourceRO dsRO = ep.getDataSources().stream().findFirst().get();

                        if (!CollectionUtils.isEmpty(dsRO.getProperties())) {

                            builder = new CDBuilder();
                            builder.buildDataSink(remote)//
                                    .buildProperties(dsRO.getProperties())//
                                    .addDataSource(dsDevice.getDevice(), dsDevice.getDomain());

                            taskExecutor.execute(new SetConfigDelegation(builder.getResult(), Arrays.asList(componentConnection)));
                        }
                    }
                }
            }
        }
        return next("OK", item);
    }

    public void setId(Long id) {
        this.id = id;
    }

}
