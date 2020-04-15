package UI;

import application.BookController;
import application.CashController;
import application.UserController;
import core.book.application.BookRentLedger;
import core.book.application.Library;
import core.book.application.DefaultTrader;
import core.book.domain.Trader;
import core.book.infrastructure.BookLedgerRepository;
import core.book.infrastructure.BookRepository;
import core.cash.application.CashLedger;
import core.cash.infrastructure.CashRepository;
import core.user.application.UserManager;
import core.user.infrastructure.UserRepository;

import java.util.Scanner;

public class MainUI {

    //repository
    BookRepository bookRepository;
    CashRepository cashRepository;
    UserRepository userRepository;
    BookLedgerRepository bookLedgerRepository;


    //application
    BookRentLedger bookRentLedger;
    Library library;
    CashLedger cashLedger;
    UserManager userManager;
    Trader trader;

    //Controller
    BookController bookController;
    CashController cashController;
    UserController userController;


    //UI
    RentUI rentUI;
    BookUI bookUI;
    UserUI userUI;
    DepositeUI depositeUI;

    Scanner input = new Scanner(System.in);
    public void start() {
        setUp();
        System.out.println("1. 대여하기");
        System.out.println("2. 책 정보 보기");
        System.out.println("3. 유저 정보 보기");
        System.out.println("4. 돈 충전하기");
        System.out.println("5. 종료");
        run(input.nextLine());

    }
    private void setUp() {
        bookLedgerRepository = new BookLedgerRepository();

        bookRentLedger = new BookRentLedger(bookLedgerRepository);
        library = new Library(bookRepository);
        cashLedger = new CashLedger(cashRepository);
        bookRentLedger = new BookRentLedger(bookLedgerRepository);
        userManager = new UserManager(userRepository);
        trader = new DefaultTrader(cashLedger,bookRentLedger, library);
        userManager = new UserManager(userRepository);


        bookController = new BookController(library,trader);
        cashController = new CashController(cashLedger,userManager);
        userController = new UserController(userManager);


        rentUI = new RentUI(bookController,userManager);
        bookUI = new BookUI(trader,library);
        userUI = new UserUI(userManager, cashController, bookController);

    }
    private void run(String num) {

        switch (Integer.parseInt(num)) {
            case 1 : rentUI.start();
                break;
            case 2 : bookUI.start();
                break;
            case 3 : userUI.start();
                break;
            case 4 : depositeUI.start();
                break;
            case 5 : System.exit(0);
                break;
        }

    }
}
