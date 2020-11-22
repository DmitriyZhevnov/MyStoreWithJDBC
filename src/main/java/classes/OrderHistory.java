package classes;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orderHistory = new ArrayList<>();

    public void addNewOrder(List<Product> productList) {
        Order order = new Order(productList);
        int numberOfOrder = StorageOfOrders.getOrderStorage().size() + 1;
        order.setNumber(numberOfOrder);
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
