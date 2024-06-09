package org.example.course.viewTable;

import jakarta.persistence.Table;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.course.HibernateSession;
import org.example.course.entities.Provider;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProviderController implements Initializable {
    @FXML
    private TableView<Provider> provider_table = new TableView<>();
    @FXML
    private TableColumn<Provider, Long> id;
    @FXML
    private TableColumn<Provider, Long> amountofdebt;
    @FXML
    private TableColumn<Provider, String> adress;
    @FXML
    private TableColumn<Provider, String> name;
    @FXML
    private Label id_tf;
    @FXML
    private TextField amount_tf;
    @FXML
    private TextField adress_tf;
    @FXML
    private TextField name_tf;
    @FXML
    private Button back;

    @FXML
    protected void provider() {

        provider_table.setOnMouseClicked(event -> {
            Provider provider = provider_table.getSelectionModel().getSelectedItem();
            id_tf.setText(String.valueOf(provider.getId()));
            amount_tf.setText(String.valueOf(provider.getAmountOfDebt()));
            adress_tf.setText(String.valueOf(provider.getAdress()));
            name_tf.setText(String.valueOf(provider.getName()));
        });

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        amountofdebt.setCellValueFactory(new PropertyValueFactory<>("amountOfDebt"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                ObservableList<Provider> providerObservableList =
                        FXCollections.observableArrayList(
                                session.createQuery("from Provider", Provider.class).list()
                        );
                System.out.println(providerObservableList);
                provider_table.setItems(providerObservableList);
            });
            System.out.println();
        });

    }

    public void edit() {
//        Provider provider = new Provider();
//        provider.setId(Long.parseLong(id_tf.getText()));
//        provider.setAdress(adress_tf.getText());
//        provider.setAmountOfDebt(Long.parseLong(amount_tf.getText()));
//        provider.setName(name_tf.getText());
        if (id_tf.getText().equals("New") || id_tf.getText().equals("")) {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Provider provider = new Provider(adress_tf.getText(), Long.parseLong(amount_tf.getText()), name_tf.getText(), null);
                session.persist(provider);
                session.flush();
            });
        } else HibernateSession.sessionFactory().inTransaction(session -> {
            Provider provideredited = session.get(Provider.class, Long.parseLong(id_tf.getText()));
            provideredited.setAdress(adress_tf.getText());
            provideredited.setAmountOfDebt(Long.parseLong(amount_tf.getText()));
            provideredited.setName(name_tf.getText());
            session.persist(provideredited);
            session.flush();
        });
        provider();
    }

    @FXML
    private void generateNew() {
        id_tf.setText("New");
        adress_tf.setText("");
        amount_tf.setText("");
        name_tf.setText("");
    }

    @FXML
    private void delete() {
        HibernateSession.sessionFactory().inTransaction(session -> {
            Provider provider = session.get(Provider.class, Long.parseLong(id_tf.getText()));
            session.remove(provider);
        });
        provider();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        provider();
    }
    @FXML
    void goBack(ActionEvent event) throws IOException { // GO BACK!!!
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root =
                FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        ;
        stage.setTitle("Main");
        stage.setScene(new Scene(root));
        stage.show();
    }
}