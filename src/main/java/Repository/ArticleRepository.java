package Repository;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ArticleRepository {

    public Optional<Article> findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Article.class, id));
        }
    }

    public List<Article> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Article> query = session.createQuery("FROM Article", Article.class);
            return query.list();
        }
    }

    public void save(Article article) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(article);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
