package org.example.course;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.course.entities.*;
import org.hibernate.Session;

import java.lang.reflect.Field;
import java.util.List;

public class AdaptiveController<T> {
    @FXML
    private TableView<T> tableView;
    @FXML
    private ComboBox<Class> pickClass;

    @FXML
    public void initialize() {
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
        pickClass.setOnAction(event -> {
            Class<T> selectedClass = pickClass.getValue();
            if (selectedClass != null) {
                initializeTable(selectedClass);
            }
        });
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
        Session session = HibernateSession.sessionFactory().openSession();
        List<T> list = session.createQuery("from " + entityClass.getName(), entityClass).list();
        tableView.getItems().setAll(list);
        session.close();
    }
}
