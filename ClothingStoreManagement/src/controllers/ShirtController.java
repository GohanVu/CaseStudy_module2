package controllers;

import models.Shirt;
import services.IShirtService;
import services.impl.ShirtService;
import views.impl.ShirtView;

import java.util.List;

public class ShirtController {
    ShirtView shirtView = new ShirtView();
    IShirtService shirtService = new ShirtService();
    ProductController productController = new ProductController();
    int choice;
    int code;
    Shirt shirt;
    boolean result;
    List<Shirt> shirts;
    public void  run(){
        while (true){
            choice = shirtView.view();
            switch (choice){
                case 1:
                    shirt = shirtView.viewAdd(shirtService);
                    result = shirtService.add(shirt);
                    shirtView.viewMessage(result);
                    break;
                case 2:
                    code = shirtView.inputCode();
                    Shirt existShirt = shirtService.findByCode(code);
                    if (existShirt == null){
                        shirtView.viewMessage(false);
                    } else {
                        Shirt newShirt = shirtView.viewEdit(existShirt);
                        result = shirtService.update(newShirt);
                        shirtView.viewMessage(result);
                    }
                    break;
                case 3:
                    code = shirtView.inputCode();
                    shirt = shirtService.findByCode(code);
                    if(shirt == null){
                        shirtView.viewMessage(false);
                    } else {
                        shirtService.removeProduct(shirt);
                        shirtView.viewMessage(true);
                    }
                    break;
                case 4:
                    shirts = shirtService.getAll();
                    shirtView.displayAllItems(shirts);
                case 5:
                    int searchChoice = shirtView.viewSearch();
                    switch (searchChoice){
                        case 1:
                            String name  = shirtView.inputName();
                            shirts = shirtService.searchByName(name);
                            if(shirts == null){
                                System.out.println("Not found your search:'"+name+"'");
                            } else {
                                System.out.println("Your search: '"+name+"'");
                                shirtView.searchResultByName(shirts,name);
                            }
                            break;
                        case 2:
                            code = shirtView.inputCode();
                            shirts = shirtService.searchByCode(code);
                            if(shirts == null){
                                System.out.println("Invalid code: '"+code+"'");
                            } else {
                                System.out.println("Your search: '"+code+"'");
                                shirtView.searchResultByCode(shirts,code);
                            }
                            break;
                    }
                    break;
                case 0:
                    productController.run();
                    return ;
            }
        }
    }

}
