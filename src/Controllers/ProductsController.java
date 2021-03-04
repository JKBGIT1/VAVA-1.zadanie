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

public class ProductsController extends HomepageController implements Initializable {
    @FXML // Table in ProductsScene.fxml scene will contains data about products
    private TableView<Product> productsTableView;

    @FXML // These columns are columns in the productsTableView
    private TableColumn<Product, String> nameCol, descriptionCol, priceCol;

    public ProductsController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Each column will have set value based on the private parameter in Product Class
        this.setCellValueForProductsTableView(nameCol, descriptionCol, priceCol);

        // This function display data in tableView
        this.displayInTable();
    }

    /*
     * Classes methods
     */

    // This will display all products in productsTableView
    private void displayInTable() {
        productsTableView.setItems(this.getProductsObservableList());
    }

    // Changing scene from ProductsScene.fxml to EditProductScene.fxml
    public void editProductScene(ActionEvent event) {
        // On CustomersScene.fxml scene user has to select customer, which he/she wants to edit
        if (productsTableView.getSelectionModel().getSelectedItem() != null) {
            // Before changing scene we need to set proper scenePath and controller
            this.setScenePath(EDIT_PRODUCT_SCENE);
            // Passing data about customers, products and invoices to next controller
            // Thanks to constructor we set selectedCustomer to new controller, which will be loaded with new scene
            EditProductController editProductController = new EditProductController(
                    this.getCustomersObservableList(),
                    this.getProductsObservableList(),
                    this.getInvoicesObservableList(),
                    productsTableView.getSelectionModel().getSelectedItem()
            );

            this.setController(editProductController);
            this.switchScene(event);
        } else { // If user didn't select customer, there will be an info message
            this.showSuccessPopUp(INFORMATION, "You need to select a product from table.");
        }
    }

    // Changing scene from ProductsScene.fxml to CreateProductScene.fxml
    public void createProductScene(ActionEvent event) {
        this.setScenePath(CREATE_PRODUCT_SCENE);
        // Passing data about customers, products and invoices to next controller
        CreateProductController createProductController = new CreateProductController(
                this.getCustomersObservableList(),
                this.getProductsObservableList(),
                this.getInvoicesObservableList()
        );

        this.setController(createProductController);
        this.switchScene(event);
    }
}
