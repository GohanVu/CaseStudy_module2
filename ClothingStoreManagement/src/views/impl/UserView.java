package views.impl;

import services.impl.UserService;

import java.util.Scanner;

public class UserView {
    UserService userService = new UserService();
    public boolean login(){
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        try {
            while (!loggedIn){
                System.out.println("=========================================");
                System.out.println("USERNAME:");
                String username = scanner.nextLine();
                System.out.println("PASSWORD");
                String password = scanner.nextLine();
                System.out.println("=========================================");

                loggedIn = userService.login(username,password);
                if(!loggedIn){
                    System.out.println("Incorrect, please try again");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return loggedIn;
    }
}
