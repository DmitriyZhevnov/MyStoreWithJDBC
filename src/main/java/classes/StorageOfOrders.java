package classes;

import java.util.ArrayList;
import java.util.List;

public class StorageOfOrders {
    private static List<Order> orderStorage = new ArrayList<>();

    public static List<Order> getOrderStorage() {
        return orderStorage;
    }

}
