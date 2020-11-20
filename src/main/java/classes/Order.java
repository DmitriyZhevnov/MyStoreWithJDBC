package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int number;
    private List<Product> listOfProducts = new ArrayList<>();
    private LocalDateTime dateTime;

    public Order(List<Product> listOfProducts) {
        this.listOfProducts.addAll(listOfProducts);
        dateTime = LocalDateTime.now();
    }

    public double getTotalCostOfOrder(){
        double totalCost = 0;
        for (int i = 0; i < listOfProducts.size(); i++){
            totalCost += getListOfProducts().get(i).getPrice() * getListOfProducts().get(i).getCount();
        }
        return totalCost;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }
    public String printListOfProducts(){
        StringBuilder listForReturn = new StringBuilder("");
        for (Product product : listOfProducts){
            listForReturn.append("Название: " + product.getName() + ". Количество: " + product.getCount() + "\n");
        }
        return listForReturn.toString();
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
