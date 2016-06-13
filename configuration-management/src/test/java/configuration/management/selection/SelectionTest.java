package configuration.management.selection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.management.Application;
import configuration.management.model.DataSourceDLO;
import configuration.management.model.EventProcessingDLO;
import configuration.management.repo.EventProcessingRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SelectionTest {

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Autowired
    private MinCpuUtilization minCpuUtilization;

    @Autowired
    private MinNumberOfActiveRules minNumberOfActiveRules;

    @Autowired
    private MinRAMUtilization minRAMUtilization;

    private EventProcessingDLO ep1, ep2;

    private DataSourceDLO dataSource1;

    @Before
    public void before() {

        this.eventProcessingRepo.deleteAll();

        ep1 = new EventProcessingDLO();
        ep1.setCreated(new Date());
        ep1.setUpdated(new Date());
        ep1.setName("ep1");
        ep1.setAuthority("url1.bla.bla.at");
        ep1.setCpuUsage(10);
        ep1.setRamUsage(20);
        ep1.setNumberOfActiveRules(5);

        ep1 = this.eventProcessingRepo.save(ep1);

        ep2 = new EventProcessingDLO();

        ep2.setCreated(new Date());
        ep2.setUpdated(new Date());
        ep2.setName("ep2");
        ep2.setAuthority("url2.bla.bla.at");
        ep2.setCpuUsage(15);
        ep2.setRamUsage(15);
        ep2.setNumberOfActiveRules(3);

        ep2 = this.eventProcessingRepo.save(ep2);

        dataSource1 = new DataSourceDLO();
        dataSource1.setDevice("device");
        dataSource1.setDomain("domain");

    }

    @Test
    public void selectByCpuUtilization() {

        Optional<EventProcessingDLO> select = minCpuUtilization.select();

        assertTrue(select.isPresent());
        assertEquals(ep1.getName(), select.get().getName());

    }

    @Test
    public void selectByRAMUtilization() {

        Optional<EventProcessingDLO> select = minRAMUtilization.select();

        assertTrue(select.isPresent());
        assertEquals(ep2.getName(), select.get().getName());

    }

    @Test
    public void selectByActivatedRules() {
        Optional<EventProcessingDLO> select = minNumberOfActiveRules.select();

        assertTrue(select.isPresent());
        assertEquals(ep2.getName(), select.get().getName());

    }

}
