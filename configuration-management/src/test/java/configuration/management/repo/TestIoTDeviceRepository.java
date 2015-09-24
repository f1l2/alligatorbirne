package configuration.management.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import configuration.management.Application;
import configuration.management.model.EventProcessingDataSourceRO;
import configuration.management.model.EventProcessingRO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestIoTDeviceRepository {

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    private EventProcessingRO ep1, ep2;

    private EventProcessingDataSourceRO dataSource1;

    @Before
    public void before() {

        this.eventProcessingRepo.deleteAll();

        ep1 = new EventProcessingRO();
        ep1.setCreated(new Date());
        ep1.setUpdated(new Date());
        ep1.setName("ep1");
        ep1.setAuthority("http://url1.bla.bla.at");

        ep1 = this.eventProcessingRepo.save(ep1);

        ep2 = new EventProcessingRO();

        ep2.setCreated(new Date());
        ep2.setUpdated(new Date());
        ep2.setName("ep2");
        ep2.setAuthority("http://url2.bla.bla.at");

        ep2 = this.eventProcessingRepo.save(ep2);

        dataSource1 = new EventProcessingDataSourceRO();
        dataSource1.setDevice("device");
        dataSource1.setDomain("domain");

    }

    @Test
    public void findByName() {

        EventProcessingRO result = this.eventProcessingRepo.findByName(ep1.getName());

        assertNotNull(result);
        assertEquals(ep1.getId(), result.getId());
        assertEquals(ep1.getName(), result.getName());
        assertEquals(ep1.getAuthority(), result.getAuthority());
    }

    @Test
    @Transactional
    public void saveDataSource() {

        EventProcessingRO result = this.eventProcessingRepo.findByName(ep1.getName());
        result.getEventProcessingDataSources().add(dataSource1);

        result = this.eventProcessingRepo.save(result);

        result = this.eventProcessingRepo.findByName(ep1.getName());

        System.out.println(result);

        assertNotNull(result);
        assertEquals(ep1.getId(), result.getId());
        assertEquals(ep1.getName(), result.getName());
        assertEquals(ep1.getAuthority(), result.getAuthority());

        assertNotNull(result.getEventProcessingDataSources());
        assertEquals(1, result.getEventProcessingDataSources().size());
        assertEquals(dataSource1.getDevice(), result.getEventProcessingDataSources().get(0).getDevice());
        assertEquals(dataSource1.getDomain(), result.getEventProcessingDataSources().get(0).getDomain());
    }

}
