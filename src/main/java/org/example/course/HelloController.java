package org.example.course;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.course.entities.Provider;
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        //run on main thread
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Provider provider = new Provider("Cum streets 12", 1100L, "mrs.pooper", null);
                session.persist(provider);
                session.flush();
            });
            System.out.println();
            //welcomeText.setText("Welcome to JavaFX Application!");
        });
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}