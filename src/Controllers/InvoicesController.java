package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;

public class InvoicesController extends HomepageController {
    public InvoicesController(ObservableList<Customer> customersObservableList, ObservableList<Product> productsObservableList, ObservableList<Invoice> invoicesObservableList) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
    }
}
