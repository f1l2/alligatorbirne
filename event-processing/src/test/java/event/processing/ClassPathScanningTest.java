package event.processing;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import common.data.model.DataModel;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;

public class ClassPathScanningTest {

    private Set<String> classes = new HashSet<String>();

    @Test
    public void scanCommonData() throws ClassNotFoundException {

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(DataModel.class));

        Set<BeanDefinition> components = provider.findCandidateComponents("common/data");
        for (BeanDefinition component : components) {
            Class<?> cls = Class.forName(component.getBeanClassName());
            classes.add(cls.getSimpleName());
        }

        assertTrue(classes.contains(DeviceInformation.class.getSimpleName()));
        assertTrue(classes.contains(DomainInformation.class.getSimpleName()));

    }

}
