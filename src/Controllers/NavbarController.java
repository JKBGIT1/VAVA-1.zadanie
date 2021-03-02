package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NavbarController {
    public void changeScene(ActionEvent event) {
        try {
            // Inspiration from https://stackoverflow.com/questions/49827633/javafx-get-the-clicked-button-value
            Button clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();

            String scenePath = "";

            // Based on button text find out which scene should be set
            switch (buttonText) {
                case "Customers":
                    scenePath = "../FXMLS/CustomersScene.fxml";
                    break;
                case "Products":
                    scenePath = "../FXMLS/ProductsScene.fxml";
                    break;
                case "Invoices":
                    scenePath = "../FXMLS/InvoicesScene.fxml";
                    break;
            }

            // If scenePath isn't empty string change to selected scene
            if (!scenePath.equals("")) {
                Parent parent = FXMLLoader.load(getClass().getResource(scenePath));
                Scene newScene = new Scene(parent);

                // This was taken from my OOP project in first grade. Repository is private.
                // https://github.com/OOP-FIIT/oop-2019-pia-08-a-povazanova-JKBGIT1/blob/master/src/Controllers/Controller.java
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(newScene);
                window.show();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
