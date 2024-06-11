package services.impl;

import models.Shirt;
import repositories.impl.ShirtRepository;
import services.IShirtService;

import java.util.ArrayList;
import java.util.List;

public class ShirtService implements IShirtService {
    private ShirtRepository shirtRepository = new ShirtRepository();

    @Override
    public boolean add(Shirt shirt) {
        if(shirt.getName().equals("")){
            return false;
        }
        if(shirt.getCode() <0 || shirt.getCode()> 100000 || shirt.getPrice()<0 ||shirt.getQuantity()<0){
            return false;
        }
        shirtRepository.add(shirt);
        return true;
    }

    @Override
    public List<Shirt> getAll() {
        return shirtRepository.getAll();
    }

    @Override
    public Shirt findByCode(int code) {
        return shirtRepository.findByCode(code);
    }

    @Override
    public void removeProduct(Shirt shirt) {
        shirtRepository.remove(shirt);
    }

    @Override
    public boolean update(Shirt newShirt) {
        List<Shirt> shirts1 = shirtRepository.getAll();
        for (int i = 0; i < shirts1.size(); i++) {
            if(shirts1.get(i).getCode() == newShirt.getCode()){
                Shirt oldShirt = shirts1.get(i);
                if(newShirt.getName() != null){
                    oldShirt.setName(newShirt.getName());
                }
                if (newShirt.getPrice() != -1){
                    oldShirt.setPrice((newShirt.getPrice()));
                }
                if (newShirt.getQuantity() != -1){
                    oldShirt.setQuantity(newShirt.getQuantity());
                }
                shirtRepository.update(oldShirt);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Shirt> searchByName(String name) {
        List<Shirt> shirts1 = this.getAll();
        List<Shirt> result = new ArrayList<>();
        for(Shirt shirt : shirts1){
            if(shirt.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(shirt);
            }
        }
        return result.isEmpty() ? null : result;
    }

    @Override
    public List<Shirt> searchByCode(int code) {
        List<Shirt> shirts1 = this.getAll();
        List<Shirt> result = new ArrayList<>();
        for(Shirt shirt : shirts1){
            if(shirt.getCode() == code){
                result.add(shirt);
            }
        }
        return result.isEmpty() ? null : result;
    }

    @Override
    public boolean codeExists(int code) {
        for(Shirt shirt : this.getAll()){
            if (shirt.getCode() == code){
                return true;
            }
        }
        return false;
    }
}
