package configuration.management.rest.activity.receive;

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
import configuration.management.model.DataSourceDLO;
import configuration.management.model.DeviceDLO;
import configuration.management.model.EventProcessingDLO;
import configuration.management.repo.DataSourceTransformer;
import configuration.management.repo.DeviceTransformer;
import configuration.management.repo.EventProcessingTransformer;
import configuration.management.rest.activity.call.SetConfigDelegation;
import configuration.management.rest.activity.call.StartDeliveryDelegation;
import configuration.management.rest.task.ExecuteRestTask;

@Component
public class RegisterDataSourcesDevice extends ReceiveActivity<String, DataSourcesDTO> {

    final static Logger logger = LoggerFactory.getLogger(RegisterDataSourcesDevice.class);

    private Long id;

    @Autowired
    private DataSourceTransformer transformer;

    @Autowired
    private DeviceTransformer devTransformer;

    @Autowired
    private EventProcessingTransformer eventProcessingTransformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public ResponseEntity<String> doStep(DataSourcesDTO item) {

        DeviceDLO device = deviceRepository.findOne(id);
        Connection deviceConnection = devTransformer.toRemote(device);

        if (device == null) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. Device with ID couldn't be found.", HttpStatus.BAD_REQUEST));
        } else if (!CollectionUtils.isEmpty(device.getDataSources())) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. For device data sources already registered.", HttpStatus.BAD_REQUEST));
        } else {

            Set<DataSourceDLO> ds = transformer.toLocal(item.getDataSources());

            device.setDataSources(ds);
            deviceRepository.save(device);

            /**
             * Loop all data sources, which can be provided by device
             */
            for (DataSourceDLO dsDevice : ds) {

                /**
                 * TODO what the hell
                 */
                for (EventProcessingDLO ep : eventProcessingRepository.findByDataSources(dsDevice.getDevice(), dsDevice.getDomain())) {

                    Connection epConnection = eventProcessingTransformer.toRemote(ep);
                    epConnection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);

                    CDBuilder builder = new CDBuilder();
                    builder.addDataSource(dsDevice.getDevice(), dsDevice.getDomain()).buildDataSink(epConnection);

                    taskExecutor.execute(new ExecuteRestTask<StartDeliveryDelegation>(new StartDeliveryDelegation(builder.getResult(), Arrays.asList(deviceConnection))));

                    ep.getDataSources().contains(dsDevice);

                    if (!CollectionUtils.isEmpty(ep.getDataSources())) {

                        DataSourceDLO dsRO = ep.getDataSources().stream().findFirst().get();

                        if (!CollectionUtils.isEmpty(dsRO.getProperties())) {

                            builder = new CDBuilder();
                            builder.buildDataSink(epConnection)//
                                    .buildProperties(dsRO.getProperties())//
                                    .addDataSource(dsDevice.getDevice(), dsDevice.getDomain());

                            taskExecutor.execute(new ExecuteRestTask<SetConfigDelegation>(new SetConfigDelegation(builder.getResult(), Arrays.asList(deviceConnection))));
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
