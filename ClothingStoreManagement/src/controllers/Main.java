package controllers;

import views.impl.UserView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        boolean loggedIn = userView.login();
        if(loggedIn){
            ProductController productController= new ProductController();
            productController.run();
        }
    }
}



