//package domain;
//
//import common.RequestResult;
//import core.book.domain.Book;
//import application.BookManager;
//import core.book.infrastructure.BookRepository;
//import core.book.domain.Jenre;
//import core.cash.application.CashLedger;
//import core.user.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//
//
//class BookManagerTest {
//    @Mock
//    BookRepository bookRepository;
//    @Mock
//    CashLedger cashLedger;
//
//    BookManager bookManager;
//
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        bookManager = new BookManager(bookRepository, cashLedger);
//    }
//
//    @Test
//    public void rentExistBook() {
//
//        User user = new User("이영한");
//
//        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY ,400);
//
//        given(bookRepository.findBySerialNum(1)).willReturn(book);
//        given(cashLedger.hasEnoughMoney(user,book.getPrice())).willReturn(true);
//
//        int bookSerialNum = 1;
//        RequestResult requestResult = bookManager.rent(user, bookSerialNum);
//        assertThat(requestResult.isComplete()).isEqualTo(true);
//    }
//
//    @Test
//    public void rentNonExistBook() {
//        User user = new User("이영한");
//
//        int bookSerialNum2 = 2;
//        Book book = new Book(1,bookSerialNum2,"드래곤라자",Jenre.FANTASY ,400);
//
//        given(bookRepository.findBySerialNum(1)).willReturn(book);
//        given(cashLedger.hasEnoughMoney(user,book.getPrice())).willReturn(true);
//
//
//        RequestResult requestResult = bookManager.rent(user, bookSerialNum2);
//        assertThat(requestResult.isComplete()).isEqualTo(false);
//        assertThat(requestResult.getFailMessage()).isEqualTo("매장에 존재하지 않다.");
//    }
//
//    @Test
//    public void rentBookWithoutEnoughMoney() {
//
//        User user = new User("이영한");
//
//        Book book = new Book(1,1,"드래곤라자",Jenre.FANTASY ,400);
//
//        given(bookRepository.findBySerialNum(1)).willReturn(book);
//        given(cashLedger.hasEnoughMoney(user,book.getPrice())).willReturn(false);
//
//        int bookSerialNum = 1;
//
//        RequestResult requestResult = bookManager.rent(user, bookSerialNum);
//        assertThat(requestResult.isComplete()).isEqualTo(false);
//        assertThat(requestResult.getFailMessage()).isEqualTo("잔액이 부족합니다.");
//    }
//
//    @Test
//    public void registerNewBook() {
//        int serialNum = 1;
//        Book book = new Book(1,serialNum,"바키",Jenre.MARTIAL_ARTS, 400);
//
//        given(bookManager.searchBookBySerialNum(serialNum)).willReturn(null);
//        RequestResult result = bookManager.registerNewBook(book);
//        assertThat(result.isComplete()).isEqualTo(true);
//    }
//
//    @Test
//    public void registerNewBookWithExistSerialNum() {
//        int serialNum = 1;
//        Book book = new Book(1,serialNum,"바키", Jenre.MARTIAL_ARTS, 400);
//
//        given(bookManager.searchBookBySerialNum(serialNum)).willReturn(book);
//        RequestResult result = bookManager.registerNewBook(book);
//        assertThat(result.isComplete()).isEqualTo(false);
//        assertThat(result.getFailMessage()).isEqualTo("입력한 시리얼넘버는 이미 사용중입니다.");
//    }
//
//}
