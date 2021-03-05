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
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class CreateInvoiceController extends HomepageController implements Initializable {

    @FXML // Table in CreateInvoiceController.fxml scene will contains data about customers
    private TableView<Customer> customersTableView;

    @FXML // These columns are columns in the customerTableView
    private TableColumn<Customer, String> firstNameCol, lastNameCol, streetCol, houseNumberCol, cityCol, postalCodeCol;

    @FXML
    private TextField tfDate;

    public CreateInvoiceController(
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
        // If new Invoice was already created set text to textFields
        try {
            if (this.getSelectedInvoice() != null) {
                Invoice currentInvoice = this.getSelectedInvoice();
                tfDate.setText(currentInvoice.getDate());
            }

            // Each column will have set value based on the private parameter in Customer Class
            this.setCellValuesForCustomersTableView(firstNameCol, lastNameCol, streetCol, houseNumberCol, cityCol, postalCodeCol);

            // This function display data in tableView
            this.displayInTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayInTable() {
        customersTableView.setItems(this.getCustomersObservableList());
    }

    public void selectProductsForInvoiceScene(ActionEvent event) {
        try {
            if (this.getSelectedInvoice() != null) { // Invoice was already created and it is stored in selectedInvoice
                // Need to check if user wants to change some data in Invoice
                Date date = this.convertStringToDate(tfDate.getText()); // If user enter wrong date format next condition will take care of it
                if (date == null) {
                    this.showErrorPopUp(ERROR, "Check date format in date of issue. It should be like 'dd.mm.yyyy'");
                    return;
                }

                this.getSelectedInvoice().setDate(date);

                // User select a new customer for this Invoice
                if (customersTableView.getSelectionModel().getSelectedItem() != null) {
                    this.getSelectedInvoice().setCustomer(customersTableView.getSelectionModel().getSelectedItem());
                }

                this.changeToCreateInvoiceSelectProductsScene(event, this.getSelectedInvoice());
            } else if (customersTableView.getSelectionModel().getSelectedItem() != null) {
                Date date = this.convertStringToDate(tfDate.getText()); // If user enter wrong date format next condition will take care of it
                if (date == null) {
                    this.showErrorPopUp(ERROR, "Check date format in date of issue. It should be like 'dd.mm.yyyy'");
                    return;
                }

                // Get selected customer and create new instance of it
                Customer selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();
                Customer newCustomer = new Customer(
                        selectedCustomer.getFirstName(),
                        selectedCustomer.getLastName(),
                        selectedCustomer.getStreet(),
                        selectedCustomer.getHouseNumber(),
                        selectedCustomer.getCity(),
                        selectedCustomer.getPostalCode()
                );

                // Before setting selectedInvoice in new Controller program creates new object of Invoice
                this.changeToCreateInvoiceSelectProductsScene(event, new Invoice(date, newCustomer));

            } else {
                this.showSuccessPopUp(INFORMATION, "You need to select a customer from table.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeToCreateInvoiceSelectProductsScene(ActionEvent event, Invoice invoice) {
        // Before changing scene we need to set proper scenePath and controller
        this.setScenePath(CREATE_INVOICE_SELECT_PRODUCTS_SCENE);
        // Passing data about customers, products and invoices to next controller
        // Thanks to constructor we set selectedInvoice to new controller, which will be loaded with new scene
        CreateInvoiceSelectProductsController newController = new CreateInvoiceSelectProductsController(
                this.getCustomersObservableList(),
                this.getProductsObservableList(),
                this.getInvoicesObservableList(),
                invoice
        );

        this.setController(newController);
        this.switchScene(event);
    }
}
