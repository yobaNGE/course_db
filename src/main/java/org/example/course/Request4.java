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
import org.example.course.entities.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Request4 implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Components> request4_table = new TableView<Components>();
    @FXML
    private TableColumn<Firm, Long> firm_name;
    @FXML
    private TableColumn<Hire, String> salary;
    @FXML
    private TableColumn<TradeAgent, String> name;
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

    public void request4(ActionEvent event) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firm_name.setCellValueFactory(new PropertyValueFactory<>("firm_name"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
