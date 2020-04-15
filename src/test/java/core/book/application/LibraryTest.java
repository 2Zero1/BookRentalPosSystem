package core.book.application;

import core.book.domain.Book;
import core.book.domain.BookAlreadyUsedException;
import core.book.domain.Jenre;
import core.book.infrastructure.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class LibraryTest {
    @Mock
    BookRepository bookRepository;
    Library library;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        library = new Library(bookRepository);
    }

    @Test
    public void registerBook() {
        int serialNum = 1;
        Book book = new Book(1,serialNum,"드래곤라자", Jenre.FANTASY,300);
        given(bookRepository.findBySerialNum(serialNum)).willReturn(Optional.empty());
        library.registerBook(book);
        verify(bookRepository).insertBook(book);

    }
    @Test
    public void registerBookByExitedSerialNum() {
        int serialNum = 1;
        Book book = new Book(1,serialNum,"드래곤라자", Jenre.FANTASY,300);
        given(bookRepository.findBySerialNum(serialNum)).willReturn(Optional.of(book));
        assertThatThrownBy(() -> {library.registerBook(book);}).isInstanceOf(BookAlreadyUsedException.class);
    }

    @Test
    public void searchBookBySerialNum() {
        Book book = new Book(1,1,"드래곤라자", Jenre.FANTASY,300);
        given(bookRepository.findBySerialNum(1)).willReturn(Optional.of(book));

        assertThat(library.searchBookBySerialNum(1)).isEqualTo(Optional.of(book));

    }

    @Test
    public void getAllBooks() {
        List<Book> books = List.of(new Book(1,1,"드래곤라자",Jenre.FANTASY,300),
                new Book(2,2,"도라에몽",Jenre.FANTASY,400));
        given(bookRepository.getAllBooks()).willReturn(books);
        assertThat(library.getAllBooks()).isEqualTo(books);
    }

    @Test
    public void getBookByName() {
        String bookName = "드래곤라자";

        List<Book> books = List.of(new Book(1,1,bookName,Jenre.FANTASY,300),
                new Book(1,2,bookName,Jenre.FANTASY,300));
        given(bookRepository.getBookByName(bookName)).willReturn(books);
        assertThat(library.getBookByName(bookName)).isEqualTo(books);
    }

    @Test
    public void getBookBySerialNum() {
        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY,300);
        given(bookRepository.getBookBySerialNum(book.getSerialNum())).willReturn(Optional.of(book));

        assertThat(library.getBookBySerialNum(book.getSerialNum())).isEqualTo(book);
    }
}