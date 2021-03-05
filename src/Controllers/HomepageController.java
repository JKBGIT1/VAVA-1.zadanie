package Controllers;

import Classes.Customer;
import Classes.Invoice;
import Classes.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class HomepageController {

    public static final String CUSTOMERS = "Customers";
    public static final String PRODUCTS = "Products";
    public static final String INVOICES = "Invoices";
    public static final String HOME = "Home";

    // Header contexts of PopUps windows
    public static final String CREATION_SUCCESS = "Creation success";
    public static final String EDIT_SUCCESS = "Edit success";
    public static final String ERROR = "Something went wrong";
    public static final String INFORMATION = "Information";
    public static final String INPUT = "Enter requested data";

    // Paths to fxmls files, which are used, for switching between scenes
    public static final String CUSTOMERS_SCENE = "../FXMLs/CustomersScene.fxml";
    public static final String PRODUCTS_SCENE = "../FXMLs/ProductsScene.fxml";
    public static final String INVOICES_SCENE = "../FXMLs/InvoicesScene.fxml";
    public static final String HOMEPAGE = "../FXMLs/Homepage.fxml";
    public static final String CREATE_CUSTOMER_SCENE = "../FXMLs/CreateCustomerScene.fxml";
    public static final String EDIT_CUSTOMER_SCENE = "../FXMLs/EditCustomerScene.fxml";
    public static final String CREATE_PRODUCT_SCENE = "../FXMLs/CreateProductScene.fxml";
    public static final String EDIT_PRODUCT_SCENE = "../FXMLs/EditProductScene.fxml";
    public static final String DETAIL_INVOICE_SCENE = "../FXMLs/DetailInvoiceScene.fxml";
    public static final String CREATE_INVOICE_SCENE = "../FXMLs/CreateInvoiceScene.fxml";
    public static final String CREATE_INVOICE_SELECT_PRODUCTS_SCENE = "../FXMLs/CreateInvoiceSelectProductsScene.fxml";

    private String scenePath = "";
    private Object controller = null;

    // These attributes are needed for editing pages.
    private Customer selectedCustomer = null;
    private Product selectedProduct = null;

    // These attribute is needed for detail displaying of invoice
    private Invoice selectedInvoice = null;

    // List of all customers, which were created in system and will be displayed in CustomersScene.fxml scene table
    private ObservableList<Customer> customersObservableList = FXCollections.observableArrayList();
    private ObservableList<Product> productsObservableList = FXCollections.observableArrayList();
    private ObservableList<Invoice> invoicesObservableList = FXCollections.observableArrayList();

    public HomepageController(
            ObservableList<Customer> customersObservableList,
            ObservableList<Product> productsObservableList,
            ObservableList<Invoice> invoicesObservableList
    ) {
        this.setCustomersObservableList(customersObservableList);
        this.setProductsObservableList(productsObservableList);
        this.setInvoicesObservableList(invoicesObservableList);
    }

    /*
     * Getters and Setters
     */

    public String getScenePath() {
        return scenePath;
    }

    public void setScenePath(String scenePath) {
        this.scenePath = scenePath;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Invoice getSelectedInvoice() {
        return selectedInvoice;
    }

    public void setSelectedInvoice(Invoice selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
    }

    public ObservableList<Customer> getCustomersObservableList() {
        return customersObservableList;
    }

    public void setCustomersObservableList(ObservableList<Customer> customersObservableList) {
        this.customersObservableList = customersObservableList;
    }

    public ObservableList<Product> getProductsObservableList() {
        return productsObservableList;
    }

    public void setProductsObservableList(ObservableList<Product> productsObservableList) {
        this.productsObservableList = productsObservableList;
    }

    public ObservableList<Invoice> getInvoicesObservableList() {
        return invoicesObservableList;
    }

    public void setInvoicesObservableList(ObservableList<Invoice> invoicesObservableList) {
        this.invoicesObservableList = invoicesObservableList;
    }

    /*
     * End of Getters and Setters
     */

    /*
     * Converting methods
     */

    // NOTE: This function needs to be used inside try and catch
    public double convertStringToDouble(String stringNumber) {
        // Before parse replace commas with dots to prevent error
        double doubleNumber = Double.parseDouble(stringNumber.replaceAll(",","."));

        // If user didn't use zero as an input then we floor this number on two decimals
        if (doubleNumber != 0) {
            doubleNumber = Math.floor(doubleNumber * 100) / 100; // Taken from https://stackoverflow.com/questions/7747469/how-can-i-truncate-a-double-to-only-two-decimal-places-in-java
        }
        return doubleNumber;
    }

    // NOTE: This function needs to be used inside try and catch
    public String convertDateToString(Date date) {
        // Inspiration from https://www.javatpoint.com/java-date-to-string
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    // NOTE: This function needs to be used inside try and catch
    public String convertDoubleToString(double price) {
        return String.valueOf(price);
    }

    public Date convertStringToDate(String stringDate) {
        try {
            // Try to handle more types of date format
            stringDate = stringDate.replaceAll("/", ".");
            stringDate = stringDate.replaceAll("-", ".");
            stringDate = stringDate.replaceAll(",", ".");

            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(stringDate); // Inspiration from https://www.javatpoint.com/java-string-to-date
            return date;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /*
     * End of Converting methods
     */

    // This function is used in EditCustomerController and CreateCustomerContorller, when user click on Back button
    public void backToCustomersScene(ActionEvent event) {
        this.setControllerAndPathForCustomersScene();
        this.switchScene(event);
    }

    // This function is used in EditProductController and CreateProductContorller, when user click on Back button
    public void backToProductsScene(ActionEvent event) {
        this.setControllerAndPathForProductsScene();
        this.switchScene(event);
    }

    public void backToInvoicesScene(ActionEvent event) {
        this.setControllerAndPathForInvoicesScene();
        this.switchScene(event);
    }

    // Set controller and scenePath before changing to CustomersScene.fxml
    public void setControllerAndPathForCustomersScene() {
        this.setScenePath(CUSTOMERS_SCENE);
        // Passing data about customers, products and invoices to next controller
        CustomersController customersController = new CustomersController(
                this.getCustomersObservableList(),
                this.getProductsObservableList(),
                this.getInvoicesObservableList()
        );

        this.setController(customersController);
    }

    // Set controller and scenePath before changing to HomepageScene.fxml
    public void setControllerAndPathForProductsScene() {
        this.setScenePath(PRODUCTS_SCENE);
        // Passing data about customers, products and invoices to next controller
        ProductsController productsController = new ProductsController(
                this.getCustomersObservableList(),
                this.getProductsObservableList(),
                this.getInvoicesObservableList()
        );

        this.setController(productsController);
    }

    // Set controller and scenePath before changing to HomepageScene.fxml
    public void setControllerAndPathForInvoicesScene() {
        this.setScenePath(INVOICES_SCENE);
        // Passing data about customers, products and invoices to next controller
        InvoicesController invoicesController = new InvoicesController(
                this.getCustomersObservableList(),
                this.getProductsObservableList(),
                this.getInvoicesObservableList()
        );

        this.setController(invoicesController);
    }

    // Set controller and scenePath before changing to HomepageScene.fxml
    public void setControllerAndPathForHomepageScene() {
        this.setScenePath(HOMEPAGE);
        // Passing data about customers, products and invoices to next controller
        HomepageController homepageController = new HomepageController(
                this.getCustomersObservableList(),
                this.getProductsObservableList(),
                this.getInvoicesObservableList()
        );
        this.setController(homepageController);
    }

    // Change scene based on the navbar Button
    public void changeScene(ActionEvent event) {
        // Inspiration from https://stackoverflow.com/questions/49827633/javafx-get-the-clicked-button-value
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        this.setScenePathAndController(buttonText);

        this.switchScene(event);
    }

    // This function is invoke only if one of navbar button is clicked
    public void setScenePathAndController(String buttonText) {
        // Based on button text find out which scene should be set
        switch (buttonText) {
            case CUSTOMERS:
                this.setControllerAndPathForCustomersScene();
                break;
            case PRODUCTS:
                this.setControllerAndPathForProductsScene();
                break;
            case INVOICES:
                this.setControllerAndPathForInvoicesScene();
                break;
            case HOME:
                this.setControllerAndPathForHomepageScene();
                break;
        }
    }

    // When scenePath and controller is set program is going to change a scene on the screen
    public void switchScene(ActionEvent event) {
        try {
            if (!this.getScenePath().equals("")) {
                // This was taken from my school project in second grade on DBS
                // https://github.com/FIIT-DBS2020/project-hlavacka_muller/blob/master/src/GUI/SwitchScene.java
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(this.getScenePath()));
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                loader.setController(this.getController());

                Parent parent = loader.load();
                Scene scene = new Scene(parent);

                window.setScene(scene);
                window.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Setting cell values to TableView columns
     */

    public void setCellValuesForCustomersTableView(
            TableColumn<Customer, String> firstNameCol,
            TableColumn<Customer, String> lastNameCol,
            TableColumn<Customer, String> streetCol,
            TableColumn<Customer, String> houseNumberCol,
            TableColumn<Customer, String> cityCol,
            TableColumn<Customer, String> postalCodeCol
    ) {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        houseNumberCol.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
    }

    public void setCellValueForProductsTableView(
            TableColumn<Product, String> nameCol,
            TableColumn<Product, String> descriptionCol,
            TableColumn<Product, String> priceCol
    ) {
        // Each column will have set value based on the private parameter in Product Class
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /*
     * End of Setting cell values to TableView columns
     */

    /*
     * PopUps Windows
     */

    // Inspiration for PopUp windows on https://stackoverflow.com/questions/26341152/controlsfx-dialogs-deprecated-for-what/32618003#32618003
    public void showSuccessPopUp(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public void showErrorPopUp(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public int showInputDialog(String contentText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Fill text field");
        dialog.setHeaderText(INPUT);
        dialog.setContentText(contentText);

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            try {
                return Integer.parseInt(result.get());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return 1; // Default value will be 1, if user didn't enter int.
    }

    public boolean showConfirmationPopUp(String headerText, String contentText ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Choose an option");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            // If condition under this comment is true, user confirm action.
            if (result.get().getText().equals("OK")) {
                return true;
            }
        }

        return false;
    }

    /*
     * End of PopUps Windows
     */
}
