package services.impl;

import models.Pants;
import repositories.PantsRepository;
import services.IPantsService;

import java.util.ArrayList;
import java.util.List;

public class PantsService implements IPantsService {
    private PantsRepository pantsRepository = new PantsRepository();
    @Override
    public boolean add(Pants pants) {
        if(pants.getName().equals("")){
            return false;
        }
        if (pants.getCode()<0|| pants.getCode()>1000000|| pants.getPrice()<0|| pants.getQuantity()<0){
            return false;
        }
        pantsRepository.add(pants);
        return true;
    }

    @Override
    public List<Pants> getAll() {
        return pantsRepository.getAll();
    }

    @Override
    public Pants findByCode(int code) {
        return pantsRepository.findByCode(code);
    }

    @Override
    public void removeProduct(Pants pants) {
        pantsRepository.removePants(pants);
    }

    @Override
    public boolean update(Pants newPants) {
        List<Pants> pants1 = pantsRepository.getAll();
        for (int i = 0; i < pants1.size(); i++) {
            if(pants1.get(i).getCode() == newPants.getCode()){
                Pants oldPants = pants1.get(i);
                if(newPants.getName() !=null){
                    oldPants.setName(newPants.getName());
                }
                if(newPants.getPrice()!= -1){
                    oldPants.setName(newPants.getName());
                } if(newPants.getQuantity() != -1){
                    oldPants.setQuantity(newPants.getQuantity());
                }
                pantsRepository.updatePantsToRepo(oldPants);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Pants> searchByName(String name) {
        List<Pants> pants1 = this.getAll();
        List<Pants> result = new ArrayList<>();
        for (Pants pants : pants1){
            if(pants.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(pants);
            }
        }
        return result.isEmpty() ? null : result;
    }

    @Override
    public List<Pants> searchByCode(int code) {
        List<Pants> pants1 = this.getAll();
        List<Pants> result  = new ArrayList<>();
        for(Pants pants : pants1){
            if (pants.getCode() == code) {
                result.add(pants);
            }
        }
        return result.isEmpty() ? null : result;
    }

    @Override
    public boolean codeExists(int code) {
        for(Pants pants : this.getAll()){
            if(pants.getCode() == code){
                return true;
            }
        }
        return false;
    }

}
