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

public class CreateInvoiceSelectProductsController extends HomepageController implements Initializable {
    @FXML // allProductsTableView contains all products in system and selectedProductsTableView contains all products in current Invoice
    private TableView<Product> allProductsTableView, selectedProductsTableView;

    @FXML // These columns are columns in the allProductsTableView and selectedProductsTableView
    private TableColumn<Product, String> nameColAll, descriptionColAll, priceColAll,
            nameColSelected, descriptionColSelected, priceColSelected;
    @FXML
    private TableColumn<Product, Integer> countColSelected;

    public CreateInvoiceSelectProductsController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList,
            Invoice selectedInvoice
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
        this.setSelectedInvoice(selectedInvoice);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // This will set cells to tableView with all products
        this.setCellValueForProductsTableView(nameColAll, descriptionColAll, priceColAll);
        // This will set cells to tableView with selected products in Invoice
        this.setCellValueForProductsTableView(nameColSelected, descriptionColSelected, priceColSelected);
        // There is one more cell in selected tableView
        countColSelected.setCellValueFactory(new PropertyValueFactory<>("count"));

        // This function display data in tableView for all products
        this.displayInTable(allProductsTableView, this.getProductsObservableList());
        // This function display data in tableView for selected products
        this.displayInTable(selectedProductsTableView, this.getSelectedInvoice().getInvoiceProductsObservableList());
    }

    private void displayInTable(TableView<Product> tableView, ObservableList<Product> products) {
        tableView.setItems(products);
    }

    public void backToCreateInvoiceScene(ActionEvent event) {
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

    public void addProductToInvoice() {

    }

    public void createProductScene(ActionEvent event) {

    }
}
