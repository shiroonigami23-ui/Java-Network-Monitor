
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemMonitor {
    public static void main(String[] args) {
        System.out.println("🚀 Starting Advanced Java System Monitor...");

        // Use a Thread to simulate background monitoring
        Thread monitorThread = new Thread(() -> {
            try (PrintWriter writer = new PrintWriter(new FileWriter("system_log.txt", true))) {
                for (int i = 0; i < 5; i++) {
                    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
                    long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);

                    String logEntry = String.format("[%s] Free Memory: %dMB / Max Memory: %dMB",
                                                        timestamp, freeMemory, maxMemory);

                    System.out.println("Logging: " + logEntry);
                    writer.println(logEntry);
                    writer.flush();

                    Thread.sleep(2000); // Wait 2 seconds between logs
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        });

        monitorThread.start();
        try {
            monitorThread.join(); // Wait for monitoring to finish
            System.out.println("✅ Monitoring Complete. Logs saved to system_log.txt");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
