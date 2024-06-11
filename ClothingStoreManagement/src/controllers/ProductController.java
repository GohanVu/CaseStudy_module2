package controllers;

import views.impl.ProductView;

import java.util.Scanner;

public class ProductController {
    ProductView productView = new ProductView();
    Scanner scanner = new Scanner(System.in);
    public void run(){
        productView.displayProductView();
        handleUserChoice();
    }
    private void handleUserChoice(){
        int choice = -1;
        do {
            try {
                System.out.print("Input your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        ShirtController shirtController = new ShirtController();
                        shirtController.run();
                    case 2:
                        PantsController pantsController = new PantsController();
                        pantsController.run();
                    case 3:
                    case 0:
                        return;
                    default:
                        System.out.println("In valid option. Please try again");
                }
            } catch (NumberFormatException e){
                System.out.println("Choice must be a number, please try again");
            } catch (Exception e){
                System.out.println("Error, please try again! ");
            }
        } while (choice < 0 || choice > 2);
    }
}
