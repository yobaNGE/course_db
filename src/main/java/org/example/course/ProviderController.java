package org.example.course;

import jakarta.persistence.Table;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.course.entities.Provider;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProviderController {
    @FXML
    private TableView<Provider> provider_table = new TableView<>();
    @FXML
    private TableColumn<Provider, Long> id;
    @FXML
    private TableColumn<Provider, Long> amountofdebt;
    @FXML
    private TableColumn<Provider, String> adress;
    @FXML
    private TableColumn<Provider, String> name;

    @FXML
    protected void provider (){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        amountofdebt.setCellValueFactory(new PropertyValueFactory<>("amountOfDebt"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                ObservableList<Provider> providerObservableList =
                        FXCollections.observableArrayList(
                                session.createQuery("from Provider", Provider.class).list()
                        );
                System.out.println(providerObservableList);
                provider_table.setItems(providerObservableList);
            });
            System.out.println();
        });
    }
}