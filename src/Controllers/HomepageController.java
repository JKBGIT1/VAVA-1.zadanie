package Controllers;

import Classes.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    public static final String CUSTOMERS = "Customers";
    public static final String PRODUCTS = "Products";
    public static final String INVOICES = "Invoices";
    public static final String HOME = "Home";

    public static final String CUSTOMERS_SCENE = "../FXMLs/CustomersScene.fxml";
    public static final String PRODUCTS_SCENE = "../FXMLs/ProductsScene.fxml";
    public static final String INVOICES_SCENE = "../FXMLs/InvoicesScene.fxml";
    public static final String HOMEPAGE = "../FXMLs/Homepage.fxml";
    public static final String CREATE_CUSTOMER_SCENE = "../FXMLs/CreateCustomerScene.fxml";

    private String scenePath = "";
    private Object controller = null;

    private Customer selectedCustomer = null;

    // List of all customers, which were created in system and will be displayed in CustomersScene.fxml scene table
    private ObservableList<Customer> customersObservableList = FXCollections.observableArrayList();

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

    public ObservableList<Customer> getCustomersObservableList() {
        return customersObservableList;
    }

    public void setCustomersObservableList(ObservableList<Customer> customersObservableList) {
        this.customersObservableList = customersObservableList;
    }

    /*
     * End of Getters and Setters
     */

    public void backToCustomersScene(ActionEvent event) {
        // When returning from EditCustomerScene.fxml scene we need to set selectedCustomer back to null
        // it doesn't have impact on other scenes, so we can set it to null even when we go from CreateCustomerScene.fxml scene
        this.setScenePath(CUSTOMERS_SCENE);
        this.setController(new CustomersController());
        this.changeScene(event);
    }

    // Change scene based on the navbar Button
    public void changeScene(ActionEvent event) {
        try {
            // Inspiration from https://stackoverflow.com/questions/49827633/javafx-get-the-clicked-button-value
            Button clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();

            this.setScenePathAndController(buttonText);

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

    public void setScenePathAndController(String buttonText) {
        // Based on button text find out which scene should be set
        switch (buttonText) {
            case CUSTOMERS:
                this.setScenePath(CUSTOMERS_SCENE);
                this.setController(new CustomersController());
                break;
            case PRODUCTS:
                this.setScenePath(PRODUCTS_SCENE);
                this.setController(new ProductsController());
                break;
            case INVOICES:
                this.setScenePath(INVOICES_SCENE);
                this.setController(new InvoicesController());
                break;
            case HOME:
                this.setScenePath(HOMEPAGE);
                this.setController(new HomepageController());
                break;
        }
    }
}
