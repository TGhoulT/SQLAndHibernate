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
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
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


        /*Course course = session.get(Course.class, 45);
        int countStudents = course.getStudents().size();
        System.out.println("Course: " + course +
                "\nCount students: " + countStudents);*/


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



        transaction.commit();
        sessionFactory.close();
    }
}
