package methods;

import model.Product;

import java.util.List;
import java.util.Scanner;

public interface CRUD {
    void randomRecord(List<Product> productList,String fileName);
    void createProduct(List<Product> productList,String fileName);
    void deleteProduct(List<Product> productList,String fileName);
    void readProduct(List<Product> productList,String fileName);
    void updateProduct(List<Product> productList, String fileName);
    void backUpData(String sourceFilePath, String backupFilePath);
    int displayAllProduct(List<Product> productList, int pageNumber, int pageSize);
    void searchProductByName(List<Product> productList);
    int setNewRow();
    void setPageSize(Scanner scanner);
    int savePageSize(int pageSize);

}