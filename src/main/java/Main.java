import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry)
                .getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        Course course = session.get(Course.class, 45);
        int countStudents = course.getStudents().size();
        System.out.println("Course: " + course +
                "\nCount students: " + countStudents);


        List<Student> studentsList = course.getStudents();
        //studentsList.forEach(System.out::println);


        Subscription subscription = new Subscription();
        subscription.setStudentId(101);
        subscription.setCourseId(101);
        subscription.setSubscriptionDate();


        transaction.commit();
        sessionFactory.close();
    }
}
