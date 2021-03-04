package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProductController extends HomepageController implements Initializable {

    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfName, tfPrice;

    public EditProductController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList,
            Product selectedProduct
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
        this.setSelectedProduct(selectedProduct);
    }

    public void saveEditedProduct() {
        // Change parameters to selectedCustomer based on textFields
        Product product = this.getSelectedProduct();

        try {
            product.setName(tfName.getText());
            product.setPrice(this.convertStringToDouble(tfPrice.getText()));
            product.setDescription(taDescription.getText());

            // Let user know, that product editing was successful
            this.showSuccessPopUp(EDIT_SUCCESS, "Product was successfully edited.");
        } catch (Exception e) {
            showErrorPopUp(ERROR, "Try check input type in Price text fields. It should be number.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Product product = this.getSelectedProduct();

        // When scene is loading set values to their textFields based on selectedProduct
        tfName.setText(product.getName());
        tfPrice.setText(product.getPrice());
        taDescription.setText(product.getDescription());
    }
}
