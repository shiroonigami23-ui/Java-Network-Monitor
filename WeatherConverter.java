
public class WeatherConverter {
    public static void main(String[] args) {
        // Example temperature in Celsius
        double celsius = 25.0;
        double fahrenheit = (celsius * 9/5) + 32;

        System.out.println("========================================");
        System.out.println("   JAVA WEATHER CONVERTER (DOCKER)      ");
        System.out.println("========================================");
        System.out.println("Input: " + celsius + "°C");
        System.out.println("Result: " + fahrenheit + "°F");
        System.out.println("========================================");
        System.out.println("Execution successful!");
    }
}
