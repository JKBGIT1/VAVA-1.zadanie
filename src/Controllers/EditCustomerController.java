package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditCustomerController extends CustomersController {

    @FXML
    private TextField tfFirstName, tfLastName, tfStreet, tfHouseNumber, tfCity, tfPostalCode;

    public void saveEditedCustomer(ActionEvent event) {
        System.out.println("Edited customer.");
    }
}
