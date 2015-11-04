package iot.device.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class VirtualEP {

    private static List<VirtualData> data = Collections.synchronizedList(new ArrayList<VirtualData>());

    private static MultiValueMap<String, VirtualData> map = new LinkedMultiValueMap<String, VirtualData>();

    public static void send(VirtualData item) {

        data.add(item);
        map.add(item.getUrl(), item);
    }

    public static VirtualData getLast() {
        if (data.size() == 0) {
            return null;
        }
        return data.get(data.size() - 1);
    }

    public static VirtualData getSecondLast() {
        if (data.size() <= 1) {
            return null;
        }
        return data.get(data.size() - 2);
    }

    public static List<VirtualData> getData() {
        return data;
    }

    public static MultiValueMap<String, VirtualData> getMap() {
        return map;
    }

    public static void setData(List<VirtualData> data) {
        VirtualEP.data = data;
    }

    public static void setMap(MultiValueMap<String, VirtualData> map) {
        VirtualEP.map = map;
    }

    public static void reset() {
        VirtualEP.setData(new ArrayList<VirtualData>());
        VirtualEP.setMap(new LinkedMultiValueMap<String, VirtualData>());
    }
}
