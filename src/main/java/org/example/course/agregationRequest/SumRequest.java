package org.example.course.agregationRequest;

import jakarta.persistence.Query;
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
import org.example.course.Request3;
import org.example.course.entities.Assembly;
import org.example.course.entities.Components;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SumRequest implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Result> sum_request = new TableView<>();
    @FXML
    private TableColumn<Result, Long> id;
    @FXML
    private TableColumn<Result, Long> total_value;


    public void sum(ActionEvent event) {
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
        search_btn.setOnMouseClicked(event -> sum());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        total_value.setCellValueFactory(new PropertyValueFactory<>("total_value"));
    }

    @FXML
    private void sum() {
        System.out.println("sum");
        HibernateSession.sessionFactory().inTransaction(session -> {
//            Query query = session.createQuery("SELECT Assembly.id, sum(Assembly.amountToProduce * EndProduct.costPerThing) as total_value " +
//                    "from Assembly join EndProduct on Assembly.id = EndProduct.assembly.id group by Assembly.id");
            Query query = session.createQuery("SELECT a.id, sum(a.amountToProduce * e.costPerThing) as total_value " +
                    "from Assembly a join EndProduct e on a.id = e.assembly.id group by a.id");
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
            sum_request.setItems(providerObservableList);
            System.out.println(query.getResultList());
        });
    }
    protected class Result{
        private Long id;
        private Long total_value;

        public Result(Long id, Long total_value) {
            this.id = id;
            this.total_value = total_value;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getTotal_value() {
            return total_value;
        }

        public void setTotal_value(Long total_value) {
            this.total_value = total_value;
        }
    }
}
