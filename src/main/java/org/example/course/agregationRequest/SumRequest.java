package org.example.course.agregationRequest;

import jakarta.persistence.Query;
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
import org.example.course.entities.Assembly;
import org.example.course.entities.Components;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SumRequest implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Components> sum_request = new TableView<Components>();
    @FXML
    private TableColumn<Assembly, Long> id;
    @FXML
    private TableColumn<Components, String> total_value;


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
            Query query = session.createQuery("SELECT Assembly.id, sum(Assembly.amountToProduce * EndProduct.costPerThing) as total_value " +
                    "from Assembly join EndProduct on Assembly.id = EndProduct.assembly.id group by Assembly.id");
            System.out.println(query.getResultList());
        });
    }
    private class Result{
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
