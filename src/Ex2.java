import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class Ex2 {
    public static void main(String[] args) {
        String [] files = Ex2_1.createTextFiles(50,2,100);
        System.out.println(Arrays.toString(files));

        Long start = System.currentTimeMillis();
        System.out.println("TotalLines: " + Ex2_1.getNumOfLines(files));
        Long done = System.currentTimeMillis();
        System.out.println((done-start)*0.001);


        start = System.currentTimeMillis();
        System.out.println("TotalLines: " + Ex2_1.getNumOfLinesThreads(files));
        done = System.currentTimeMillis();
        System.out.println((done-start)*0.001);


        start = System.currentTimeMillis();
        System.out.println("TotalLines: " + Ex2_1.getNumOfLinesThreadPool(files));
        done= System.currentTimeMillis();
        System.out.println((done-start)*0.001);


    }
}