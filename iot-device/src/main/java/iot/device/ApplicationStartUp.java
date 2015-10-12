package iot.device;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import common.component.ApplicationStartUpUtils;
import iot.device.sensor.Sensor;
import iot.device.sensor.SensorFactory;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;

@Component
public class ApplicationStartUp implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    final static Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    @Autowired
    private Status status;

    private SensorFactory factory;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {
        try {
            ApplicationStartUpUtils.processLocalConnection(arg0);
        } catch (Exception e) {
            logger.error("Error retrieving local connection data.", e);
        } finally {
            status.setCurrent(STATUS_TYPE.STARTED_UP);
        }

        factory = SensorFactory.getInstance();

        try {

            ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
            provider.addIncludeFilter(new AssignableTypeFilter(Sensor.class));

            Set<BeanDefinition> components = provider.findCandidateComponents("iot/device/sensor/impl");
            for (BeanDefinition component : components) {
                Class<?> cls = Class.forName(component.getBeanClassName());

                Field declaredField = cls.getDeclaredField("NAME");
                declaredField.setAccessible(true);
                logger.info("Implemented sensor: " + declaredField.get(null));

                Constructor<?> ctor = cls.getConstructor();
                factory.getSensors().put((String) declaredField.get(null), (Sensor<?>) ctor.newInstance());

            }
        } catch (Exception e) {
            logger.error("Error retrieving and instantiating implemented sensors");
        }
    }
}
