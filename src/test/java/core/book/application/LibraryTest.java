package core.book.application;

import core.book.domain.Book;
import core.book.domain.exception.BookSerialNumAlreadyUsedException;
import core.book.domain.Jenre;
import core.book.infrastructure.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;



class LibraryTest {
    @Mock
    BookRepository bookRepository;
    Library library;

    Book book;
    List<Book> books;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        library = new Library(bookRepository);

        book = new Book(1,1,"드래곤라자", Jenre.FANTASY,300);
        books = List.of(new Book(1,1,"드래곤라자",Jenre.FANTASY,300),
                new Book(2,2,"도라에몽",Jenre.FANTASY,400));
    }

    @Test
    public void registerBook() {
        given(bookRepository.findBySerialNum(book.getSerialNum()))
                .willReturn(Optional.empty());
        library.registerBook(book);

        verify(bookRepository).insertBook(book);
    }
    @Test
    public void registerBookByExitedSerialNum() {
        given(bookRepository.findBySerialNum(book.getSerialNum()))
                .willReturn(Optional.of(book));

        assertThatThrownBy(() -> library.registerBook(book))
                .isInstanceOf(BookSerialNumAlreadyUsedException.class);
    }

    @Test
    public void checkExistSerialNumWithExistingSerialNum() {
        given(bookRepository.findBySerialNum(book.getSerialNum()))
                .willReturn(Optional.of(book));

        assertThatThrownBy(() -> library.checkExistSerialNum(
                book.getSerialNum())).isInstanceOf(
                        BookSerialNumAlreadyUsedException.class);
    }

    @Test
    public void checkExistSerialNumWithNonExistingSerialNum() {
        given(bookRepository.findBySerialNum(book.getSerialNum()))
                .willReturn(Optional.empty());

        library.checkExistSerialNum(book.getSerialNum());

        verify(bookRepository).findBySerialNum(book.getSerialNum());
    }

    @Test
    public void getAllBooks() {
        given(bookRepository.getAllBooks()).willReturn(books);
        assertThat(library.getAllBooks()).isEqualTo(books);
    }

    @Test
    public void getBookByName() {
        given(bookRepository.getBookByName(book.getName())).willReturn(books);
        assertThat(library.getBookByName(book.getName())).isEqualTo(books);
    }

    @Test
    public void getBookBySerialNum() {
        given(bookRepository.getBookBySerialNum(book.getSerialNum())).willReturn(Optional.of(book));

        assertThat(library.getBookBySerialNum(book.getSerialNum())).isEqualTo(book);
    }
}