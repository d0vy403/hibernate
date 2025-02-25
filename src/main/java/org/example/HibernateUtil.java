package org.example;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    public static Session getSession() {
        return new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Comment.class)
                .buildSessionFactory()
                .openSession();
    }
}
