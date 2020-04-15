package UI;

//import application.BookService;

import application.BookController;
import core.book.application.Library;
import core.book.domain.Book;
import core.book.domain.Jenre;
import core.book.domain.Trader;

import java.util.List;
import java.util.Scanner;

public class BookUI  {
    Scanner input = new Scanner(System.in);
    Trader trader;
//    Library library;
    BookController bookController;
    public BookUI(Trader trader, Library library) {
        this.trader = trader;
//        this.library = library;
        bookController = new BookController(library,trader);
    }

    public void start() {
        System.out.println("1. 책 이름 검색");
        System.out.println("2. 책 번호 검색");
        System.out.println("3. 모든 책 리스트 검색");
        System.out.println("4. 책 등록");
        run(input.nextLine());
        System.out.println();

}

    public void run(String num) {

        switch (Integer.parseInt(num)) {
            case 1 : searchBookByName();
                break;
            case 2 : searchBookByBookSerial();
                break;
            case 3 : searchAllBook();
                break;
            case 4: registerBook();
                break;
            default:
                return;
        }

    }
    private void registerBook() {

        System.out.println("등록할 책 이름을 입력하시오");
        String bookName = input.nextLine();
        System.out.println("대여비를 입력하시오");
        int price = Integer.parseInt(input.nextLine());

        Jenre jenre = selectJenre();
        Book book = new Book(0,bookName,jenre,price);
        bookController.registerNewBook(book);
        System.out.println("등록된 서적 : "+book.toString());

    }
    private Jenre selectJenre(){
        System.out.println("선택할 장르 번호를 입력하시오");
        System.out.println("1. 판타지");
        System.out.println("2. 무협");
        System.out.println("3. 로맨스");
        System.out.println("4. 학교물");
        switch (Integer.parseInt(input.nextLine())) {
            case 1:
                return Jenre.FANTASY;
            case 2:
                return Jenre.MARTIAL_ARTS;
            case 3:
                return Jenre.ROMANCE;
            case 4:
                return Jenre.SCHOOL;
            default:
                return null;
        }
    }

    public void searchBookByName() {
        System.out.println("책 이름을 입력하시오");
        String bookName = input.nextLine();
        List<Book> books = bookController.getBookByName(bookName);
//        List<Book> books = bookService.getBookByName(bookName);
        books.forEach(System.out::println);
        System.out.println();

    }
    public void searchBookByBookSerial() {
        System.out.println("책 번호를 입력하시오");

        Book book = bookController.getBookBySerialNum(Integer.parseInt(input.nextLine()));
        System.out.println(book);
        System.out.println();
    }
    public void searchAllBook() {
        System.out.println("현재 매장 내의 책 목록");
        bookController.getAllBooks().forEach(System.out::println);
        System.out.println();
    }
}
