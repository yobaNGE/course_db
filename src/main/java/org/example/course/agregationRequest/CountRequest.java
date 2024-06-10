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

public class CountRequest implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private Button back;
    @FXML
    private TableView<CountRequest.Result> table = new TableView<>();
    @FXML
    private TableColumn<CountRequest.Result, String> firm;
    @FXML
    private TableColumn<CountRequest.Result, Long> employee_count;
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
                Query query = session.createQuery("SELECT f.name, COUNT(*) as employee_count " +
                        "FROM Firm f JOIN Hire h " +
                        "ON f.id = h.firm.id " +
                        "Group By f.id");
                List<CountRequest.Result> list = new ArrayList<>(5);
                for (Object o : query.getResultList()) {
                    Object[] row = (Object[]) o;
                    list.add(new CountRequest.Result((String) row[0], (Long) row[1]));
                    System.out.println(row[0] + " " + row[1]);
                }
                ObservableList<CountRequest.Result> providerObservableList =
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
        employee_count.setCellValueFactory(new PropertyValueFactory<>("employee_count"));
    }
    protected class Result {
        private String firm;
        private long employee_count;

        public Result(String firm, long employee_count) {
            this.firm = firm;
            this.employee_count = employee_count;
        }

        public String getFirm() {
            return firm;
        }

        public void setFirm(String firm) {
            this.firm = firm;
        }

        public long getEmployee_count() {return employee_count;}

        public void setEmployee_count(long employee_count) {
            this.employee_count = employee_count;
        }
    }
}
