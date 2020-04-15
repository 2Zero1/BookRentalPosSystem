package core.book.application;

import core.book.domain.Book;
import core.book.domain.BookAlreadyUsedException;
import core.book.domain.BookAlreadyRentException;
import core.book.domain.BookEntityNotFoundException;
import core.book.infrastructure.BookRepository;
import core.user.domain.User;

import java.util.List;
import java.util.Optional;

//TODO : BookManager의 책 등록, 검색 하는 부분을 이곳에서 진행.
public class Library {
    BookRepository bookRepository;

    public Library(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void registerBook(Book book) throws BookAlreadyRentException {
        searchBookBySerialNum(book.getSerialNum())
                .ifPresent((v) -> {
                    throw new BookAlreadyUsedException(book.getSerialNum());
                });


        bookRepository.insertBook(book);
    }

    public Optional<Book> searchBookBySerialNum(int serialNum){
        return bookRepository.findBySerialNum(serialNum);
    }


    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> getBookByName(String book) {
        return bookRepository.getBookByName(book);
    }

    public Book getBookBySerialNum(int serialNum) throws BookEntityNotFoundException {
        return bookRepository.getBookBySerialNum(serialNum).orElseThrow(()-> {throw new BookEntityNotFoundException(serialNum);});
    }

    public List<Book> getRentBookByUser(User user) {
        return bookRepository.getRentBookByUser(user);
    }
}
