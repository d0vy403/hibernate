package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        Transaction trx = session.beginTransaction();

        for (int i = 1; i <= 10; i++) {
            session.persist(new Comment("Author" + i, "This is comment number " + i));
        }
        trx.commit();

        List<Comment> comments = session.createQuery("FROM Comment", Comment.class).list();
        comments.forEach(System.out::println);

        trx = session.beginTransaction();
        Comment commentUpdate = session.get(Comment.class, 1);
        if (commentUpdate != null) {
            commentUpdate.setContent("Updated content!");
            session.update(commentUpdate);
        }
        trx.commit();

        session.createQuery("From Comment", Comment.class).list().forEach(System.out::println);

        session.close();
    }
}