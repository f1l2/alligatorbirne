package configuration.management.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.DataSources;
import common.transformer.Transformer;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceDataSourceTransformer;
import configuration.management.repo.IoTDeviceRepository;

@Component
public class RegisterIoTDeviceDataSources extends Activity<String, DataSources> {

    final static Logger logger = LoggerFactory.getLogger(RegisterIoTDeviceDataSources.class);

    private Long id;

    @Autowired
    private IoTDeviceRepository deviceRepository;

    @Autowired
    private IoTDeviceDataSourceTransformer transformer;

    @Override
    public ResponseEntity<String> doStep(DataSources item) {

        IoTDeviceRO device = this.deviceRepository.findOne(id);

        if (device == null) {
            setErrorResponse(new ResponseEntity<String>("Registration of data sources failed. Device with Id couldn't be found.", HttpStatus.BAD_REQUEST));
        } else {
            device.setIoTDeviceDataSources(Transformer.makeCollection(transformer.toLocal(item.getDataSources())));

            deviceRepository.save(device);
        }

        return next("OK", item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
