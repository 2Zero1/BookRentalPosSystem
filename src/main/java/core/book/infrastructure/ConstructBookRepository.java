package core.book;

import core.book.Book;
import core.book.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;


public class ConstructBookRepo implements BookRepository {

    private SessionFactory sessionFactory;

    public ConstructBookRepo() {
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

    @Override
    public List<Book> findAll() {
        // now lets pull events from the database and list them
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> result = session.createQuery( "from Book " ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Book> findByBookName(String bookName) {
        // now lets pull events from the database and list them
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> result = session.createQuery( "from Book where name = "+bookName ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public boolean insertBook(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Book findBySerialNum(int serialNum) {
        // now lets pull events from the database and list them
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book result = (Book) session.createQuery( "from Book where serialNum = "+serialNum ).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;

    }
}

