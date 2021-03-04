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
    private TextField tfDate, tfTotalPrice;

    public CreateInvoiceController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList
    ) {
        super(customersObservableList, productsObservableList, invoicesObservableList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Each column will have set value based on the private parameter in Customer Class
        this.setCellValuesForCustomersTableView(firstNameCol, lastNameCol, streetCol, houseNumberCol, cityCol, postalCodeCol);

        // This function display data in tableView
        this.displayInTable();
    }

    private void displayInTable() {
        customersTableView.setItems(this.getCustomersObservableList());
    }

    public void selectProductsForInvoiceScene(ActionEvent event) {
        try {
            if (customersTableView.getSelectionModel().getSelectedItem() != null) {
                Date date = this.convertStringToDate(tfDate.getText()); // If user enter wrong date format next condition will take care of it
                if (date == null) {
                    this.showErrorPopUp(ERROR, "Check date format in date of issue. It should be like 'dd.mm.yyyy'");
                    return;
                }

                double totalPrice = this.convertStringToDouble(tfTotalPrice.getText()); // If user didn't enter number catch will handle it
                Customer selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();

                // Before changing scene we need to set proper scenePath and controller
                this.setScenePath(CREATE_INVOICE_SELECT_PRODUCTS_SCENE);
                // Passing data about customers, products and invoices to next controller
                // Thanks to constructor we set selectedInvoice to new controller, which will be loaded with new scene
                CreateInvoiceSelectProductsController newController = new CreateInvoiceSelectProductsController(
                        this.getCustomersObservableList(),
                        this.getProductsObservableList(),
                        this.getInvoicesObservableList(),
                        new Invoice(date, totalPrice, selectedCustomer) // Before setting selectedInvoice program creates new object of it
                );

                this.setController(newController);
                this.switchScene(event);
            } else {
                this.showSuccessPopUp(INFORMATION, "You need to select a customer from table.");
            }
        } catch (Exception e) {
            this.showErrorPopUp(ERROR, "Enter number in Total price text field.");
            System.out.println(e.getMessage());
        }
    }
}
