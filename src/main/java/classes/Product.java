package classes;

public class Product {
    private int serialNumber;
    private String name;
    private String description;
    private double price;
    private int count;

    public Product(int id, String name, String description, double price) {
        this.serialNumber = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Product product) {
        this.serialNumber = product.getSerialNumber();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

//    @Override
//    public String toString() {
//        return (name + " " + count);
//    }
}

