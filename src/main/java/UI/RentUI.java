package UI;

import application.BookController;
import common.SystemException;
import core.user.application.UserManager;
import core.user.domain.User;

import java.util.Scanner;

public class RentUI {
    Scanner input = new Scanner(System.in);
    BookController bookController;
    UserManager userManager;

    public RentUI(BookController bookController, UserManager userManager) {
        this.bookController = bookController;
        this.userManager = userManager;
    }

    public void start() {
        System.out.println("계정을 입력해 주세요.");
        run(input.nextLine());
    }

    public void run(String name) {
        try{
            System.out.println("대여할 책 번호를 입력하시오");
            User user = userManager.getUserByName(name);
            bookController.rentBySerialNum(user,Integer.parseInt(input.nextLine()));


        }catch (SystemException e) {
            e.printStackTrace();
        }

    }


}
