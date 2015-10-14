package iot.device.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.rest.UtilsUrl;
import iot.device.Application;
import iot.device.property.Configuration;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DeliveryTaskRepositoryTest {

    private DeliveryTaskRO task1, task2;

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    /**
     * Implicitly test function save.
     */
    @Before
    public void before() {

        URL url = UtilsUrl.parseUrl("host:2000");

        Properties properties = new Properties();
        properties.put("property1Key", "property1Value");
        properties.put("property2Key", "property2Value");

        Configuration configuration = new Configuration();
        configuration.setAndUpdateProperties(properties);

        task1 = new DeliveryTaskRO();
        task1.setUrlDataSink(url);
        task1.setConfiguration(configuration);

        repo.save(task1);

        task2 = new DeliveryTaskRO();
        task2.setUrlDataSink(UtilsUrl.parseUrl("host:2001"));
        task2.setConfiguration(configuration);

        repo.save(task2);
    }

    /**
     * Implicitly test function delete.
     */
    @After
    public void after() {
        repo.delete(task1);
    }

    @Test
    public void save() {

        repo.save(task1);

        List<DeliveryTaskRO> results = repo.findAll();

        assertNotNull(results);
        assertEquals(2, results.size());

        task1.setUrlDataSink(UtilsUrl.parseUrl("host:2002"));

        repo.save(task1);

        results = repo.findAll();

        assertNotNull(results);
        assertEquals(3, results.size());

    }

    @Test
    public void findByAuthority() {

        DeliveryTaskRO result;

        result = repo.findByAuthority("abc");

        assertNull(result);

        result = repo.findByAuthority(task1.getUrlDataSink().getAuthority());

        assertNotNull(result);
        assertEquals(task1.getUrlDataSink(), result.getUrlDataSink());
    }

    @Test
    public void findByUrl() {

        DeliveryTaskRO result;

        result = repo.findByAuthority("abc");

        assertNull(result);

        result = repo.findByUrl(task1.getUrlDataSink());

        assertNotNull(result);
        assertEquals(task1.getUrlDataSink(), result.getUrlDataSink());
    }

    @Test
    public void findAll() {

        List<DeliveryTaskRO> results;

        results = repo.findAll();

        assertNotNull(results);
        assertEquals(2, results.size());

        repo.delete(task2);

        results = repo.findAll();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(task1.getUrlDataSink(), results.get(0).getUrlDataSink());

    }
}
