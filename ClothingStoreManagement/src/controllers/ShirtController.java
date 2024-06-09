package controllers;

import models.Pants;
import services.IPantsService;
import services.impl.PantsService;
import views.PantsView;

import java.io.File;
import java.util.List;

public class ShirtController {
    public static void main(String[] args) {
        PantsView pantsView = new PantsView();
        IPantsService pantsService = new PantsService() ;
        int choice ;
        int code;
        Pants pants;
        boolean result;
        List<Pants> pants1;
        while (true){
            choice = pantsView.view();
            switch (choice){
                case 1: {

                    try {
                        pants = pantsView.viewAdd(pantsService);
                        result = pantsService.add(pants);
                        pantsView.viewMessage(result);
                        break;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case 2: {
                    code = pantsView.inputCode();
                    Pants existsPants = pantsService.findByCode(code);
                    if (existsPants == null){
                        pantsView.viewMessage(false);
                    } else {
                        Pants newPants = pantsView.viewEdit(existsPants);
                        result = pantsService.update(newPants);
                        pantsView.viewMessage(result);
                    }
                }
                break;
                case 3:{
                    code = pantsView.inputCode();
                    pants = pantsService.findByCode(code);
                    if(pants == null){
                        pantsView.viewMessage(false);
                    } else {
                        boolean isConfirm = pantsView.confirmDelete(pants);
                        if(isConfirm){
                            pantsService.removeProduct(pants);
                            pantsView.viewMessage(true);
                        }
                    }
                }
                break;
                case 4:{
                    pants1 = pantsService.getAll();
                    pantsView.displayAllPants(pants1);
                }
                break;
                case 5:{
                    int searchChoice = pantsView.viewSearch();
                    switch (searchChoice){
                        case 1:{
                            String name = pantsView.inputName();
                            pants1 = pantsService.searchByName(name);
                            if(pants1 == null){
                                System.out.println("Not found your search: "+name+"'");
                            } else {
                                System.out.println("Your search:'"+name+"'");
                                pantsView.searchResultByName(pants1,name);
                            }
                        }
                        break;
                        case 2: {
                            code = pantsView.inputCode();
                            pants1 = pantsService.searchByCode(code);
                            if(pants1 == null){
                                System.out.println("Invalid code:'"+code+"'");
                            } else {
                                System.out.println("Your search:'"+code+"'");
                                pantsView.searchResultByCode(pants1,code);
                            }
                        }
                        break;
                    }
                }
                break;
                case 0:
                    return;
            }
            System.out.println();
        }
    }
}
