package classes;

import java.util.HashMap;
import java.util.Map;

public class StorageOfOrders {
    private static Map<Integer, Order> orderStorage = new HashMap<>();

    public static Map<Integer, Order> getOrderStorage() {
        return orderStorage;
    }

}
