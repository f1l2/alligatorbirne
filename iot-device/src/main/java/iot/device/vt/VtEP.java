package iot.device.vt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class VtEP {

    private static List<VtData> data = new ArrayList<VtData>();

    private static MultiValueMap<String, VtData> map = new LinkedMultiValueMap<String, VtData>();

    public static void send(VtData item) {
        data.add(item);
        map.add(item.getUrl(), item);
    }

    public static VtData getLast() {
        if (data.size() == 0) {
            return null;
        }
        return data.get(data.size() - 1);
    }

    public static List<VtData> getData() {
        return data;
    }

    public static MultiValueMap<String, VtData> getMap() {
        return map;
    }
}
