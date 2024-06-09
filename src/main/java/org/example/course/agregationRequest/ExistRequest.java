package org.example.course.agregationRequest;

import jakarta.persistence.Query;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.example.course.HibernateSession;
import org.example.course.entities.Components;
import org.example.course.entities.EndProduct;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExistRequest implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Components> exist_request = new TableView<Components>();
    @FXML
    private TableColumn<Components, Long> cost_per_thing;
    @FXML
    private TableColumn<Components, String> name;

    @FXML
    protected void exist() {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery("SELECT DISTINCT name, costPerThing FROM Components WHERE EXISTS (SELECT 1 FROM Components)", Components.class);
                ObservableList<Components> providerObservableList =
                        FXCollections.observableArrayList(
                                query.getResultList()
                        );
                System.out.println(providerObservableList);
                exist_request.setItems(providerObservableList);
            });
        });

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cost_per_thing.setCellValueFactory(new PropertyValueFactory<>("costPerThing"));
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
}
