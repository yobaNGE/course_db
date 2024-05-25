package org.example.course;

import jakarta.persistence.Table;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.course.entities.Provider;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProviderController {
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
    private TextField id_tf;
    @FXML
    private TextField amount_tf;
    @FXML
    private TextField adress_tf;
    @FXML
    private TextField name_tf;

    @FXML
    protected void provider (){

        provider_table.setOnMouseClicked(event->{
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
//    public void Edit() {
//        try {
//            conn = DButils.ConnectDb();
////            String id = id_tf.getText();
//            String amount = amount_tf.getText();
//            String adress = (col_id.getCellData(index).toString());
//            String name = (col_id.getCellData(index).toString());
//            String sql = "update users set login = ?,password= ? where
//            user_id= ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1,login);
//            ps.setString(2,password);
//            ps.setString(3,id);
//            ps.execute();
//            UpdateTable();
//        } catch (Exception e) {
//            //JOptionPane.showMessageDialog(null, e);
//            e.printStackTrace();
//        }
//    }


}