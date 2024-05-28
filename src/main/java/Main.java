/*
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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



CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Subscription> criteriaQuery = criteriaBuilder.createQuery(Subscription.class);
        Root<Subscription> root = criteriaQuery.from(Subscription.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(criteriaBuilder.function("year", Integer.class, root.get("subscriptionDate")), 2018));

        List<Subscription> subscriptions = session.createQuery(criteriaQuery).getResultList();

        //Обработка подписок для вычисления среднего количества в месяц для каждого курса
        Map<Integer, List<Subscription>> subscriptionsByCourse = subscriptions.stream()
                .collect(Collectors.groupingBy(Subscription::getCourseId));

        subscriptionsByCourse.forEach((courseId, subs) -> {
            Set<Integer> activeMonthsCount = subs.stream()
                    .map(sub -> {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(sub.getSubscriptionDate());
                        return cal.get(Calendar.MONTH) + 1;
                    })
                    .collect(Collectors.toSet());

            long totalSubscriptions = subs.size();
            int activeMonths = activeMonthsCount.size();
            double average = (double) totalSubscriptions / activeMonths;
            System.out.println("Курс ID: " + courseId + ", Среднее количество подписок в месяц в 2018 году: " + average);
        });



CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> courseCriteriaQuery = criteriaBuilder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = courseCriteriaQuery.from(PurchaseList.class);
        List<PurchaseList> purchaseLists = session.createQuery(courseCriteriaQuery).getResultList();

        List<String> courseNames = new ArrayList<>();
        for (PurchaseList p : purchaseLists) {
            if (!courseNames.contains(p.getCourseName())) {
                courseNames.add(p.getCourseName());
            }
        }

        Map<String, Integer> totalExpenses = new HashMap<>();
        for (String course : courseNames) {
            totalExpenses.put(course, 0);
        }

        for (PurchaseList p : purchaseLists) {
            String course = p.getCourseName();
            Integer price = p.getPrice();
            totalExpenses.put(course, totalExpenses.get(course) + price);
        }

        totalExpenses.forEach((elem1, elem2) -> System.out.println(elem1 + " - " + elem2));





        try (Session session = sessionFactory.openSession()) {
String hql = "SELECT s.courseId, COUNT(*) AS totalSubscriptions, AVG(DATEDIFF(CURDATE(), s.subscriptionDate)) AS avgDaysSinceSubscription " +
                    "FROM Subscription s " +
                    "GROUP BY s.courseId " +
                    "ORDER BY totalSubscriptions DESC";

            String hql = "SELECT course_id, COUNT(course_id) / SELECT(MAX(MONTH(subscription_date)) + FROM " + Subscription.class.getName() + " GROUP BY course_id";

            Query<Object[]> query = session.createQuery(hql, Object[].class);
            List<Object[]> resultList = query.getResultList();

            for (Object[] row : resultList) {
                int courseId = (int) row[0];
                float totalSubscriptions = (long) row[1];
                System.out.println("Course ID: " + courseId + ", Total Subscriptions: " + totalSubscriptions / 9);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Subscription> criteriaQuery = criteriaBuilder.createQuery(Subscription.class);
        Root<Subscription> root = criteriaQuery.from(Subscription.class);
        criteriaQuery.select(root).where()




        // Фильтрация подписок только за 2018 год
criteriaQuery.select(root).where(criteriaBuilder.between(root.get("subscriptionDate"),
                java.sql.Date.valueOf("2018-01-01"), java.sql.Date.valueOf("2018-12-31")));


List<Subscription> subscriptions = session.createQuery(criteriaQuery).getResultList();





//*
/ Обработка подписок для вычисления среднего количества в месяц для каждого курса
        Map<Integer, List<Subscription>> subscriptionsByCourse = subscriptions.stream()
                .collect(Collectors.groupingBy(Subscription::getCourseId));

        // Вычисление среднего количества подписок в месяц для каждого курса
        subscriptionsByCourse.forEach((courseId, subs) -> {
            // Общее количество подписок
            long totalSubscriptions = subs.size();
            // Максимальное количество месяцев (12)
            int maxMonths = 12;
            // Среднее количество подписок в месяц
            double average = (double) totalSubscriptions / maxMonths;

            // Вывод результата на консоль
            System.out.println("Курс ID: " + courseId + ", Среднее количество подписок в месяц в 2018 году: " + average);
        });




transaction.commit();

        sessionFactory.close();
    }
}
*/
