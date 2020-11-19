package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageOfProducts {
    private static List<Product> storage = new ArrayList<>();

    public StorageOfProducts(){
        addProduct(new Product(1, "Milk", "2.3%",1.50), 15);
        addProduct(new Product(2, "Water", "sparkling ",1.00), 10);
        addProduct(new Product(3, "Apple", "red",0.10), 5);
    }

    public static void addProduct(Product product, int countToAdd) {
        if (storage.stream().anyMatch(s -> s.getId() == product.getId())) {
            int firstCount = StorageOfProducts.getProductInStorage(product).getCount();
            StorageOfProducts.getProductInStorage(product).setCount(firstCount + countToAdd);
        } else {
            storage.add(product);
            StorageOfProducts.getProductInStorage(product).setCount(countToAdd);
        }
    }

    public static Product getProductInStorageById(int id){
        if (storage.stream().anyMatch(s -> s.getId() == id)) {
            return storage.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }
    public static Product getProductInStorage(Product product) {
        if (storage.stream().anyMatch(s -> s.getId() == product.getId())) {
            return storage.stream().filter(s -> s.getId() == product.getId()).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public static List<Product> returnStorage() {
        return storage;
    }
}
