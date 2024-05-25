package org.example.course;

import org.example.course.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    public static SessionFactory sessionFactory() {
        return new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/course_db") // assembly_firm на свое название бд
                .setProperty("hibernate.connection.username", "postgres") // на свой
                .setProperty("hibernate.connection.password", "12345") // на свой
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                //.setProperty("hibernate.hbm2ddl.auto", "create")
                .addAnnotatedClass(Assembly.class)
                .addAnnotatedClass(CheckProvide.class)
                .addAnnotatedClass(CheckSaleComponent.class)
                .addAnnotatedClass(CheckSaleEndProduct.class)
                .addAnnotatedClass(ClientRep.class)
                .addAnnotatedClass(Components.class)
                .addAnnotatedClass(EndProduct.class)
                .addAnnotatedClass(Firm.class)
                .addAnnotatedClass(Hire.class)
                .addAnnotatedClass(Provide.class)
                .addAnnotatedClass(Provider.class)
                .addAnnotatedClass(SaleComponent.class)
                .addAnnotatedClass(SaleEndProduct.class)
                .addAnnotatedClass(TradeAgent.class)
                .buildSessionFactory();
    }
}
