package iot.device.vt;

import java.util.ArrayList;
import java.util.List;

public class VtEP {

    private static List<VtData> data = new ArrayList<VtData>();

    public static void send(VtData item) {
        data.add(item);
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
}
