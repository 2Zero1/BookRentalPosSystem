package core.cash;

import common.RequestResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookLedgerRepository {

    private SessionFactory sessionFactory;

    public BookLedgerRepository() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public BookTransaction getLatestTxBySerialNum(int serialNum) {
        // now lets pull events from the database and list them
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        BookTransaction result = (BookTransaction)session.createQuery( "from BookTransaction where bookSerialNum = "+serialNum+" order by date desc "  ).getSingleResult();
        BookTransaction result = (BookTransaction)session.createQuery( "from BookTransaction where bookSerialNum = "+serialNum).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public RequestResult insertTx(BookTransaction bookTx){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(bookTx);
        session.getTransaction().commit();
        System.out.println(bookTx.getBookTransactionNum());
        session.close();
        return RequestResult.Complete();
    }
}
