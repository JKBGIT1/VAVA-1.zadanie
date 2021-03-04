package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProductController extends HomepageController implements Initializable {
    public EditProductController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList,
            Product selectedProduct
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
        this.setSelectedProduct(selectedProduct);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
