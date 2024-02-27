package methods;

import model.Product;

import java.util.List;

public interface CRUD {
    void randomRecord(List<Product> productList);
    void createProduct(List<Product> productList);
    void deleteProduct();
    void readProduct(List<Product> productList);
    void updateProduct(List<Product> productList);
    void displayAllProduct(List<Product> productList, int pageNumber, int pageSize);
    void searchProductByName();
}
