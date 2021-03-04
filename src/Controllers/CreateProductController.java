package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateProductController extends HomepageController{

    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfName, tfPrice;

    public CreateProductController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
    }

    public void createProduct() {
        try {
            // Create new product object
            // We need to convert string from tfPrice to double
            // If program can't convert it, this catch function will take care of error message
            Product product = new Product(
                    tfName.getText(),
                    taDescription.getText(),
                    this.convertStringToDouble(tfPrice.getText())
            );

            // Make textFields and textArea blank again
            tfName.setText("");
            tfPrice.setText("");
            taDescription.setText("");

            // Let user know, that new customer was succesfully created
            this.showSuccessPopUp(CREATION_SUCCESS, "Product was successfully created.");

            // Add newly created customer to list of all customers
            this.getProductsObservableList().add(product);
        } catch (Exception e) {
            showErrorPopUp(ERROR, "Try check input type in Price text fields. It needs to be number");
            System.out.println(e.getMessage());
        }
    }
}
