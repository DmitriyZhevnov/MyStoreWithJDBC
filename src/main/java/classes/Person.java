package classes;

import org.apache.log4j.Logger;

public class Person {
    final static Logger logger = Logger.getLogger(Person.class);
    private String name;
    private int age;
    private String login;
    private String password;
    private String status;
    private String phoneNumber;
    private String address;
    private Basket basket;
    private OrderHistory orderHistory;

    public Person(String name, int age, String login, String password, String status) {
        logger.info("New user " + login);
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
        this.status = status;
        address = "";
        phoneNumber = "";
        basket = new Basket();
        orderHistory = new OrderHistory();
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void buyBasket() {
        basket.deleteFromStorageThatInTheBasket();
        orderHistory.addNewOrder(basket.getBasket());
        orderHistory.getOrderHistory().get(orderHistory.getOrderHistory().size() - 1).setAddress(address);
        orderHistory.getOrderHistory().get(orderHistory.getOrderHistory().size() - 1).setPhoneNumber(phoneNumber);
        orderHistory.getOrderHistory().get(orderHistory.getOrderHistory().size() - 1).setLogin(getLogin());
        //
        StorageOfOrders.getOrderStorage().add(orderHistory.getOrderHistory().get(orderHistory.getOrderHistory().size() - 1));
        //
        if (logger.isInfoEnabled()){
            logger.info("New order №"+ orderHistory.getOrderHistory().get(orderHistory.getOrderHistory().size() - 1).getNumber());
        }
        basket.removeAllFromBasket();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Basket getBasket() {
        return basket;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
