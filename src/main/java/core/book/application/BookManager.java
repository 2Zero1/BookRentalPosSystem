package core.book;

import core.user.User;
import core.cash.CashLedger;
import common.RequestResult;

public class BookManager {
    BookRepository bookRepository;
    CashLedger cashLedger;

    public BookManager(BookRepository bookRepository, CashLedger cashLedger) {
        this.bookRepository = bookRepository;
        this.cashLedger = cashLedger;
    }

    public RequestResult rent(User user, int bookSerialNum) {
        Book book = searchBookBySerialNum(bookSerialNum);
            if (book == null) {
            return RequestResult.Fail("매장에 존재하지 않다.");
        }

        if (book.isRented()) {
            return RequestResult.Fail("이미 대출된 서적이다.");
        }

        if (!cashLedger.hasEnoughMoney(user, book.getPrice())) {
            return RequestResult.Fail("잔액이 부족합니다.");
        }

        //거래를 진행한다.


        return RequestResult.Complete();
    }

//    private RequirementChecker checkRequirement(User user, int bookSerialNum) {
//        Book book = searchBookBySerialNum(bookSerialNum);
//        if (book == null) {
//            return RequirementChecker.NonSatisfied("매장에 존재하지 않다.");
//        }
//
//        if (book.isRented()){
//            return RequirementChecker.NonSatisfied("이미 대출된 서적이다.");
//        }
//
//        return cashManager.hasEnoughMoney(user,book.getPrice());
//    }


    public Book searchBookBySerialNum(int bookSerialNum) {
        return bookRepository.findBySerialNum(bookSerialNum);
    }

    public RequestResult registerNewBook(Book book) {
        if (searchBookBySerialNum(book.getSerialNum()) != null) {
            return RequestResult.Fail("입력한 시리얼넘버는 이미 사용중입니다.");
        }
        return RequestResult.Complete();
    }

}
