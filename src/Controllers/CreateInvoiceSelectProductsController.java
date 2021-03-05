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

    public void removeProductFromInvoice() {
        if (selectedProductsTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(INFORMATION, "You need to select product in 'Products in invoice' table, if you want to remove it.");
        } else {
            // User needs to confirm delete action
            if (this.showConfirmationPopUp("Remove product", "Do you really want to remove product form Invoice?")) {
                ObservableList<Product> selectedProducts = this.getSelectedInvoice().getInvoiceProductsObservableList();
                // Remove selected item from products in current Invoice
                selectedProducts.remove(selectedProductsTableView.getSelectionModel().getSelectedItem());
            }
        }
    }

    public void addProductToInvoice() {
        if (allProductsTableView.getSelectionModel().getSelectedItem() == null) {
            this.showErrorPopUp(INFORMATION, "You need to select product in 'All products' table, if you want to add it to Invoice.");
        } else {
            ObservableList<Product> selectedProducts = this.getSelectedInvoice().getInvoiceProductsObservableList();
            // Get selected product from list of all products
            Product selectedItem = allProductsTableView.getSelectionModel().getSelectedItem();

            // Because we are chaning count value we need to create a new object and copy attributes from selected product
            Product newProduct = new Product(
                    selectedItem.getName(),
                    selectedItem.getDescription(),
                    this.convertStringToDouble(selectedItem.getPrice())
            );
            newProduct.setCount(this.showInputDialog("Enter number of selected product: "));

            // Add new instance of selected product from All products table to all products in current Invoice
            selectedProducts.add(newProduct);
        }
    }

    public void createProductScene(ActionEvent event) {
        Invoice selectedInvoice = this.getSelectedInvoice();
        ObservableList<Product> productsInInvoice = selectedInvoice.getInvoiceProductsObservableList();

        double invoiceTotalPrice = 0;

        // loop through every single product in selectedInvoice
        for (Product product : productsInInvoice) {
            // Need to parse price from String to double before adding to invoiceTotalPrice
            invoiceTotalPrice += (Double.parseDouble(product.getPrice()) * product.getCount());
        }

        // Set totalPrice to selectedInvoice
        selectedInvoice.setTotalPrice(invoiceTotalPrice);
        // Set customer full name for better info in Invoice tableView
        String customerFullName = selectedInvoice.getCustomer().getFirstName() + " " + selectedInvoice.getCustomer().getLastName();
        selectedInvoice.setCustomerFullName(customerFullName);
        // Add selectedInvoice to list with all invoices in this system
        this.getInvoicesObservableList().add(selectedInvoice);
        // Inform user about Invoice creation
        this.showSuccessPopUp(CREATION_SUCCESS, "Invoice was successfully created");
        // Finally change to Invoices scene
        this.setControllerAndPathForInvoicesScene();
        this.switchScene(event);
    }
}
