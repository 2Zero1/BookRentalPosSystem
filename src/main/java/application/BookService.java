package application;

import domain.Book;
import domain.BookRepository;
import domain.Jenre;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        //TODO repository에서 찾아와야 한다.
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public List<Book> searchBooksWithName(String bookName) {
        //TODO repository에서 찾아와야 한다.
        List<Book> books = bookRepository.findByBookName(bookName)
                            .stream().collect(Collectors.toList());
        return books;
    }
}
