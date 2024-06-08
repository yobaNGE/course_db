package org.example.course;

import jakarta.persistence.Query;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.course.entities.EndProduct;
import org.example.course.entities.Provider;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Request1Controller implements Initializable {
    @FXML
    private TextField text_tf;
    @FXML
    private Button search_btn;
    @FXML
    private TableView<EndProduct> request1_table = new TableView<>();
    @FXML
    private TableColumn<EndProduct, Long> id;
    @FXML
    private TableColumn<EndProduct, Long> time_to_produce;
    @FXML
    private TableColumn<EndProduct, Long> amount;
    @FXML
    private TableColumn<EndProduct, Long> cost_per_thing;
    @FXML
    private TableColumn<EndProduct, Long> min_amount;
    @FXML
    private TableColumn<EndProduct, Long> assembly_id;

    @FXML
    protected void request1() {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery("from EndProduct where timeToProduce=:n order by id desc", EndProduct.class);
                query.setParameter("n", Long.parseLong(text_tf.getText()));
                ObservableList<EndProduct> providerObservableList =
                        FXCollections.observableArrayList(
                                query.getResultList()
                        );
                System.out.println(providerObservableList);
                request1_table.setItems(providerObservableList);
            });
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        time_to_produce.setCellValueFactory(new PropertyValueFactory<>("timeToProduce"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        cost_per_thing.setCellValueFactory(new PropertyValueFactory<>("costPerThing"));
        min_amount.setCellValueFactory(new PropertyValueFactory<>("minAmount"));
        assembly_id.setCellValueFactory(new PropertyValueFactory<>("assemblyId"));
//        request1();
    }
}
