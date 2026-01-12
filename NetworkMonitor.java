
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class NetworkMonitor {
    public static void main(String[] args) {
        List<String> targets = List.of("https://google.com", "https://github.com", "https://stackoverflow.com");
        HttpClient client = HttpClient.newBuilder()
                        .connectTimeout(Duration.ofSeconds(5))
                                        .build();

        System.out.println("🌐 Initializing Network Latency Check...");

        targets.forEach(url -> {
            long start = System.currentTimeMillis();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

            try {
                HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
                long latency = System.currentTimeMillis() - start;
                System.out.printf("[%d] %s - Latency: %dms%n", response.statusCode(), url, latency);
            } catch (Exception e) {
                System.out.println("❌ Failed to reach " + url + ": " + e.getMessage());
            }
        });
    }
}
