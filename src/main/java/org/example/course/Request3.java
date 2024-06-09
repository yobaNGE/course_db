package org.example.course;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.course.entities.CheckSaleComponent;
import org.example.course.entities.CheckSaleEndProduct;
import org.example.course.entities.Components;
import org.example.course.entities.EndProduct;
import org.example.course.searchRequest.Request2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Request3 implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Result> request3_table = new TableView<>();
    @FXML
    private TableColumn<Result, Long> articul;
    @FXML
    private TableColumn<Result, Long> scope_of_supply;
    @FXML
    private TextField scope;
    @FXML
    private TextField time;
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
    @FXML
    private void search() {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery("SELECT c.articul, cs.scopeOfSupply " +
                        "FROM Components c JOIN CheckSaleComponent cs " +
                        "ON c.articul = cs.articul " +
                        "WHERE c.name = :n AND cs.scopeOfSupply = :b");
                query.setParameter("n", time.getText());
                query.setParameter("b", Long.parseLong(scope.getText()));
                List<Result> list = new ArrayList<>(5);
                for (Object o : query.getResultList()) {
                    Object[] row = (Object[]) o;
                    list.add(new Result((Long) row[0], (Long) row[1]));
                    System.out.println(row[0] + " " + row[1]);
                }
                ObservableList<Result> providerObservableList =
                        FXCollections.observableArrayList(
                                list
                        );
                System.out.println(providerObservableList);
                request3_table.setItems(providerObservableList);
                System.out.println(query.getResultList());
            });
        });
    }
    public void request3(ActionEvent event) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        articul.setCellValueFactory(new PropertyValueFactory<>("articul"));
        scope_of_supply.setCellValueFactory(new PropertyValueFactory<>("scope_of_supply"));
    }
    protected class Result {
        private long id;
        private long scope_of_supply;

        public Result(long id, long scope_of_supply) {
            this.id = id;
            this.scope_of_supply = scope_of_supply;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getScope_of_supply() {
            return scope_of_supply;
        }

        public void setScope_of_supply(long scope_of_supply) {
            this.scope_of_supply = scope_of_supply;
        }
    }
}
