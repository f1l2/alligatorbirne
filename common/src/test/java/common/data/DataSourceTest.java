package common.data;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import common.data.builder.DSBuilder;
import common.data.dto.DataSourcesDTO;
import common.data.model.DataSource;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;

public class DataSourceTest {

    private DataSourcesDTO dataSource;

    @Before
    public void before() {
        DSBuilder dsBuilder = new DSBuilder();
        dsBuilder.buildDataSource("pressure", "floor1");

        dataSource = dsBuilder.getResult();

        assertEquals(1, dataSource.getDataSources().size());
    }

    @Test
    public void add() {

        DeviceInformation device = new DeviceInformation();
        device.setName("name1");

        DomainInformation domain = new DomainInformation();
        domain.setName("name1");

        DataSource ds = new DataSource();
        ds.setDeviceInformation(device);
        ds.setDomainInformation(domain);

        dataSource.add(ds);
        assertEquals(2, dataSource.getDataSources().size());

        dataSource.add(ds);
        assertEquals(2, dataSource.getDataSources().size());
    }

    @Test
    public void remove() {

        DeviceInformation device = new DeviceInformation();
        device.setName("name1");

        DomainInformation domain = new DomainInformation();
        domain.setName("name1");

        DataSource ds = new DataSource();
        ds.setDeviceInformation(device);
        ds.setDomainInformation(domain);

        dataSource.add(ds);
        assertEquals(2, dataSource.getDataSources().size());

        dataSource.remove(ds);
        assertEquals(1, dataSource.getDataSources().size());

        dataSource.add(ds);
        assertEquals(2, dataSource.getDataSources().size());

        Set<DataSource> dss = new HashSet<DataSource>();
        dss.add(ds);

        dataSource.remove(dss);
        assertEquals(1, dataSource.getDataSources().size());

    }

    @Test
    public void interference() {
        DSBuilder dsBuilder1 = new DSBuilder();
        dsBuilder1//
                .buildDataSource("pressure", "floor1") //
                .buildDataSource("pressure", "floor2") //
                .buildDataSource("pressure", "floor3");

        DSBuilder dsBuilder2 = new DSBuilder();
        dsBuilder2//
                .buildDataSource("pressure", "floor1") //
                .buildDataSource("pressure1", "floor2") //
                .buildDataSource("pressure1", "floor3");

        DataSourcesDTO result1 = dsBuilder1.getResult();
        DataSourcesDTO result2 = dsBuilder2.getResult();

        result1.getDataSources().retainAll(result2.getDataSources());

        assertEquals(1, result1.getDataSources().size());

    }

}
