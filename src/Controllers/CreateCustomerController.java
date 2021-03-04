package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateCustomerController extends HomepageController {
    @FXML
    private TextField tfFirstName, tfLastName, tfStreet, tfHouseNumber, tfCity, tfPostalCode;

    public CreateCustomerController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
    }

    public void createCustomer() {
        try {
            // Create new customer object
            // Every parameter in Customer object is String, so we don't need to check their types,
            // because we are getting text from textFields
            Customer customer = new Customer(
                    tfFirstName.getText(),
                    tfLastName.getText(),
                    tfStreet.getText(),
                    tfHouseNumber.getText(),
                    tfCity.getText(),
                    tfPostalCode.getText()
            );

            // Make textFields blank again
            tfFirstName.setText("");
            tfLastName.setText("");
            tfStreet.setText("");
            tfHouseNumber.setText("");
            tfCity.setText("");
            tfPostalCode.setText("");

            // Let user know, that new customer was succesfully created
            this.showSuccessPopUp(CREATION_SUCCESS, "Customer was successfully created.");

            // Add newly created customer to list of all customers
            this.getCustomersObservableList().add(customer);
        } catch (Exception e) {
            showErrorPopUp(ERROR, "Try check input types in text fields.");
            e.printStackTrace();
        }
    }
}
