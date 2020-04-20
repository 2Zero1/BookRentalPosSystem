package core.book.infrastructure;

import core.book.domain.BookTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Optional;

public interface BookLedgerRepository  {

    Optional<BookTransaction> getLatestTxBySerialNum(int serialNum);

    BookTransaction insertTx(BookTransaction bookTx);
}
