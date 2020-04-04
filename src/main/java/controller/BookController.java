package controller;

import application.BookService;
import domain.Book;

import java.util.List;

public class BookController {
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void getAllBookList() {
        List<Book> books = bookService.getAllBooks();
        books.forEach(System.out::println);
    }

    public void searchBooksWithName(String bookName) {
        List<Book> books = bookService.searchBooksWithName(bookName);
        if (books.stream().filter(v -> v.getName().equals(bookName)).count() == 0) {
            System.out.println("책 "+bookName+"가 존재하지 않습니다.");
        }else{
            books.forEach(System.out::println);
        }
    }
}
