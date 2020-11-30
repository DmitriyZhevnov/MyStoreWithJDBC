package classes;

import database.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageOfProducts2 {
    private List<Product> storage = new ArrayList<>();

    public void addProduct(Product product, int countToAdd) {
        if (storage.stream().anyMatch(s -> s.getSerialNumber() == product.getSerialNumber())) {
            ////// поправить метод
            DataBase.addProductInStorage(product);
        } else {
            storage.add(product);
            storage.stream().filter(s-> s==product).collect(Collectors.toList()).get(0).setCount(countToAdd);
        }
    }

    public Product getProductInStorageById(int id) {
        if (storage.stream().anyMatch(s -> s.getSerialNumber() == id)) {
            return storage.stream().filter(s -> s.getSerialNumber() == id).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public Product getProductInStorage(Product product) {
        if (storage.stream().anyMatch(s -> s.getSerialNumber() == product.getSerialNumber())) {
            return storage.stream().filter(s -> s.getSerialNumber() == product.getSerialNumber()).collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }

    public List<Product> returnStorage() {
        return storage;
    }
}
