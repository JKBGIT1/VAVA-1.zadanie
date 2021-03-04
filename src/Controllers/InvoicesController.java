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
import java.util.Date;
import java.util.ResourceBundle;

public class InvoicesController extends HomepageController implements Initializable {
    @FXML // Table in InvoicesScene.fxml scene will contains data about invoices
    private TableView<Invoice> invoicesTableView;

    @FXML // These columns are columns in the invoicesTableView
    private TableColumn<Invoice, String> customerFullNameCol, dateCol;
    @FXML
    private TableColumn<Invoice, Double> totalPriceCol;

    public InvoicesController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Each column will have set value based on the private parameter in Customer Class
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerFullNameCol.setCellValueFactory(new PropertyValueFactory<>("customerFullName"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        // This function display data in tableView
        this.displayInTable();
    }

    private void displayInTable() {
        invoicesTableView.setItems(this.getInvoicesObservableList());
    }

    // Changing scene from InvoicesScene.fxml to DetailInvoiceScene.fxml
    public void detailInvoiceScene(ActionEvent event) {
        // In InvoicesScene.fxml scene user has to select invoice, which he/she wants to display in detail.
        if (invoicesTableView.getSelectionModel().getSelectedItem() != null) {
            // Before changing scene we need to set proper scenePath and controller
            this.setScenePath(DETAIL_INVOICE_SCENE);
            // Passing data about customers, products and invoices to next controller
            // Thanks to constructor we set selectedInvoice to new controller, which will be loaded with new scene
            DetailInvoiceController detailInvoiceController = new DetailInvoiceController(
                    this.getCustomersObservableList(),
                    this.getProductsObservableList(),
                    this.getInvoicesObservableList(),
                    invoicesTableView.getSelectionModel().getSelectedItem()
            );

            this.setController(detailInvoiceController);
            this.switchScene(event);
        } else { // If user didn't select customer, there will be an info message
            this.showSuccessPopUp(INFORMATION, "You need to select an invoice from table.");
        }
    }

    // Changing scene from InvoicesScene.fxml to CreateInvoiceScene.fxml
    public void createInvoiceScene(ActionEvent event) {
        this.setScenePath(CREATE_INVOICE_SCENE);
        // Passing data about customers, products and invoices to next controller
        CreateInvoiceController createInvoiceController = new CreateInvoiceController(
                this.getCustomersObservableList(),
                this.getProductsObservableList(),
                this.getInvoicesObservableList(),
                this.getSelectedInvoice()
        );

        this.setController(createInvoiceController);
        this.switchScene(event);
    }
}
