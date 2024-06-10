package org.example.course.searchRequest;

import jakarta.persistence.Query;
import jakarta.persistence.Timeout;
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
import org.example.course.HibernateSession;
import org.example.course.entities.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Request5 implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Result> request5_table = new TableView<Result>();
    @FXML
    private TableColumn<Result, Long> articul;
    @FXML
    private TableColumn<Result, Long> scope_of_supply;
    @FXML
    private TableColumn<Result, Timestamp> date_of_supply;
    @FXML
    private TextField date_of_supply_tf;
    @FXML
    private TextField scope_of_supply_tf;

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
                Query query = session.createQuery(
                        "SELECT c.articul, csc.scopeOfSupply, sc.dateOfSupply " +
                                "FROM CheckSaleComponent csc JOIN Components c " +
                                "ON c.articul = csc.articul " +
                                "JOIN SaleComponent sc " +
                                "ON csc.id = sc.checkSaleComponent.id " +
                                "WHERE sc.dateOfSupply=:n and csc.scopeOfSupply=:b");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateFormat.parse(date_of_supply_tf.getText());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Timestamp timestamp = new Timestamp(date.getTime());
                query.setParameter("n", timestamp);
                query.setParameter("b", Long.parseLong(scope_of_supply_tf.getText()));
                List<Result> list = new ArrayList<>(5);
                //хз че с циклом
                for (Object o : query.getResultList()) {
                    Object[] row = (Object[]) o;
                    list.add(new Result((Long) row[0], (Long) row[1], (Timestamp) row[2]));
                    System.out.println(row[0] + " " + row[1]);
                }
                ObservableList<Result> providerObservableList =
                        FXCollections.observableArrayList(
                                list
                        );
                System.out.println(providerObservableList);
                request5_table.setItems(providerObservableList);
                System.out.println(query.getResultList());
            });
        });
    }

    protected class Result {
        private long articul;
        private long scope_of_supply;
        private Timestamp date_of_supply;

        public Result(long articul, long scope_of_supply, Timestamp date_of_supply) {
            this.articul = articul;
            this.scope_of_supply = scope_of_supply;
            this.date_of_supply = date_of_supply;
        }

        public long getArticul() {
            return articul;
        }

        public void setArticul(long articul) {
            this.articul = articul;
        }

        public long getScope_of_supply() {
            return scope_of_supply;
        }

        public void setScope_of_supply(long scope_of_supply) {
            this.scope_of_supply = scope_of_supply;
        }

        public Timestamp getDate_of_supply() {
            return date_of_supply;
        }

        public void setDate_of_supply(Timestamp date_of_supply) {
            this.date_of_supply = date_of_supply;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        articul.setCellValueFactory(new PropertyValueFactory<>("articul"));
        scope_of_supply.setCellValueFactory(new PropertyValueFactory<>("scope_of_supply"));
        date_of_supply.setCellValueFactory(new PropertyValueFactory<>("date_of_supply"));
    }
}
