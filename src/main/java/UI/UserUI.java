package UI;

import application.BookController;
import application.CashController;
import core.user.application.UserManager;
import core.user.domain.User;

import java.util.Scanner;

public class UserUI {
    UserManager userManager;
    CashController cashController;
    BookController bookController;
    Scanner input = new Scanner(System.in);

    public UserUI(UserManager userManager, CashController cashController, BookController bookController) {
        this.userManager = userManager;
        this.cashController = cashController;
        this.bookController = bookController;
    }


    public void start() {
        System.out.println("1. 유저 리스트 보기");
        System.out.println("2. 유저 검색");
        run(input.nextLine());
    }

    public void run(String num) {
        switch (Integer.parseInt(num)) {
            case 1 : searchAllUsers();
                break;
            case 2 : searchUser();
                break;
        }
    }
    public void searchAllUsers() {
        userManager.getAllUsers().forEach(System.out::println);

    }
    public void searchUser(){
        System.out.println("유저의 이름을 입력하시오");
        String name = input.nextLine();
        //유저 찾고
        User user = userManager.getUserByName(name);
        //유저돈 보여주고
        cashController.getUserMoney(name);
        //유저 대여중인 책 보여주기
        bookController.getRentBookByUser(user).forEach(System.out::println);
    }
}
