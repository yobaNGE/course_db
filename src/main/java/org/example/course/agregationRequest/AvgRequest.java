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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AvgRequest implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<Result> table = new TableView<>();
    @FXML
    private TableColumn<Result, String> firm;
    @FXML
    private TableColumn<Result, Double> avg_salary;
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
                Query query = session.createQuery("SELECT f.name, AVG(h.salary) as average_salary " +
                        "FROM Firm f JOIN Hire h " +
                        "ON f.id = h.firm.id " +
                        "Group By f.id");
                List<Result> list = new ArrayList<>(5);
                for (Object o : query.getResultList()) {
                    Object[] row = (Object[]) o;
                    list.add(new Result((String) row[0], (Double) row[1]));
                    System.out.println(row[0] + " " + row[1]);
                }
                ObservableList<Result> providerObservableList =
                        FXCollections.observableArrayList(
                                list
                        );
                System.out.println(providerObservableList);
                table.setItems(providerObservableList);
                System.out.println(query.getResultList());
            });
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firm.setCellValueFactory(new PropertyValueFactory<>("firm"));
        avg_salary.setCellValueFactory(new PropertyValueFactory<>("avg_salary"));
    }
    protected class Result {
        private String firm;
        private Double avg_salary;

        public Result(String firm, Double avg_salary) {
            this.firm = firm;
            this.avg_salary = avg_salary;
        }

        public String getFirm() {
            return firm;
        }

        public void setFirm(String firm) {
            this.firm = firm;
        }

        public Double getAvg_salary() {return avg_salary;}

        public void setAvg_salary(Double avg_salary) {
            this.avg_salary = avg_salary;
        }
    }
}
