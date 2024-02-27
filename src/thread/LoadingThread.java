package thread;

import filemethods.FileMethods;
import filemethods.FileMethodsImpl;

import java.util.concurrent.TimeUnit;


public class LoadingThread {
    private static final String DATA_SOURCE_FILE = "product.bak";
    static FileMethods fileMethods = new FileMethodsImpl();
    public void loadingThread(){
        Thread loadingThread = new Thread(() -> {
            boolean hasData = fileMethods.hasData(DATA_SOURCE_FILE);
            if (hasData) {
                System.out.println("Loading existing data..");
                try {
                    // Simulate loading time with increasing dots
                    for (int i = 0; i < 10; i++) {
                        System.out.print("."); // Print a dot
                        TimeUnit.SECONDS.sleep(1); // Wait for 1 second
                        // Print additional dots based on the current iteration
                        for (int j = 0; j < i; j++) {
                            System.out.print(".");
                        }
                        System.out.print("\r"); // Move cursor back to the beginning of the line
                        TimeUnit.MILLISECONDS.sleep(50);
                    }
                    System.out.println("\nData loaded successfully."); // Print message when loading is completed
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage()); // Handle InterruptedException appropriately
                }
            }
        });
        loadingThread.start();
    }
}
