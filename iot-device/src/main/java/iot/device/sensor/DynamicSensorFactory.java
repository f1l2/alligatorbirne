package iot.device.sensor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DynamicSensorFactory implements BeanDefinitionRegistryPostProcessor {

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

    public Sensor<?> createBean(final String beanClassName) {

        Sensor<?> sensor = null;
        synchronized (beanClassName.intern()) {
            try {
                sensor = (Sensor<?>) beanFactory.getBean(beanClassName);
            } catch (final NoSuchBeanDefinitionException ex) {

                beanDefinitionRegistry.registerBeanDefinition(beanClassName, BeanDefinitionBuilder//
                        .genericBeanDefinition(beanClassName).getBeanDefinition());

                sensor = (Sensor<?>) beanFactory.getBean(beanClassName);
            }
        }

        return sensor;
    }

    public Sensor<?> getBean(final String beanClassName) {

        Sensor<?> sensor = null;
        synchronized (beanClassName.intern()) {
            try {
                sensor = (Sensor<?>) beanFactory.getBean(beanClassName);
            } catch (final NoSuchBeanDefinitionException ex) {

            }
        }
        return sensor;
    }
}
