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

public class Request5 implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Components> request5_table = new TableView<Components>();
    @FXML
    private TableColumn<Components, Long> articul;
    @FXML
    private TableColumn<CheckSaleComponent, String> scope_of_supply;
    @FXML
    private TableColumn<SaleComponent, String> date_of_supply;
    @FXML
    void goBack(ActionEvent event) throws IOException { // GO BACK!!!
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("/org/example/course/hello-view.fxml"));
        ;
        stage.setTitle("Main");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void request5(ActionEvent event) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        articul.setCellValueFactory(new PropertyValueFactory<>("articul"));
        scope_of_supply.setCellValueFactory(new PropertyValueFactory<>("scope_of_supply"));
        date_of_supply.setCellValueFactory(new PropertyValueFactory<>("date_of_supply"));
    }
}
