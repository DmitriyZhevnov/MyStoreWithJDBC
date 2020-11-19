package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {
    private List<Product> basket = new ArrayList<>();

    public Basket() {
    }

//    public Basket(Basket basket) {
//        this.basket = basket.getBasket();
//    }

    public void addProductToBasket(Product product, int count) {
        if (StorageOfProducts.getProductInStorage(product).getCount() >= count) {
            basket.add(new Product(product));
            getProductFromBasket(product).setCount(count);
        } else {
            System.out.println("Столько нет");
        }
    }

    public void removeAllFromBasket() {
        basket.clear();
    }

    public Product getProductFromBasket(Product product) {
        return basket.stream().filter(s -> s.getId() == product.getId()).collect(Collectors.toList()).get(0);
    }

    public void buyAllThatInBasket() {
        for (Product product : basket) {
            int firstCount = StorageOfProducts.getProductInStorage(product).getCount();
            StorageOfProducts.getProductInStorage(product).setCount(firstCount - product.getCount());
        }
    }

    public List<Product> getBasket() {
        return basket;
    }
}
