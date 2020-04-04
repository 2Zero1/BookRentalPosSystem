package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(1,1,"드래곤라자",Jenre.FANTASY,500);
    }
    @Test
    public void checkAttributes() {
        assertThat(book.getName()).isEqualTo("드래곤라자");
        assertThat(book.getSerialNum()).isEqualTo(1);
        assertThat(book.getBookID()).isEqualTo(1);
        assertThat(book.getJenre()).isEqualTo(Jenre.FANTASY);
        assertThat(book.getPrice()).isEqualTo(500);
    }

}
