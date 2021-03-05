package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailInvoiceController extends HomepageController implements Initializable {

    @FXML
    private TableView<Product> productsInInvoiceTableView;
    @FXML
    private Label dateLabel, totalPriceLabel, firstNameLabel, lastNameLabel,
                    houseNumberLabel, streetLabel, cityLabel, postalCodeLabel;
    @FXML // These columns are columns in the productsInInvoiceTableView
    private TableColumn<Product, String> nameCol, descriptionCol, countCol, priceCol;

    public DetailInvoiceController(
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
        try {
            Invoice invoice = this.getSelectedInvoice();
            Customer invoiceCustomer = invoice.getCustomer();

            // Set labels text form Invoice object
            // Before setting text to label it will convert Date to String
            dateLabel.setText(this.convertDateToString(invoice.getDateInDate()));
            // Before setting text to label it will convert double to String
            totalPriceLabel.setText(this.convertDoubleToString(invoice.getTotalPrice()));
            // Set labels text from Customer object, which figures as attribute in Invoice object
            firstNameLabel.setText(invoiceCustomer.getFirstName());
            lastNameLabel.setText(invoiceCustomer.getLastName());
            houseNumberLabel.setText(invoiceCustomer.getHouseNumber());
            streetLabel.setText(invoiceCustomer.getStreet());
            cityLabel.setText(invoiceCustomer.getCity());
            postalCodeLabel.setText(invoiceCustomer.getPostalCode());

            // Each column will have set value based on the private parameter in Product Class
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("totalProductsPrice"));

            // This function display data in tableView
            this.displayInTable(invoice);
        } catch (Exception e) {
            this.showErrorPopUp(ERROR, "Check if data is in right format 'dd.mm.yyyy'");
            e.printStackTrace();
        }
    }

    private void displayInTable(Invoice invoice) {
        // Table will display all products, which belongs to selectedInvoice
        productsInInvoiceTableView.setItems(invoice.getInvoiceProductsObservableList());
    }
}
