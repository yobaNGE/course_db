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
import org.example.course.agregationRequest.SumRequest;
import org.example.course.entities.CheckSaleEndProduct;
import org.example.course.entities.EndProduct;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Request2 implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Result> request2_table = new TableView<>();
    @FXML
    private TableColumn<Result, Long> id;
    @FXML
    private TableColumn<Result, Long> scope_of_supply;
    @FXML
    private TextField scope;
    @FXML
    private TextField time;
    @FXML
    private Label label;

    public void request2(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        scope_of_supply.setCellValueFactory(new PropertyValueFactory<>("scope_of_supply"));
    }

    @FXML
    private void search() {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery("SELECT e.id, c.scopeOfSupply " +
                        "FROM EndProduct e JOIN CheckSaleEndProduct c " +
                        "ON e.id = c.articul " +
                        "WHERE e.timeToProduce =:n AND c.scopeOfSupply =:b");
                query.setParameter("n", Long.parseLong(time.getText()));
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
                request2_table.setItems(providerObservableList);
                System.out.println(query.getResultList());
                if (providerObservableList.isEmpty()){
                    label.setText("Готовых изделий с такими данными нет");
                }
                else {
                    label.setText("");
                }
            });
        });
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
