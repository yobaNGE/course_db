module org.example.course {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens org.example.course to javafx.fxml;

    opens org.example.course.entities to org.hibernate.orm.core, javafx.base;
    exports org.example.course;
    exports org.example.course.searchRequest;
    opens org.example.course.searchRequest to javafx.fxml;
    exports org.example.course.agregationRequest;
    opens org.example.course.agregationRequest to javafx.fxml;
    exports org.example.course.viewTable;
    opens org.example.course.viewTable to javafx.fxml;
}