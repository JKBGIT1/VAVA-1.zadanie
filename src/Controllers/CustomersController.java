package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController extends HomepageController implements Initializable {
    @FXML // Table in CustomerScene.fxml scene will contains data about customers
    private TableView<Customer> customersTableView;

    @FXML // These columns are columns in the customerTableView
    private TableColumn<Customer, String> firstNameCol, lastNameCol, streetCol, houseNumberCol, cityCol, postalCodeCol;

    public CustomersController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Each column will have set value based on the private parameter in Customer Class
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        houseNumberCol.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        // This function display data in tableView
        this.displayInTable();
    }

    /*
     * Class methods
     */

    // This will display all customers in customerTableView
    private void displayInTable() {
        customersTableView.setItems(this.getCustomersObservableList());
    }

    // Changing scene from CustomersScene.fxml to EditCustomerScene.fxml
    public void editCustomerScene(ActionEvent event) {
        // On CustomersScene.fxml scene user has to select customer, which he/she wants to edit
        if (customersTableView.getSelectionModel().getSelectedItem() != null) {
            // Before changing scene we need to set proper scenePath and controller
            this.setScenePath(EDIT_CUSTOMER_SCENE);
            // Passing data about customers, products and invoices to next controller
            // Thanks to constructor we set selectedCustomer to new controller, which will be loaded with new scene
            EditCustomerController editCustomerController = new EditCustomerController(
                    this.getCustomersObservableList(),
                    this.getProductsObservableList(),
                    this.getInvoicesObservableList(),
                    customersTableView.getSelectionModel().getSelectedItem()
            );

            this.setController(editCustomerController);
            this.switchScene(event);
        } else { // If user didn't select customer, there will be an info message
            System.out.println("You have to choose a user.");
        }
    }

    // Changing scene from CustomersScene.fxml to CreateCustomerScene.fxml
    public void createCustomerScene(ActionEvent event) {
        this.setScenePath(CREATE_CUSTOMER_SCENE);
        // Passing data about customers, products and invoices to next controller
        CreateCustomerController createCustomerController = new CreateCustomerController(
                this.getCustomersObservableList(),
                this.getProductsObservableList(),
                this.getInvoicesObservableList()
        );

        this.setController(createCustomerController);
        this.switchScene(event);
    }
}
