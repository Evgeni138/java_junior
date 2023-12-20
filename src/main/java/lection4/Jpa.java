package lection4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


public class Jpa {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<User> users = LongStream.rangeClosed(1, 10)
                    .mapToObj(it -> new User(it))
                    .peek(it -> it.setName("User #" + it.getId()))
                    .peek(it -> session.persist(it))
                    .collect(Collectors.toList());

//            User user = new User();
//            user.setId(2L);
//            user.setName("Eugene");
//
//            session.persist(user);
            session.getTransaction().commit();
        }

        final User loadedUser;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            loadedUser = session.get(User.class, 2);
            loadedUser.setName("Updated");
            session.merge(loadedUser);
            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.openSession()) {

            List<User> users = session.createQuery("select u from User u where id > 3 order by id desc", User.class)
                    .getResultList();
            System.out.println(users);
        }
    }
}
