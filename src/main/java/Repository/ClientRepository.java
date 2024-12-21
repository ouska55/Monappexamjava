package Repository;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Entity.ClientEntity;

public class ClientRepository {

    private static final Session HibernateUtil = null;
    
        public Optional<ClientEntity> findByTelephone(String telephone) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ClientEntity> query = session.createQuery("FROM Client WHERE telephone = :telephone", Client.class);
            query.setParameter("telephone", telephone);
            return query.uniqueResultOptional();
        }
    }

    public void save(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}