package event.processing;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import common.data.DataModel;

public class Test1 {

    @Test
    public void test() throws ClassNotFoundException {

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(DataModel.class));

        Set<BeanDefinition> components = provider.findCandidateComponents("common/data");
        for (BeanDefinition component : components) {
            Class<?> cls = Class.forName(component.getBeanClassName());

        }

    }

}
