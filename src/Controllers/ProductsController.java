package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;

public class ProductsController extends HomepageController {

    public ProductsController(ObservableList<Customer> customersObservableList, ObservableList<Product> productsObservableList, ObservableList<Invoice> invoicesObservableList) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
    }
}
