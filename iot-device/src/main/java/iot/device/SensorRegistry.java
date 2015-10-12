package iot.device;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import iot.device.sensor.Sensor;

@Component
public class SensorRegistry implements BeanDefinitionRegistryPostProcessor {

    final static Logger logger = LoggerFactory.getLogger(SensorRegistry.class);

    private BeanDefinitionRegistry beanDefinitionRegistry;

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;

    }

    public void instantiate() {

        try {

            ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
            provider.addIncludeFilter(new AssignableTypeFilter(Sensor.class));

            Set<BeanDefinition> components = provider.findCandidateComponents("iot/device/sensor/impl");
            for (BeanDefinition component : components) {
                Class<?> cls = Class.forName(component.getBeanClassName());
                try {
                    beanFactory.getBean(cls.getSimpleName());
                } catch (NoSuchBeanDefinitionException ex) {
                    beanDefinitionRegistry.registerBeanDefinition(cls.getSimpleName(), BeanDefinitionBuilder.genericBeanDefinition(cls)//
                            .getBeanDefinition());
                    logger.info("Manually instantiated bean: " + beanFactory.getBean(cls.getSimpleName()).toString());
                }
            }
        } catch (Exception e) {
            logger.error("Error retrieving and instantiating implemented sensors. {}", e);
        }
    }
}
