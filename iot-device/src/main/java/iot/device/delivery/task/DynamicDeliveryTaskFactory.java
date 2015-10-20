package iot.device.delivery.task;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import iot.device.repo.DeliveryTaskRO;

@Component
public class DynamicDeliveryTaskFactory implements BeanDefinitionRegistryPostProcessor {

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

    public DelegationTask createBean(final DeliveryTaskRO deliveryTask) {

        final String beanName = String.format("deliveryTask_%s", Arrays.hashCode(new Object[] { deliveryTask.getUrlDataSink().getAuthority() }));

        DelegationTask dt = null;
        synchronized (beanName.intern()) {
            try {
                dt = (DelegationTask) beanFactory.getBean(beanName);
            } catch (final NoSuchBeanDefinitionException ex) {

                beanDefinitionRegistry.registerBeanDefinition(beanName, BeanDefinitionBuilder//
                        .genericBeanDefinition(DelegationTask.class).addConstructorArgValue(deliveryTask).getBeanDefinition());

                dt = (DelegationTask) beanFactory.getBean(beanName);
            }
        }

        return dt;
    }

    public DelegationTask getBean(final DeliveryTaskRO deliveryTask) {

        final String beanName = String.format("deliveryTask_%s", Arrays.hashCode(new Object[] { deliveryTask.getUrlDataSink().getAuthority() }));

        DelegationTask dt = null;
        synchronized (beanName.intern()) {
            try {
                dt = (DelegationTask) beanFactory.getBean(beanName);
            } catch (final NoSuchBeanDefinitionException ex) {

            }
        }

        return dt;
    }
}
