package application;

import domain.Book;
import domain.BookRepository;
import domain.Jenre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest {

    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService(bookRepository);

    }


    @Test
    public void getAllBooks(){

        List<Book> mockBooks = List.of(new Book(1,1,"드래곤라자", Jenre.FANTASY,500));


        given(bookRepository.findAll()).willReturn(mockBooks);
        List<Book> books = bookService.getAllBooks();
        assertThat(books).hasSize(1);

        assertThat(books.get(0).getName()).isEqualTo("드래곤라자");

    }

    @Test
    public void searchBooksWithName() {
        String bookName = "드래곤라자";
        List<Book> mockBooks = List.of(new Book(1,1,bookName, Jenre.FANTASY,500),
                new Book(1,2,bookName, Jenre.FANTASY,500));

        given(bookRepository.findByBookName(bookName)).willReturn(mockBooks);

        List<Book> books = bookRepository.findByBookName("드래곤라자").stream().collect(Collectors.toList());
        assertThat(books).hasSize(2);
        assertThat(books.get(0).getName()).isEqualTo("드래곤라자");
        assertThat(books.get(1).getName()).isEqualTo("드래곤라자");
    }

}
