package filemethods;

import model.Product;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileMethodsImpl implements FileMethods{

    @Override
    public List<Product> readProductsFromFile(String fileName) {
        List<Product> productList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Product product = new Product();
                    product.setProductCode(parts[0].trim());
                    product.setProductName(parts[1].trim());
                    product.setProductPrice(Double.parseDouble(parts[2].trim()));
                    product.setQty(Integer.parseInt(parts[3].trim()));
                    product.setDate(LocalDate.parse(parts[4].trim()));
                    productList.add(product);
                } else {
                    System.out.println("Invalid data in file: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return productList;
    }

    @Override
    public void writeToFile(List<Product> productList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product p : productList) {
                writer.write(p.getProductCode() + "," +
                        p.getProductName() + "," +
                        p.getProductPrice() + "," +
                        p.getQty() + "," +
                        p.getDate().toString());
                writer.newLine();
            }
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
