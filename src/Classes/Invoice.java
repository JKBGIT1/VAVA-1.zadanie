package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class Invoice {
    private Date date;
    private String customerFullName;
    private Customer customer;
    private ObservableList<Product> invoiceProductsObservableList = FXCollections.observableArrayList();
    private double totalPrice;

    public Invoice(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ObservableList<Product> getInvoiceProductsObservableList() {
        return invoiceProductsObservableList;
    }

    public void setInvoiceProductsObservableList(ObservableList<Product> invoiceProductsObservableList) {
        this.invoiceProductsObservableList = invoiceProductsObservableList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
