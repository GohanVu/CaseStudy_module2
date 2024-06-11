package views.impl;

import models.Shirt;
import services.IShirtService;
import views.IShirtView;

import java.util.List;
import java.util.Scanner;

public class ShirtView  implements IShirtView {
    @Override
    public int view() {
        System.out.println("=========================================");
        System.out.println("||            Shirt View             ||");
        System.out.println("=========================================");
        System.out.println("||          1. New product           ||");
        System.out.println("||          2. Edit product          ||");
        System.out.println("||          3. Delete product        ||");
        System.out.println("||          4. Show all              ||");
        System.out.println("||          5. Search product        ||");
        System.out.println("||          0. Back to product view  ||");
        System.out.println("=========================================");
        Scanner scanner = new Scanner(System.in);
        int choice = -1 ;
        do {
            try{
                System.out.print("Input your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Choice must be a number, please try again");
            } catch (Exception e){
                System.out.println("Error, please try again! ");
            }
        } while (choice < 0 || choice > 5 );
        return choice ;
    }

    @Override
    public void viewMessage(boolean result) {
        if(result){
            System.out.println("Successful");
        } else {
            System.out.println("Failure");
        }
    }

    @Override
    public void searchResultByName(List<Shirt> items, String userType) {
        System.out.println("=============================================================");
        System.out.println("                     RESULT for '"+userType+"'");
        System.out.println("=============================================================");
        printHeader();
        for (Shirt shirt : items){
            System.out.println(infoShirt(shirt));
        }
        System.out.println("==============================================================");
    }

    @Override
    public void searchResultByCode(List<Shirt> items, int userType) {
        System.out.println("=============================================================");
        System.out.println("                     RESULT for '"+userType+"'");
        System.out.println("=============================================================");
        printHeader();
        for (Shirt shirt : items){
            System.out.println(infoShirt(shirt));
        }
        System.out.println("==============================================================");
    }
    private String infoShirt (Shirt shirt){
        return String.format("| %-5d | %-25s | %-10.2f | %-8d |",
                shirt.getCode(),
                shirt.getName(),
                shirt.getPrice(),
                shirt.getQuantity());
    }

    @Override
    public void printHeader() {
        System.out.println(String.format("| %-5s | %-25s | %-10s | %-3s |", "CODE", "NAME", "PRICE", "QUANTITY"));

    }

    @Override
    public void displayAllItems(List<Shirt> items) {
        System.out.println("=============================================================");
        System.out.println("||                    Shirt list                           ||");
        System.out.println("=============================================================");
        printHeader();
        for (Shirt shirt : items){
            System.out.println(infoShirt(shirt));
        }
        System.out.println("==============================================================");
    }

    @Override
    public int inputCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your code: ");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public boolean confirmDelete(Shirt item) {
        System.out.println("Do you want delete the product with code "+item.getCode()+": "+item.getName());
        System.out.println("Press Y to confirm, N to cancel");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().toUpperCase();
        if(confirm.equals("Y")){
            return true;
        } else if (confirm.equals("N")){
            return false;
        } else {
            System.out.println("Invalid input, please try again");
            return confirmDelete(item);
        }
    }

    @Override
    public String inputName() {
        System.out.println("Enter name: ");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public Shirt viewEdit(Shirt oldItem) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new shirt name (press Enter to keep old name): ");
        String name = scanner.nextLine();
        if(name.isEmpty()){
            name = oldItem.getName();
        }
        double price = oldItem.getPrice();
        try {
            System.out.println("Enter new shirt price (press Enter to keep old price): ");
            String priceInput = scanner.nextLine();
            if(!priceInput.isEmpty()){
                price = Double.parseDouble(priceInput);
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Keep the old price.");
        }
        int quantiy = oldItem.getQuantity();
        try {
            System.out.println("Enter new shirt quantity (press Enter to keep old quantity): ");
            String quantityInput = scanner.nextLine();
            if(!quantityInput.isEmpty()){
                quantiy = Integer.parseInt(quantityInput);
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Keep the old quantity");
        }
         return new Shirt(oldItem.getCode(),name,price,quantiy);
    }

    @Override
    public int viewSearch() {
        System.out.println("--------------Search Shirt--------------");
        System.out.println("1.Search by name");
        System.out.println("2.Search by code");
        System.out.println("0.Back to shirt view");
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do {
            try {
                System.out.println("Input your choice");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Choice must be a number");
            } catch (Exception e ){
                System.out.println("Error, please try again");
            }
        } while (choice < 0 || choice > 2);
        return choice;
    }
    public Shirt viewAdd(IShirtService shirtService){
        Scanner scanner = new Scanner(System.in);
        int code = -1;
        while (code < 0){
            try {
                System.out.println("Input code: ");
                code = Integer.parseInt(scanner.nextLine());
                if(code <0 ){
                    System.out.println("Code cannot be negative, please try again");
                } else if (shirtService.codeExists(code)){
                    System.out.println("Code already exists, please try again");
                     code = -1;
                }
            } catch (NumberFormatException e){
                System.out.println("Code must be a number, please try again");
            }
        }
        System.out.println("Input name: ");
        String name = scanner.nextLine();

        double price = -1;
        while (price< 0){
            try {
                System.out.println("Input price: ");
                price = Double.parseDouble(scanner.nextLine());
                if(price < 0){
                    System.out.println("Price must be a number, please try again");
                    price = -1;
                }
            } catch (NumberFormatException e){
                System.out.println("Price must be a number, please try again");
            }
        }
        int quantity = -1;
        while (quantity < 0){
            try {
                System.out.println("Input quantity: ");
                quantity = Integer.parseInt(scanner.nextLine());
                if(quantity < 0){
                    System.out.println("Quantity cannot be negative number, please try again");
                    quantity = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Quantity must be a number, please try again");
            }
        }
        Shirt shirt = new Shirt(code,name,price,quantity);
        return shirt;
    }
}
