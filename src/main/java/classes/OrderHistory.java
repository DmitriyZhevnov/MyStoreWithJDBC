package classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHistory {
    private Map<Integer, Order> orderHistory = new HashMap<>();

    public void addNewOrder(List<Product> productList) {
        Order order = new Order(productList);
        int numberOfOrder = StorageOfOrders.getOrderStorage().size() + 1;
        order.setNumber(numberOfOrder);
        orderHistory.put(numberOfOrder, order);
        StorageOfOrders.getOrderStorage().put(numberOfOrder, order);
    }

    public Map<Integer, Order> getOrderHistory() {
        return orderHistory;
    }
}
