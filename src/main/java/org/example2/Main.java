package org.example2;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        Skyrius skyrius1 = new Skyrius("IT skyrius");
        Skyrius skyrius2 = new Skyrius("Finansai");
        Skyrius skyrius3 = new Skyrius("Marketingas");
        Skyrius skyrius4 = new Skyrius("Žmogiškieji Ištekliai");
        Skyrius skyrius5 = new Skyrius("Pardavimai");

        session.persist(skyrius1);
        session.persist(skyrius2);
        session.persist(skyrius3);
        session.persist(skyrius4);
        session.persist(skyrius5);

        // Creating Employees (Darbuotojas)
        Darbuotojas darbuotojas1 = new Darbuotojas("Jonas", "Jonaitis", "Programuotojas", skyrius1);
        Darbuotojas darbuotojas2 = new Darbuotojas("Petras", "Petraitis", "Apskaitininkas", skyrius2);
        Darbuotojas darbuotojas3 = new Darbuotojas("Ona", "Onaitė", "Marketingo specialistė", skyrius3);
        Darbuotojas darbuotojas4 = new Darbuotojas("Laura", "Lauraitė", "Personalo vadovė", skyrius4);
        Darbuotojas darbuotojas5 = new Darbuotojas("Mantas", "Mantaitis", "Pardavimų vadybininkas", skyrius5);

        session.persist(darbuotojas1);
        session.persist(darbuotojas2);
        session.persist(darbuotojas3);
        session.persist(darbuotojas4);
        session.persist(darbuotojas5);

        // Creating Projects (Projektas)
        Projektas projektas1 = new Projektas("ERP sistema", List.of(darbuotojas1, darbuotojas2));
        Projektas projektas2 = new Projektas("Biudžeto analizė", List.of(darbuotojas2, darbuotojas3));
        Projektas projektas3 = new Projektas("Reklamos kampanija", List.of(darbuotojas3, darbuotojas4));
        Projektas projektas4 = new Projektas("Darbuotojų mokymai", List.of(darbuotojas4, darbuotojas5));
        Projektas projektas5 = new Projektas("Klientų aptarnavimo gerinimas", List.of(darbuotojas5, darbuotojas1));

        session.persist(projektas1);
        session.persist(projektas2);
        session.persist(projektas3);
        session.persist(projektas4);
        session.persist(projektas5);


        trx.commit();
        session.close();
        HibernateUtil.shutdown();
    }
}