package controller;

import application.BookService;
import domain.Book;
import domain.Jenre;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BookControllerTest {

    BookController bookController;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookController = new BookController(bookService);

        System.setOut(new PrintStream(outContent));
    }
    @After
    public void out() {
        System.setOut(originalOut);
    }

    @Test
    public void AllBookList() {
        given(bookService.getAllBooks()).willReturn(List.of(new Book(1,1,"드래곤라자", Jenre.FANTASY,500)));

        bookController.getAllBookList();
        assertThat(outContent.toString()).isEqualTo("책 번호 : 1, 책 ID : 1, 책 이름 : 드래곤라자, 장르 : FANTASY, 대여비 : 500, 대여 가능 여부 : 가능\n");

    }

    @Test
    public void searchBooksWithNameThatExist() {
        String bookName = "드래곤라자";
        given(bookService.searchBooksWithName(bookName)).willReturn(
                List.of(new Book(1,1,bookName, Jenre.FANTASY,500),
                new Book(1,2,bookName, Jenre.FANTASY,500)));

        bookController.searchBooksWithName(bookName);

        assertThat(outContent.toString()).isEqualTo("책 번호 : 1, 책 ID : 1, 책 이름 : 드래곤라자, 장르 : FANTASY, 대여비 : 500, 대여 가능 여부 : 가능\n" +
                "책 번호 : 1, 책 ID : 2, 책 이름 : 드래곤라자, 장르 : FANTASY, 대여비 : 500, 대여 가능 여부 : 가능\n");

    }

    @Test
    public void searchBooksWithNameThatNoExist() {
        String bookName = "드래곤라자";
        given(bookService.searchBooksWithName(bookName)).willReturn(
                Lists.emptyList());
        bookController.searchBooksWithName(bookName);
        assertThat(outContent.toString()).isEqualTo("책 "+bookName+"가 존재하지 않습니다.\n");
    }

}
