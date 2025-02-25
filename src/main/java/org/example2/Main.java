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

        Projektas dummyProject = new Projektas();
        dummyProject.setPavadinimas("Bendras projektas");
        session.persist(dummyProject);

        // Creating Employees (Darbuotojas)
        Darbuotojas darbuotojas1 = new Darbuotojas("Jonas", "Jonaitis", "Programuotojas", skyrius1, dummyProject);
        Darbuotojas darbuotojas2 = new Darbuotojas("Petras", "Petraitis", "Apskaitininkas", skyrius2, dummyProject);
        Darbuotojas darbuotojas3 = new Darbuotojas("Ona", "Onaitė", "Marketingo specialistė", skyrius3, dummyProject);
        Darbuotojas darbuotojas4 = new Darbuotojas("Laura", "Lauraitė", "Personalo vadovė", skyrius4, dummyProject);
        Darbuotojas darbuotojas5 = new Darbuotojas("Mantas", "Mantaitis", "Pardavimų vadybininkas", skyrius5, dummyProject);

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

        printAllEmployess(session);

        assignEmployeesToProject(session, projektas1);

        updateEverySecondDepartment(session);

        session.close();
        HibernateUtil.shutdown();
    }

    private static void printAllEmployess(Session session) {
        List<Darbuotojas> darbuotojai = session.createQuery("FROM Darbuotojas", Darbuotojas.class).getResultList();
        System.out.println("\n VISI DARBUOTOJAI:");
        for (Darbuotojas d : darbuotojai) {
            System.out.println(d.getVardas() + " " + d.getPavarde() + " - " + d.getPareigos());
        }
    }

    private static void assignEmployeesToProject(Session session, Projektas projektas) {
        Transaction trx = session.beginTransaction();
        List<Darbuotojas> darbuotojai = session.createQuery("FROM Darbuotojas", Darbuotojas.class).getResultList();

        for (Darbuotojas d : darbuotojai) {
            d.setProjektas(projektas);
            session.merge(d);
        }

        trx.commit();
        System.out.println("\n VISI DARBUOTOJAI PRISKIRTI PRIE PROJEKTO: " + projektas.getPavadinimas());

    }

    private static void updateEverySecondDepartment(Session session) {
        Transaction trx = session.beginTransaction();
        List<Skyrius> skyriai = session.createQuery("FROM Skyrius", Skyrius.class).getResultList();

        for (int i = 1; i < skyriai.size(); i += 2) {
            Skyrius skyrius = skyriai.get(i);
            skyrius.setPavadinimas(skyrius.getPavadinimas().toUpperCase());
            session.merge(skyrius);
        }
        trx.commit();
        System.out.println("\n KAS ANTRO SKYRIAUS PAVADINIMAS PAKEISTAS Į DIDŽIASIAS RAIDES.");
    }
}