package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerController extends HomepageController implements Initializable {

    @FXML
    private TextField tfFirstName, tfLastName, tfStreet, tfHouseNumber, tfCity, tfPostalCode;

    public EditCustomerController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList,
            Customer selectedCustomer
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
        this.setSelectedCustomer(selectedCustomer);
    }

    public void saveEditedCustomer() {
        // Change parameters to selectedCustomer based on textFields
        Customer customer = this.getSelectedCustomer();

        try {
            customer.setFirstName(tfFirstName.getText());
            customer.setLastName(tfLastName.getText());
            customer.setStreet(tfStreet.getText());
            customer.setHouseNumber(tfHouseNumber.getText());
            customer.setCity(tfCity.getText());
            customer.setPostalCode(tfPostalCode.getText());

            // Let user know, that customer editing was succesfull
            this.showSuccessPopUp(CREATION_SUCCESS, "Customer was successfully edited.");
        } catch (Exception e) {
            showErrorPopUp(ERROR, "Try check input types in text fields.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Customer customer = this.getSelectedCustomer();

        // When scene is loading set values to their textFields based on selectedCustomer
        tfFirstName.setText(customer.getFirstName());
        tfLastName.setText(customer.getLastName());
        tfStreet.setText(customer.getStreet());
        tfHouseNumber.setText(customer.getHouseNumber());
        tfCity.setText(customer.getCity());
        tfPostalCode.setText(customer.getPostalCode());
    }
}
