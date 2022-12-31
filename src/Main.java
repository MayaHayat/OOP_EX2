import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String [] files = Ex2_1.createTextFiles(50,2,100);
        System.out.println(Arrays.toString(files));

        Instant start = Instant.now();
        System.out.println("TotalLines: " + Ex2_1.getNumOfLines(files));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        start = Instant.now();
        System.out.println("TotalLines: " + Ex2_1.getNumOfLinesThreads(files));
        end = Instant.now();
        System.out.println(Duration.between(start, end));

        start = Instant.now();
        System.out.println("TotalLines: " + Ex2_1.getNumOfLinesThreadPool(files));
        end = Instant.now();
        System.out.println(Duration.between(start, end));

    }
}