package Part_1;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Ex2 {
    public static void main(String[] args) {
        String [] files = Ex2_1.createTextFiles(50,2,100);
        System.out.println(Arrays.toString(files));

        Instant start = Instant.now();
        System.out.println("TotalLines: " + Ex2_1.getNumOfLines(files));
        Instant done = Instant.now();
        System.out.println(Duration.between(start, done));

        start = Instant.now();
        Ex2_1 function3 = new Ex2_1();
        System.out.println("TotalLines: " + function3.getNumOfLinesThreads(files));
        done = Instant.now();
        System.out.println(Duration.between(start, done));


        start = Instant.now();
        Ex2_1 function4 = new Ex2_1();
        System.out.println("TotalLines: " + function4.getNumOfLinesThreadPool(files));
        done = Instant.now();
        System.out.println(Duration.between(start, done));


    }
}