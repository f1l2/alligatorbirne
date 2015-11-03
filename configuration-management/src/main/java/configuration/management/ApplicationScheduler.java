package configuration.management;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import configuration.management.model.EventProcessing;
import configuration.management.model.Device;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.DeviceRepository;

@Component
public class ApplicationScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationScheduler.class);

    private static final int OBSOLETE_CORRIDOR = 2;

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Autowired
    private DeviceRepository deviceRepo;

    @Scheduled(fixedDelay = 6000)
    @Transactional
    public void carryOutActivity() {

        /**
         * Detect obsolete components
         * 
         * IoTDev(s) or EP(s) which hasn't send a heart beat message within the last {@value #OBSOLETE_CORRIDOR} minutes gets removed.
         */

        GregorianCalendar gc = new GregorianCalendar();
        gc.add(GregorianCalendar.MINUTE, OBSOLETE_CORRIDOR * (-1));

        List<Device> obsoleteIoTDevs = deviceRepo.findByUpdatedBefore(gc.getTime());

        if (!CollectionUtils.isEmpty(obsoleteIoTDevs)) {
            deviceRepo.delete(obsoleteIoTDevs);
            logger.info("Obsolete IoTDev(s) detected. {}", obsoleteIoTDevs.stream().map(item -> item.toString()).collect(Collectors.joining(", ")));
        }

        List<EventProcessing> obsoleteEPs = eventProcessingRepo.findByUpdatedBefore(gc.getTime());

        if (!CollectionUtils.isEmpty(obsoleteEPs)) {
            eventProcessingRepo.delete(obsoleteEPs);
            logger.info("Obsolete EP(s) detected. {}", obsoleteEPs.stream().map(item -> item.toString()).collect(Collectors.joining(", ")));
        }

    }
}
