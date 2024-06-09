package org.example.course;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.course.entities.Components;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SumRequest implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Components> sum_request = new TableView<Components>();
    @FXML
    private TableColumn<Components, Long> id;
    @FXML
    private TableColumn<Components, String> total_value;


    public void sum(ActionEvent event) {
    }

    @FXML
    void goBack(ActionEvent event) throws IOException { // GO BACK!!!
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        ;
        stage.setTitle("Main");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        total_value.setCellValueFactory(new PropertyValueFactory<>("total_value"));
    }


}
