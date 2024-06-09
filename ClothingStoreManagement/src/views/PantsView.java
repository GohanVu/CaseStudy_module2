package views;

import models.Pants;
import models.Shirt;
import services.IPantsService;
import services.impl.PantsService;

import java.util.List;
import java.util.Scanner;

public class PantsView {
    public int view (){
        System.out.println("=========================================");
        System.out.println("||             Pants View              ||");
        System.out.println("=========================================");
        System.out.println("||          1. New product             ||");
        System.out.println("||          2. Edit product            ||");
        System.out.println("||          3. Delete product          ||");
        System.out.println("||          4. Show all                ||");
        System.out.println("||          5. Search product          ||");
        System.out.println("||          0. Exit                    ||");
        System.out.println("=========================================");
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do {
            try {
                System.out.print("Input your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Choice must be a number, please try again");
            } catch (Exception e){
                System.out.println("Error,please try again!");
            } finally {
                System.out.println();
            }
        } while (choice < 0 || choice > 5);
        return choice;
    }
    public Pants viewAdd(IPantsService pantsService){
        Scanner scanner = new Scanner(System.in);
        int code = -1;
        while (code<0){
            try {
                System.out.println("Input code: ");
                code = Integer.parseInt(scanner.nextLine());
                if(code<0){
                    System.out.println("Code cannot be negative,please try again");
                } else if (pantsService.codeExists(code)){
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
        while (price<0){
            try{
                System.out.println("Input price:");
                price = Double.parseDouble(scanner.nextLine());
                if (price <0){
                    System.out.println("Price cannot be negative number, please try again");
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
                if (quantity <0){
                    System.out.println("Quantity cannot be negative number, please try again");
                    quantity = -1;
                }
            } catch (NumberFormatException e){
                System.out.println("Quantity must be a number, please try again");
            }
        }
        Pants pants = new Pants(code,name,price,quantity);
        return pants;
    }
    public void viewMessage(boolean result){
        if(result){
            System.out.println("Successful");
        } else {
            System.out.println("Failure");
        }
    }
    public void searchResultByName(List<Pants> pants , String userType){
        System.out.println("=============================================================");
        System.out.println("                 RESULT for '"+userType+"'");
        System.out.println("=============================================================");
        printHeader();
        for (Pants pants1 : pants){
            System.out.println(infoShirt(pants1));
        }
        System.out.println("==============================================================");
    }
    public void searchResultByCode(List<Pants> pants , int userType){
        System.out.println("=============================================================");
        System.out.println("                 RESULT for '"+userType+"'");
        System.out.println("=============================================================");
        printHeader();
        for (Pants pants1 : pants){
            System.out.println(infoShirt(pants1));
        }
        System.out.println("==============================================================");
    }
    private String infoShirt(Pants pants1) {
        return String.format("| %-5d | %-25s | %-10.2f | %-8d |",
                pants1.getCode(),
                pants1.getName(),
                pants1.getPrice(),
                pants1.getQuantity());
    }
    private void printHeader() {
        System.out.println(String.format("| %-5s | %-25s | %-10s | %-3s |", "CODE", "NAME", "PRICE", "QUANTITY"));
    }
    public boolean confirmDelete(Pants pants){
        System.out.println("Do you want delete the product with code "+pants.getCode()+": "+pants.getName());
        System.out.println("Press Y to confirm, N to cancel");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().toUpperCase();
        if(confirm.equals("Y")){
            return true;
        } else if (confirm.equals("N")){
            return false;
        } else {
            System.out.println("Invalid input, please try again");
            return  confirmDelete(pants);
        }
    }
    public String inputName(){
        System.out.println("Enter name: ");
        return new Scanner(System.in).nextLine();
    }
    public Pants viewEdit (Pants oldPants){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new pants name (press Enter to keep old name-'"+oldPants.getName()+"'-): ");
        String name = scanner.nextLine();
        if (name.isEmpty()){
            name = oldPants.getName();
        }
        double price = oldPants.getPrice();
        try{
            System.out.println("Enter new pants price (press Enter to keep old price '"+oldPants.getPrice()+"'): ");
            String priceInput = scanner.nextLine();
            if (!priceInput.isEmpty()){
                price = Double.parseDouble(priceInput);
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Keeping the old price '"+oldPants.getPrice()+"'");
        }
        int quantity = oldPants.getQuantity();
        try{
            System.out.println("Enter new pants quantity (press Enter to keep old quantity '"+oldPants.getQuantity()+"'): ");
            String quantityInput = scanner.nextLine();
            if (!quantityInput.isEmpty()){
                quantity = Integer.parseInt(quantityInput);
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Keeping the old quantity '"+oldPants.getQuantity()+"'");

        }
        return new Pants(oldPants.getCode(),name,price,quantity);
    }
    public int viewSearch(){
        System.out.println("--------------Search Product--------------");
        System.out.println("1.Search by name");
        System.out.println("2.Search by code");
        System.out.println("0.Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do{
            try{
                System.out.println("Input your choice");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Choice must be a number");
            } catch (Exception e){
                System.out.println("Error, please try again");
            } finally {
                System.out.println();
            }
        } while (choice<0 || choice > 2);
        return choice;
    }

    public int inputCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your code: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayAllPants(List<Pants> pants1) {
        System.out.println("=============================================================");
        System.out.println("||                    Shirt list                           ||");
        System.out.println("=============================================================");
        printHeader();
        for (Pants pants : pants1){
            System.out.println(infoShirt(pants));
        }
        System.out.println("==============================================================");
    }
}
