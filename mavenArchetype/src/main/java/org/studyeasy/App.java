package org.studyeasy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.studyeasy.entity.Users;

import java.util.List;

public class App {
    public static void main(String[] args) {
        SessionFactory factory =
                new Configuration().configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Users.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {

            session.beginTransaction();
//            List<Users> users =
                        session.createQuery("update users set password = 'thisIsNotAPassword'" +
                            "where password like '%com%'").executeUpdate();
//            for (Users temp : users) {
//                System.out.println(temp);
//            }

//            Users users = new Users();
//            session.save(users);
//            users = session.get(Users.class, 4);
//            session.delete(users);
//            users.setPassword("thisIsNotAPassword");
//            session.getTransaction().commit();
//            System.out.println(users);

        } finally {
            session.close();
            factory.close();
        }
    }
}
