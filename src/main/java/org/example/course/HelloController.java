package org.example.course;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        //run on main thread
        Platform.runLater(() -> {
            HibernateSession.sessionFactory();
            //welcomeText.setText("Welcome to JavaFX Application!");
        });
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}