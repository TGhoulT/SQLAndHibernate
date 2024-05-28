import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class NewMain {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Subscription.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            /*String hql = "SELECT s.courseId, COUNT(*) AS totalSubscriptions, AVG(DATEDIFF(CURDATE(), s.subscriptionDate)) AS avgDaysSinceSubscription " +
                    "FROM Subscription s " +
                    "GROUP BY s.courseId " +
                    "ORDER BY totalSubscriptions DESC";*/

            String hql = "SELECT s.courseId, COUNT(*) AS totalSubscriptions\n" +
                    "FROM Subscription s\n" +
                    "GROUP BY s.courseId\n" ;

            Query query = session.createQuery(hql, Subscription[].class);
            List<String[]> resultList = query.getResultList();

            for (Object[] row : resultList) {
                int courseId = (int) row[0];
                float totalSubscriptions = (long) row[1];
                System.out.println("Course ID: " + courseId + ", Total Subscriptions: " + totalSubscriptions / 9);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}