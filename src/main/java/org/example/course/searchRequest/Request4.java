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
import org.example.course.entities.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Request4 implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Result> table = new TableView<Result>();
    @FXML
    private TableColumn<Result, String> firm_name;
    @FXML
    private TableColumn<Result, Long> salary;
    @FXML
    private TableColumn<Result, String> name;
    @FXML
    private TextField salary_tf;
    @FXML
    private Label label;
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
                Query query = session.createQuery("SELECT f.name, h.salary, ta.name " +
                        "FROM Firm f JOIN Hire h " +
                        "ON f.id = h.firm.id " +
                        "JOIN TradeAgent ta " +
                        "ON h.pagerNumber = ta.pagerNumber " +
                        "WHERE h.salary IN (:n) ");
                query.setParameter("n", Long.parseLong(salary_tf.getText()));
                List<Result> list = new ArrayList<>(5);
                for (Object o : query.getResultList()) {
                    Object[] row = (Object[]) o;
                    list.add(new Result((String) row[0], (Long) row[1], (String) row[2]));
                    System.out.println(row[0] + " " + row[1]);
                }
                ObservableList<Result> providerObservableList =
                        FXCollections.observableArrayList(
                                list
                        );
                System.out.println(providerObservableList);
                table.setItems(providerObservableList);
                System.out.println(query.getResultList());
                if (providerObservableList.isEmpty()){
                    label.setText("Сотрудников с такой зарплатой нет");
                }
                else {
                    label.setText("");
                }
            });
        });
    }
    protected class Result {
        private String firm_name;
        private long salary;
        private String name;

        public Result(String firm_name, long salary, String name) {
            this.firm_name = firm_name;
            this.salary = salary;
            this.name = name;
        }

        public String getFirm_name() {
            return firm_name;
        }

        public void setFirm_name(String firm_name) {
            this.firm_name = firm_name;
        }

        public long getSalary() {
            return salary;
        }

        public void setSalary(long salary) {
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firm_name.setCellValueFactory(new PropertyValueFactory<>("firm_name"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
