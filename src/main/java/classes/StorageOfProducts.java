package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageOfProducts {
    private static List<Product> storage = new ArrayList<>();

    public static void addProduct(Product product, int countToAdd) {
        if (storage.stream().anyMatch(s -> s.getSerialNumber() == product.getSerialNumber())) {
            int firstCount = storage.stream().filter(s-> s==product).collect(Collectors.toList()).get(0).getCount();
            StorageOfProducts.getProductInStorage(product).setCount(firstCount + countToAdd);
        } else {
            storage.add(product);
            StorageOfProducts.getProductInStorage(product).setCount(countToAdd);
        }
    }

    public static Product getProductInStorageById(int id) {
        if (storage.stream().anyMatch(s -> s.getSerialNumber() == id)) {
            return storage.stream().filter(s -> s.getSerialNumber() == id).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public static Product getProductInStorage(Product product) {
        if (storage.stream().anyMatch(s -> s.getSerialNumber() == product.getSerialNumber())) {
            return storage.stream().filter(s -> s.getSerialNumber() == product.getSerialNumber()).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public static List<Product> returnStorage() {
        return storage;
    }
}
