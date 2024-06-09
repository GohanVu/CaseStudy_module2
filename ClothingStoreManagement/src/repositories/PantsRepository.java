package repositories;

import models.Pants;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PantsRepository {
    private static final String SRC_PANTS = "D:\\Codegym\\module2\\CaseStudy_module2\\ClothingStoreManagement\\src\\data\\pants.csv";
    private static List<Pants> pants = new LinkedList<>();
    static {
        File file = new File(SRC_PANTS);
        if(!file.exists()){
            try {
                file.createNewFile();
                Pants pants1 = new Pants(01,"Skinny Jeans",300000,17);
                writeFile(Collections.singletonList(pants1),false);
            } catch (Exception e){
                System.out.println("Error creating new file");
            }

        }
    }

    private static void writeFile(List<Pants> pants, boolean append) {
        File file = new File(SRC_PANTS);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(file,append);
            bufferedWriter = new BufferedWriter(fileWriter);
            if(!append){
                bufferedWriter.write("CODE,NAME,PRICE,QUANTITY");
                bufferedWriter.newLine();
            }
            for (Pants pants1 : pants){
                bufferedWriter.write(toString(pants1));
                bufferedWriter.newLine();
            }
        } catch (IOException e){
            System.out.println("File writing error");
        } finally {
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e){
                    System.out.println("File closing error");
                }
            }
        }
    }
    private static String toString (Pants pants){
        return pants.getCode()+","+pants.getName()+","+pants.getPrice()+","+pants.getQuantity();
    }
    public void add (Pants pants){
        List<Pants> pants1 = getAll();
        pants1.add(pants);
        writeFile(Collections.singletonList(pants),true);
    }

    public List<Pants> getAll() {
        List<Pants> pants1 = new LinkedList<>();
        File file = new File(SRC_PANTS);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine())!=null){
                String[] temp = line.split(",");
                pants1.add(new Pants(Integer.parseInt(temp[0]),temp[1],Double.parseDouble(temp[2]),Integer.parseInt(temp[3])));
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
        return pants1;
    }
    public  Pants findByCode(int code){
        List<Pants> pants1 = getAll();
        for (Pants pants2 : pants1){
            if(pants2 == null){
                return null;
            }
            if(pants2.getCode()== code){
                return pants2;
            }
        }
        return null;
    }
    public void removePants (Pants pants){
        List<Pants> pants1 = getAll();
        int size = pants1.size();
        for (int i = 0; i < size; i++) {
            if(pants1.get(i).getCode() == (pants.getCode())){
                pants1.remove(i);
                System.out.println("Pants "+pants.getCode());
                break;
            }
        }
        writeFile(pants1,false);
    }
    public void updatePantsToRepo(Pants updatePants){
        List<Pants> pants1 = getAll();
        for (int i = 0; i < pants1.size(); i++) {
            if(pants1.get(i).getCode() == updatePants.getCode()){
                pants1.set(i,updatePants);
                break;
            }
        }
        writeFile(pants1,false);
    }
}
