package configuration.management;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import configuration.management.repo.EventProcessingDataSourceRepository;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.IoTDeviceDataSourceRepository;
import configuration.management.repo.IoTDeviceRepository;

@Component
public class ApplicationScheduler {

    final static Logger logger = LoggerFactory.getLogger(ApplicationScheduler.class);

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Autowired
    private EventProcessingDataSourceRepository eventProcessingDataSourceRepo;

    @Autowired
    private IoTDeviceRepository deviceRepository;

    @Autowired
    private IoTDeviceDataSourceRepository deviceDataSourceRepository;

    @Scheduled(fixedDelay = 60000)
    public void carryOutActivity() {

        /**
         * clean up;
         */

        GregorianCalendar gregorianCalendar = new GregorianCalendar();

    }
}
