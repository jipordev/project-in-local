package methods;

import filemethods.FileMethods;
import filemethods.FileMethodsImpl;
import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class CRUDImpl implements CRUD{
    static Scanner scanner = new Scanner(System.in);
    private static final String CONFIG_FILE = "config.bak";
    private static final String DATA_SOURCE_FILE = "product.bak";
    private static final String TRANSFER_FILE = "transproduct.bak";
    public static FileMethods fileMethods = new FileMethodsImpl();

    @Override
    public void randomRecord(List<Product> productList, String fileName) {
        System.out.print("Enter amount of record : ");
        int randomNumber = Integer.parseInt(scanner.nextLine());
        Product[] products = new Product[randomNumber];
        for (int i = 0; i<randomNumber; i++) {
            products[i] = new Product();
            products[i].setProductCode("CSTAD"+ i);
            products[i].setProductName("Product::"+i);
            products[i].setProductPrice(0.0);
            products[i].setQty(0);
            products[i].setDate(LocalDate.now());
        }
        productList.addAll(List.of(products));

        fileMethods.writeToFile(productList,DATA_SOURCE_FILE);
    }

    @Override
    public void createProduct(List<Product> productList, String fileName) {
        Product product = new Product();
        System.out.print("Enter CODE : ");
        product.setProductCode(scanner.nextLine());
        System.out.print("Enter NAME : ");
        product.setProductName(scanner.nextLine());
        System.out.print("Enter PRICE : ");
        product.setProductPrice(Double.parseDouble(scanner.nextLine()))
    ;
        System.out.print("Enter QTY : ");
        product.setQty(Integer.parseInt(scanner.nextLine()));
        product.setDate(LocalDate.now());
        productList.add(product);
        fileMethods.writeToFile(productList, DATA_SOURCE_FILE);
    }

    @Override
    public void deleteProduct(List<Product> productList, String fileName) {
        System.out.print("Enter code to delete: ");
        String codeToDelete = scanner.nextLine();
        productList.removeIf(product -> product.getProductCode().equalsIgnoreCase(codeToDelete));
        fileMethods.writeToFile(productList,DATA_SOURCE_FILE);
        System.out.println("#################");
        System.out.println("Product deleted successfully.");
    }

    @Override
    public void readProduct(List<Product> productList, String fileName) {
        productList = fileMethods.readProductsFromFile(DATA_SOURCE_FILE);
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE,ShownBorders.ALL);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        System.out.print("Enter product code : ");
        String code = scanner.nextLine();
        System.out.println("#######################################");
        table.addCell("     Product Code     ");
        table.addCell("     Product Name     ");
        table.addCell("     Product Price     ");
        table.addCell("     Product QTY     ");
        table.addCell("     Product Date     ");
        for (Product product : productList) {
            if (product.getProductCode().equals(code)){
                table.addCell(product.getProductCode(),cellStyle);
                table.addCell(product.getProductName(),cellStyle);
                table.addCell(product.getProductPrice().toString(),cellStyle);
                table.addCell(product.getQty().toString(),cellStyle);
                table.addCell(product.getDate().toString(),cellStyle);
            }
        }
        System.out.println(table.render());
    }

    @Override
    public void updateProduct(List<Product> productList, String fileName) {
        Product updateProduct = new Product();
        System.out.println("""
                1. Update all
                2. Update name
                3. Update Price
                4. Update Qty
                """);
        System.out.print("Choose option to update : ");
        int op = Integer.parseInt(scanner.nextLine());
        switch (op) {
            case 1 -> {
                System.out.print("Enter product code : ");
                String code = scanner.nextLine();
                for (Product product : productList) {
                    if (product.getProductCode().equals(code)) {
                        System.out.println("#######################################");
                        System.out.println("Product code  : " + product.getProductCode());
                        System.out.println("Product Name  : " + product.getProductName());
                        System.out.println("Product Price : " + product.getProductPrice());
                        System.out.println("Product QTY   : " + product.getQty());
                        System.out.println("Product Date  : " + product.getDate());

                        System.out.print("Enter new NAME : ");
                        updateProduct.setProductName(scanner.nextLine());
                        System.out.print("Enter new PRICE : ");
                        updateProduct.setProductPrice(Double.parseDouble(scanner.nextLine()));
                        System.out.print("Enter new QTY : ");
                        updateProduct.setQty(Integer.parseInt(scanner.nextLine()));
                        updateProduct.setDate(LocalDate.now());
                        updateProduct.setProductCode(product.getProductCode());
                        productList.set(productList.indexOf(product), updateProduct);
                    }
                }
            }
        }
    }
    @Override
    public int displayAllProduct(List<Product> productList, int pageNumber, int pageSize) {
        boolean isTrue;
        do {
            int startIndex = (pageNumber - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, productList.size());
            Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
            System.out.println("#######################################");
            table.addCell("     Product Code     ");
            table.addCell("     Product Name     ");
            table.addCell("     Product Price     ");
            table.addCell("     Product QTY     ");
            table.addCell("     Product Date     ");
            for (int i = startIndex; i < endIndex; i++) {
                Product product = productList.get(i);
                table.addCell(product.getProductCode(), cellStyle);
                table.addCell(product.getProductName(), cellStyle);
                table.addCell(product.getProductPrice().toString(), cellStyle);
                table.addCell(product.getQty().toString(), cellStyle);
                table.addCell(product.getDate().toString(), cellStyle);
            }
            System.out.println(table.render());
            System.out.println("o" + "~".repeat(125) + "o");
            int totalPage = productList.size() / pageSize;
            System.out.printf("Page: %d of %d \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   Total Records: %d%n", pageNumber, totalPage, productList.size());
            System.out.print("Page Navigation\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(F)irst  (P)revious  (G)oto  (N)ext  (L)ast \n");
            System.out.println("o" + "~".repeat(125) + "o");
            String option;
            isTrue = true;
            System.out.print(">(B)ack or Navigate Page :  ");
            option = scanner.nextLine().toLowerCase();
            switch (option) {
                case "f" ->
                    pageNumber = 1;

                case "p" -> {
                    if (pageNumber > 1) {
                        pageNumber--;
                    }
                }
                case "g" -> {
                    try {
                        System.out.print("> Enter Page Number : ");
                        int pageNo = Integer.parseInt(scanner.nextLine());
                        if (pageNo >= 1 && pageNo <= productList.size() / pageSize) {
                            pageNumber = pageNo;
                        } else {
                            System.out.println("Invalid page number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid page number.");
                    }
                }
                case "n" -> {
                    if (pageNumber < productList.size() / pageSize) {
                        pageNumber++;
                    }
                }
                case "l" ->
                    pageNumber = productList.size() / pageSize;

                case "b" ->
                    isTrue = false;

                default ->
                    System.out.println("Invalid Option.");

            }
        } while (isTrue);
        return pageSize;
    }
    @Override
    public void searchProductByName(List<Product> productList) {
        System.out.print("Enter product name or part of the name to search: ");
        String searchKeyword = scanner.nextLine().trim().toLowerCase();
        List<Product> searchResults = new ArrayList<>();

        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(searchKeyword)) {
                searchResults.add(product);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No products found matching the search criteria.");
        } else {
            displayAllProduct(searchResults, 1, productList.size());
        }
    }
    @Override
    public int setNewRow() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return 10;
    }
    @Override
    public void setPageSize(Scanner scanner) {
        System.out.println("#".repeat(20));
        System.out.println("Set Row to Display in Table");
        int newRowSize;
        do {
            System.out.print("Enter Row (greater than 0): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
            newRowSize = scanner.nextInt();
            scanner.nextLine();
        } while (newRowSize <= 0);
        System.out.print("Do you want to set new row size (Y/N): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            savePageSize(newRowSize);
        }
    }
    @Override
    public int savePageSize(int pageSize) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
             writer.write(String.valueOf(pageSize));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return pageSize;
    }
    @Override
    public void backUpData(String sourceFilePath, String backupFilePath) {
        try {
            Path sourcePath = Path.of(sourceFilePath);
            Path backupPath = Path.of(backupFilePath);
            if (Files.exists(sourcePath)) {
                // Create the backup directory if it doesn't exist
                Files.createDirectories(backupPath.getParent());

                // Use buffered streams for better IO performance
                try (InputStream inStream = Files.newInputStream(sourcePath);
                     OutputStream outStream = Files.newOutputStream(backupPath)) {
                    byte[] buffer = new byte[8192]; // Adjust buffer size as needed
                    int bytesRead;
                    while ((bytesRead = inStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("Backup created successfully.");
            } else {
                System.out.println("Source file does not exist.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

