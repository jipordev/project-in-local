package filemethods;

import model.Product;

import java.util.List;

public interface FileMethods {
    List<Product> readProductsFromFile(String fileName);

    void writeToFile(List<Product> productList, String fileName);
}
