import filemethods.FileMethods;
import filemethods.FileMethodsImpl;
import methods.CRUD;
import methods.CRUDImpl;
import model.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static final String DATA_SOURCE_FILE = "product.bak";
    private static final String TRANSFER_FILE = "transproduct.bak";
    static CRUD crud = new CRUDImpl();
    static FileMethods fileMethods = new FileMethodsImpl();
    static Scanner scanner = new Scanner(System.in);
    public static  void main(String[] args) {
        do {
            int pageNumber =1;
            int pageSize= crud.setNewRow();
            List<Product> productList = new ArrayList<>();
            System.out.println("""
                ######################################################################
                (Dis)play (Ra)ndom (C)reate (R)ead (D)elete (U)pdate (S)earch Bac(k)up
                ######################################################################""");
            System.out.print("Please choose options : ");
            String op = scanner.nextLine();
            try {
                switch (op){
                    case "dis" -> {
                        productList = fileMethods.readProductsFromFile(DATA_SOURCE_FILE);
                        crud.displayAllProduct(productList, pageNumber, pageSize);
                    }
                    case "o" -> crud.setPageSize(scanner);
                    case "ra" -> crud.randomRecord(productList,DATA_SOURCE_FILE);
                    case "c" -> crud.createProduct(productList,DATA_SOURCE_FILE);
                    case "r" -> crud.readProduct(productList,TRANSFER_FILE);
                    case "d" -> crud.deleteProduct(productList,TRANSFER_FILE);
                    case "u" -> crud.updateProduct(productList,TRANSFER_FILE);
                    case "s" -> crud.searchProductByName(productList);
                    case "k" -> {
                        String backupDirectory = "backup/";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                        String timestamp = dateFormat.format(new Date());
                        String backupFileName = "backupfile_" + timestamp + ".bak";
                        String backupFilePath = backupDirectory + backupFileName;

                        System.out.print("Are you sure to Backup [Y/N]: ");
                        String ch = scanner.nextLine();

                        if (ch.equalsIgnoreCase("y")) {
                            crud.backUpData(DATA_SOURCE_FILE,backupFilePath);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}