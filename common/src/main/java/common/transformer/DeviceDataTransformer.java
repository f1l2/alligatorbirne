package common.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import common.data.dto.DeviceDataDTO;
import common.data.model.DeviceData;
import common.data.model.DomainInformation;

public class DeviceDataTransformer extends Transformer<DeviceData, DeviceDataDTO> {

    @Override
    public DeviceData toLocal(DeviceDataDTO remote) {

        DeviceData item = new DeviceData();

        if (null == remote) {
            return new DeviceData();
        }

        item.setDevice(remote.getDevice());
        item.setSensorData(remote.getSensorData());

        if (!CollectionUtils.isEmpty(remote.getDomains())) {
            item.setDomain(remote.getDomains().get(0));
        }
        return item;
    }

    @Override
    public DeviceDataDTO toRemote(DeviceData local) {

        DeviceDataDTO item = new DeviceDataDTO();

        if (null == local) {
            return item;
        }

        item.setDevice(local.getDevice());
        item.setSensorData(local.getSensorData());

        return item;
    }

    public static DeviceDataDTO aggregate(List<DeviceData> local) {

        if (CollectionUtils.isEmpty(local)) {
            return new DeviceDataDTO();
        }

        DeviceDataDTO item = new DeviceDataDTO();

        item.setDevice(local.get(0).getDevice());
        item.setSensorData(local.get(0).getSensorData());

        item.setDomains(local.stream().map(i -> i.getDomain()).collect(Collectors.toList()));

        return item;
    }

    public static List<DeviceData> fork(DeviceDataDTO remote) {

        List<DeviceData> items = new ArrayList<DeviceData>();

        if (CollectionUtils.isEmpty(remote.getDomains())) {
            return items;
        }

        for (DomainInformation domain : remote.getDomains()) {
            DeviceData item = new DeviceData();
            item.setDevice(remote.getDevice());
            item.setSensorData(remote.getSensorData());
            item.setDomain(domain);

            items.add(item);
        }

        return items;
    }

}
