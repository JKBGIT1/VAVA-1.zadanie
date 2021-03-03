package Controllers;

import Classes.Customer;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class CreateCustomerController extends HomepageController {
    @FXML
    private TextField tfFirstName, tfLastName, tfStreet, tfHouseNumber, tfCity, tfPostalCode;

    public void createCustomer(ActionEvent event) {
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

        // Add new customer to customers array
        System.out.println(customer.toString());
    }
}
