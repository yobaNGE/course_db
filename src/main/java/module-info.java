module org.example.course {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens org.example.course to javafx.fxml;

    opens org.example.course.entities to org.hibernate.orm.core, javafx.base;
    exports org.example.course;
}