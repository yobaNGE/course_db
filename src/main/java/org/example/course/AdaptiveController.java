package org.example.course;

import jakarta.persistence.Query;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.course.entities.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AdaptiveController<T> {
    @FXML
    private TableView<T> tableView;
    @FXML
    private ComboBox<Class> pickClass;
    @FXML
    private VBox inputFields;
    @FXML
    private Button back;


    private Class<T> selectedClass;
    private T selectedEntity;
    @FXML
    public void initialize() {

        tableView.setOnMouseClicked(event -> {
            selectedEntity = tableView.getSelectionModel().getSelectedItem();
            if (selectedEntity != null) {
                Field[] fields = selectedEntity.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    try {
                        field.setAccessible(true);
                        TextField textField = (TextField) inputFields.getChildren().get(i);
                        textField.setText(String.valueOf(field.get(selectedEntity)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        pickClass.setOnAction(event -> {
            selectedClass = pickClass.getValue();
            if (selectedClass != null) {
                initializeTable(selectedClass);
                initializeInputFields(selectedClass);
            }
        });
        pickClass.setCellFactory(param -> new ListCell<Class>() {
            @Override
            protected void updateItem(Class item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getSimpleName());
                }
            }
        });

        pickClass.setButtonCell(new ListCell<Class>() {
            @Override
            protected void updateItem(Class item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getSimpleName());
                }
            }
        });
        pickClass.getItems()
                .addAll(
                        Assembly.class, CheckProvide.class, CheckSaleComponent.class,
                        CheckSaleEndProduct.class, ClientRep.class, Components.class,
                        EndProduct.class, Firm.class, Hire.class, Provide.class,
                        Provider.class, SaleComponent.class, SaleEndProduct.class, TradeAgent.class
                );
    }

    private void initializeTable(Class<T> entityClass) {
        tableView.getColumns().clear();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            TableColumn<T, ?> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            tableView.getColumns().add(column);
        }

        fillTable(entityClass);
    }

    private void fillTable(Class<T> entityClass) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery("from " + entityClass.getName(), entityClass);
                ObservableList<T> providerObservableList =
                        FXCollections.observableArrayList(
                                query.getResultList()
                        );
                tableView.setItems(providerObservableList);
            });
        });
    }

    private void initializeInputFields(Class<T> entityClass) {
        inputFields.getChildren().clear();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            TextField textField = new TextField();
            textField.setPromptText(field.getName());
            inputFields.getChildren().add(textField);
        }
    }

    @FXML
    private void save() throws InstantiationException, IllegalAccessException {
        T entity = selectedClass.newInstance();
        if (entity == null) {
            return;
        }
        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            try {
                field.setAccessible(true);
                TextField textField = (TextField) inputFields.getChildren().get(i);
                if (!field.getName().equals("id")) {
                    if (field.getType() == Long.class) {
                        field.set(entity, Long.parseLong(textField.getText()));
                    } else if (field.getType() == Integer.class) {
                        field.set(entity, Integer.parseInt(textField.getText()));
                    } else if (field.getType() == Double.class) {
                        field.set(entity, Double.parseDouble(textField.getText()));
                    } else if (field.getType() == List.class) {
                        field.set(entity, null);
                    } else {
                        field.set(entity, textField.getText());
                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        saveEntity(entity);
        fillTable(selectedClass);
    }

    private void saveEntity(T entity) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                session.persist(entity);
                session.flush();
            });
        });
    }

    private T getEntity(Class<T> entityClass, long id) {
        AtomicReference<T> entity = new AtomicReference<>();
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                entity.set(session.get(entityClass, id));
            });
        });
        return entity.get();
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
}
