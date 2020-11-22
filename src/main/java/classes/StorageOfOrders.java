package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageOfOrders {
    private static List<Order> orderStorage = new ArrayList<>();

    public static List<Order> getOrderStorage() {
        return orderStorage;
    }

}
