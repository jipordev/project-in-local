import filemethods.FileMethods;
import filemethods.FileMethodsImpl;
import methods.CRUD;
import methods.CRUDImpl;
import model.Product;
import pagination.Pagination;
import pagination.PaginationImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DATA_SOURCE_FILE = "product.bak";
    private static final String TRANSFER_FILE = "transproduct.bak";

    static CRUD crud = new CRUDImpl();
    static Pagination pagination = new PaginationImpl();
    static FileMethods fileMethods = new FileMethodsImpl();
    static Scanner scanner = new Scanner(System.in);
    static List<Product> productList = new ArrayList<>(fileMethods.readProductsFromFile(TRANSFER_FILE));

    public static void main(String[] args) {
        do {
            int pageNumber = 1;
            int pageSize = pagination.setNewRow();
            System.out.println("""
                    ####################################################################
                    l:Display o:SetRow m:Random CRUD s:Search k:BackupData t:RestoreData
                    ####################################################################""");
            System.out.print("Please choose options : ");
            String op = scanner.nextLine();
            try {
                switch (op) {
                    case "l" -> crud.displayAllProduct(productList,pageNumber,pageSize);
                    case "o" -> pagination.setPageSize(scanner);
                    case "m" -> crud.randomRecord(productList);
                    case "w" -> crud.createProduct(productList);
                    case "r" -> crud.readProduct(productList);
                    case "d" -> crud.deleteProduct();
                    case "u" -> crud.updateProduct(productList);
                    case "s" -> crud.searchProductByName();
                    case "k" -> {
                        String backupFilePath = fileMethods.backupFileDir();
                        System.out.print("Are you sure to Backup [Y/N]: ");
                        String ch = scanner.nextLine();
                        if (ch.equalsIgnoreCase("y")) {
                            fileMethods.backUpData(DATA_SOURCE_FILE, backupFilePath);
                        }
                    }
                    case "c" -> {
                        // commit code here
                    }
                    case "t" -> fileMethods.restoreData();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
