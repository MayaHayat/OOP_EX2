package Part_1;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Ex2_1Test {
    @Test
    public void MyTests(){
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

    @Test
    public void anotherTest(){
        String [] files = Ex2_1.createTextFiles(1000,2,1000);
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

        System.out.println("Note how big the difference is here with and without threads.");
    }

}