
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NetworkMonitor {

    private static final String LOG_FILE = "network_log.txt";
    private static final List<String> TARGETS = List.of(
        "https://google.com", 
        "https://github.com", 
        "https://stackoverflow.com",
        "https://invalid-url-example.com"
    );
    private static final HttpClient CLIENT = HttpClient.newBuilder()
                                                .connectTimeout(Duration.ofSeconds(5))
                                                .build();

    public static void main(String[] args) {
        System.out.println("🌐 Initializing Scheduled Network Latency Monitor...");
        System.out.println("Results will be logged to " + LOG_FILE);

        // Schedule the monitoring task to run every 10 seconds
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(NetworkMonitor::performLatencyCheck, 0, 10, TimeUnit.SECONDS);

        // Optionally, shut down the scheduler after some time (e.g., 5 minutes for demonstration)
        // In a real application, this might run indefinitely or be managed by a service wrapper
        // scheduler.schedule(() -> {
        //     scheduler.shutdown();
        //     System.out.println("Monitoring finished.");
        // }, 5, TimeUnit.MINUTES);
    }

    private static void performLatencyCheck() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = dtf.format(LocalDateTime.now());

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("
--- Latency Check at " + timestamp + " ---");
            for (String url : TARGETS) {
                long start = System.currentTimeMillis();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                String logEntry;
                try {
                    HttpResponse<Void> response = CLIENT.send(request, HttpResponse.BodyHandlers.discarding());
                    long latency = System.currentTimeMillis() - start;
                    logEntry = String.format("[%s] %s - Latency: %dms (Status: %d)", timestamp, url, latency, response.statusCode());
                } catch (IOException | InterruptedException e) {
                    logEntry = String.format("[%s] %s - Failed to reach: %s", timestamp, url, e.getMessage());
                }
                System.out.println(logEntry);
                writer.println(logEntry);
                writer.flush(); // Ensure log is written immediately
            }
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
