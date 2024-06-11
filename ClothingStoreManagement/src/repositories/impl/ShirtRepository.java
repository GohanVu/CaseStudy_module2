package repositories.impl;

import models.Shirt;
import repositories.IPantsRepositories;
import repositories.IShirtRepositories;

import java.io.*;

import java.util.*;

public class ShirtRepository implements IShirtRepositories {
    private static final String SRC_SHIRT = "D:\\Codegym\\module2\\CaseStudy_module2\\ClothingStoreManagement\\src\\data\\shirt.csv";
    private static List<Shirt> shirts = new LinkedList<>();
    static {
        File file = new File(SRC_SHIRT);
        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Error creating new file");
            }
        }
    }

    private static void writeFileShirt(List<Shirt> shirts, boolean append) {
        File file = new File(SRC_SHIRT);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(file,append);
            bufferedWriter = new BufferedWriter(fileWriter);
            if(!append){
                bufferedWriter.write("CODE,NAME,PRICE,QUANTITY");
                bufferedWriter.newLine();
            }
            for (Shirt temp : shirts){
                bufferedWriter.write(toString(temp));
                bufferedWriter.newLine();
            }
        } catch (IOException e){
            System.out.println("File writing error");
        } finally {
            if(bufferedWriter != null){
                try{
                    bufferedWriter.close();
                } catch (IOException e){
                    System.out.println("File closing error");
                }
            }
        }
    }

    private static String toString(Shirt shirt) {
        return shirt.getCode()+ ","+ shirt.getName()
                + ","+ shirt.getPrice() + "," +shirt.getQuantity();
    }

    @Override
    public void add(Shirt item) {
        List<Shirt> shirts1 = getAll();
        shirts1.add(item);
        writeFileShirt(Collections.singletonList(item),true);
    }

    @Override
    public List<Shirt> getAll() {
        List<Shirt> shirts1 = new LinkedList<>();
        File file = new File(SRC_SHIRT);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            bufferedReader.readLine();
            while ((line=bufferedReader.readLine())!= null){
                String [] temp = line.split(",");
                shirts1.add(new Shirt(Integer.parseInt(temp[0]),temp[1],Double.parseDouble(temp[2]),Integer.parseInt(temp[3])));
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println("File reading error");
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e){
                    System.out.println("File closing error");
                }
            }
        }
        return shirts1;
    }

    @Override
    public Shirt findByCode(int code) {
        List<Shirt> shirts1 = getAll();
        for (Shirt shirt : shirts1){
            if(shirt == null){
                return null;
            }
            if(shirt.getCode() == code){
                return shirt;
            }
        }
        return null;
    }

    @Override
    public void remove(Shirt item) {
        List<Shirt> shirts1 = getAll();
        int size = shirts1.size();
        for (int i = 0; i < size; i++) {
            if(shirts1.get(i).getCode() == (item.getCode())){
                shirts1.remove(i);
                System.out.println("Shirt "+ item.getCode());
                break;
            }
        }
        writeFileShirt(shirts1,false);
    }

    @Override
    public void update(Shirt updateItem) {
        List<Shirt> shirts1 = getAll();
        for (int i = 0; i < shirts1.size(); i++) {
            if(shirts1.get(i).getCode() == updateItem.getCode()){
                shirts1.set(i,updateItem);
                break;
            }
        }
        writeFileShirt(shirts1,false);
    }
}
