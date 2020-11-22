package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {
    private List<Product> basket = new ArrayList<>();

    public void addProductToBasket(Product product, int countToAdd) {
        if (basket.stream().anyMatch(s -> s.getId() == product.getId())) {
            int firstCount = basket.stream().filter(s -> s.getId() == product.getId()).collect(Collectors.toList())
                    .get(0).getCount();
            getProductFromBasket(product).setCount(firstCount + countToAdd);
        } else {
            basket.add(new Product(product));
            getProductFromBasket(product).setCount(countToAdd);
        }
    }

    public double getTotalCostOfProduct(Product product) {
        return getProductFromBasket(product).getCount() * getProductFromBasket(product).getPrice();
    }

    public double getTotalCostOfBasket() {
        double totalCost = 0;
        for (int i = 0; i < basket.size(); i++) {
            totalCost += getTotalCostOfProduct(basket.get(i));
        }
        return totalCost;
    }

    public void removeAllFromBasket() {
        basket.clear();
    }

    public void removeProductFromBasket(Product product) {
        basket.remove(product);
    }

    public Product getProductByIdFromBasket(int id) {
        if (basket.stream().anyMatch(s -> s.getId() == id)) {
            return basket.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public Product getProductFromBasket(Product product) {
        if (basket.stream().anyMatch(s -> s.getId() == product.getId())) {
            return basket.stream().filter(s -> s.getId() == product.getId()).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public void deleteFromStorageThatInTheBasket() {
        for (Product product : basket) {
            Product thisProductInStorage = StorageOfProducts.getProductInStorage(product);
            int firstCountOfCountInStorage = thisProductInStorage.getCount();
            StorageOfProducts.getProductInStorage(product).setCount(firstCountOfCountInStorage - product.getCount());
        }
    }

    public List<Product> getBasket() {
        return basket;
    }
}
