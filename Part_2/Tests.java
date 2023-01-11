package Part_2;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void partialTest(){
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        var sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum = (int)sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };
        // var is used to infer the declared type automatically
        var priceTask = customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        var reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = priceTask.get();
            reversed = reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        logger.info(()-> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }

    @Test
    public void otherTests() {
        CustomExecutor customExecutor = new CustomExecutor();
        customExecutor.setCorePoolSize(1);
        customExecutor.setMaximumPoolSize(1);

        for (int i = 0; i < 5; i++) {
            Callable<String> testIO = () -> {
                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                return sb.reverse().toString();
            };

            var reverseTask1 = customExecutor.submit(testIO, TaskType.IO);
            var task = Task.createTask(()->{
                int sum = 0;
                for (int j = 1; j <= 10; j++) {
                    sum += j;
                }
                return sum;
            }, TaskType.COMPUTATIONAL);
            var sumTask = customExecutor.submit(task);
            var testMath = customExecutor.submit(() -> {
                return 1000 * Math.pow(1.02, 5);
            }, TaskType.COMPUTATIONAL);

            Callable<String> testIO2 = () -> {
                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                return sb.reverse().toString();
            };
            var testt = customExecutor.submit(testIO2, TaskType.IO);
            //System.out.println(customExecutor.getQueueP().toString());
            customExecutor.getCurrentMax();
            final String get1;
            final double get2;
            final int get3;
            try {
                get1 = testt.get();
                get2 = testMath.get();
                get3 = (int) sumTask.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            logger.info(()-> "Reversed String = " + get1);
            logger.info(()->String.valueOf("Total Price = " + get2));
            logger.info(()-> "Current maximum priority = " +
                    customExecutor.getCurrentMax());
        }
        customExecutor.gracefullyTerminate();
    }

    @Test
    public void MyTest(){
        CustomExecutor customExecutor = new CustomExecutor();
        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };

        customExecutor.submit(Task.createTask(callable1, TaskType.OTHER));

        customExecutor.submit(Task.createTask(callable1, TaskType.COMPUTATIONAL));
        logger.info(()-> "Current maximum priority = " +
                customExecutor.getCurrentMax());

        TaskType.OTHER.setPriority(5);
        //logger.info(()-> String.valueOf(customExecutor.getSize()));
        customExecutor.submit(Task.createTask(callable1, TaskType.OTHER));

        logger.info(()-> "Current maximum priority = " +
                customExecutor.getCurrentMax());


    }




}