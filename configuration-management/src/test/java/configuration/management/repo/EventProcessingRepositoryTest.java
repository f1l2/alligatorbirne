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
import configuration.management.model.DataSourceRO;
import configuration.management.model.EventProcessing;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EventProcessingRepositoryTest {

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    private EventProcessing ep1, ep2;

    private DataSourceRO dataSource1;

    @Before
    public void before() {

        this.eventProcessingRepo.deleteAll();

        ep1 = new EventProcessing();
        ep1.setCreated(new Date());
        ep1.setUpdated(new Date());
        ep1.setName("ep1");
        ep1.setAuthority("http://url1.bla.bla.at");

        ep1 = this.eventProcessingRepo.save(ep1);

        ep2 = new EventProcessing();

        ep2.setCreated(new Date());
        ep2.setUpdated(new Date());
        ep2.setName("ep2");
        ep2.setAuthority("http://url2.bla.bla.at");

        ep2 = this.eventProcessingRepo.save(ep2);

        dataSource1 = new DataSourceRO();
        dataSource1.setDevice("device");
        dataSource1.setDomain("domain");

    }

    @Test
    public void findByName() {

        EventProcessing result = this.eventProcessingRepo.findByName(ep1.getName());

        assertNotNull(result);
        assertEquals(ep1.getId(), result.getId());
        assertEquals(ep1.getName(), result.getName());
        assertEquals(ep1.getAuthority(), result.getAuthority());
    }

    @Test
    @Transactional
    public void saveDataSource() {

        EventProcessing result = this.eventProcessingRepo.findByName(ep1.getName());
        result.getDataSources().add(dataSource1);

        result = this.eventProcessingRepo.save(result);

        result = this.eventProcessingRepo.findByName(ep1.getName());

        assertNotNull(result);
        assertEquals(ep1.getId(), result.getId());
        assertEquals(ep1.getName(), result.getName());
        assertEquals(ep1.getAuthority(), result.getAuthority());

        assertNotNull(result.getDataSources());
        assertEquals(1, result.getDataSources().size());
        assertEquals(dataSource1.getDevice(), result.getDataSources().get(0).getDevice());
        assertEquals(dataSource1.getDomain(), result.getDataSources().get(0).getDomain());
    }

}
