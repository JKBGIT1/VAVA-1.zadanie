package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailInvoiceController extends HomepageController implements Initializable {
    public DetailInvoiceController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList,
            Invoice selectedInvoice
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
        this.setSelectedInvoice(selectedInvoice);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
