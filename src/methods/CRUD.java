package methods;

import model.Product;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public interface CRUD {
    void randomRecord(List<Product> productList,String fileName);
    void createProduct();
    void deleteProduct();
    void readProduct(List<Product> productList, String fileName);
    void updateProduct(List<Product> productList, String fileName);
    void backUpData(String sourceFilePath, String backupFilePath);
    int displayAllProduct(List<Product> productList, int pageNumber, int pageSize);
    void searchProductByName(List<Product> productList);
}
