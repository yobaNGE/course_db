package org.example.course.searchRequest;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.course.HibernateSession;
import org.example.course.entities.EndProduct;
import org.example.course.entities.Provider;
import org.hibernate.SessionFactory;

import java.io.IOException;
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
    private Button back;
    @FXML
    private Label label;

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
                if (providerObservableList.isEmpty()){
                    label.setText("Продукта с таким временем изготовления нет");
                }
                else {
                    label.setText("");
                }
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
}
