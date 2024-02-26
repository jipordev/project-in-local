package filemethods;

import model.Product;

import java.util.List;

public interface FileMethods {
    List<Product> readProductsFromFile(String fileName);

    void writeToFile(List<Product> productList, String fileName);
    String  backupFileDir();
    void writeTransferRecord(Product product, String transferFileName);
}
