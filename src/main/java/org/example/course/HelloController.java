package org.example.course;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.course.entities.Provider;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button go;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException{
        Stage stage = (Stage) go.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("request1.fxml"));
        ;
        stage.setTitle("Request1");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void goProvider(ActionEvent event) throws IOException {
        Stage stage = (Stage) go.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("provider.fxml"));
                ;
        stage.setTitle("Provider");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void goExist(ActionEvent event) throws IOException{
        Stage stage = (Stage) go.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("existRequest.fxml"));
        ;
        stage.setTitle("Exist");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void goSum(ActionEvent event) throws IOException{
        Stage stage = (Stage) go.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("sumRequest.fxml"));
        ;
        stage.setTitle("Sum");
        stage.setScene(new Scene(root));
        stage.show();
    }
}