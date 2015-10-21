package iot.device.sensor;

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

@Component
public class DynamicSensorFactory implements BeanDefinitionRegistryPostProcessor {

    final static Logger logger = LoggerFactory.getLogger(DynamicSensorFactory.class);

    private BeanDefinitionRegistry beanDefinitionRegistry;
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public Sensor<?> getBean(final String beanClassName) {

        Sensor<?> sensor = null;
        synchronized (beanClassName.intern()) {
            try {
                sensor = (Sensor<?>) beanFactory.getBean(beanClassName);
            } catch (final NoSuchBeanDefinitionException ex) {
                logger.error("Couldn't locate bean: {}", beanClassName);
            }
        }
        return sensor;
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

                    String beanName = cls.getSimpleName().toLowerCase();

                    beanDefinitionRegistry.registerBeanDefinition(beanName, BeanDefinitionBuilder.genericBeanDefinition(cls)//
                            .getBeanDefinition());

                    logger.info("Manually instantiated bean. Bean: " + beanFactory.getBean(beanName).toString() + " Identifier: " + beanName);
                }
            }
        } catch (Exception e) {
            logger.error("Error retrieving and instantiating implemented sensors. {}", e);
        }
    }
}
