package Classes;

public class Product {
    private String name;
    private String description;
    private int count = 1; // Value of this attribute shows how many types of this products are in one Invoice. By default 1.
    private double price;
    private double totalProductsPrice;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // This getter needs to return string because of displaying product in tableView
    public String getPrice() {
        return Double.toString(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalProductsPrice() {
        return totalProductsPrice;
    }

    public void setTotalProductsPrice(double totalProductsPrice) {
        this.totalProductsPrice = totalProductsPrice;
    }
}
