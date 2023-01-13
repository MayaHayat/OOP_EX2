package Part_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {


    /**
     * This function creates n different files where each file has a random number of lines with more than 10 chars.
     * @param n is the number of files created
     * @param seed is used to create the random numbers.
     * @param bound is the largest random that can be created.
     * @return an array of names of files.
     */
    public static String[] createTextFiles(int n, int seed, int bound){
        String [] namesOfFiles = new String [n];
        try{
            Random rand = new Random(seed);
            for (int i = 1; i <= n; i++) {
                int x = rand.nextInt(bound);
                File file = new File ("File_" + i + ".txt");
                namesOfFiles[i-1] = file.getName();
                file.createNewFile();
                int numLines = x;
                //System.out.println(x);
                FileWriter fw = new FileWriter(file);
                for (int j = 0 ; j < numLines ; j++){
                    fw.write("This is line number " + j + "\n");
                }
                fw.close();
            }
        }
        catch (IOException e){
            System.out.println("Had a problem creating this file.");
            e.printStackTrace();
        }
        return namesOfFiles;
    }

    /**
     * This function calculates the number of lines in each file and adds them together.
     * @param fileNames are all the names of files created in the previous function.
     * @return the total number of lines in all files.
     */

    public static int getNumOfLines(String[] fileNames){
        int totalLines = 0;
        try{
           for (int i = 0 ; i < fileNames.length ; i++){
               BufferedReader reader = new BufferedReader(new FileReader(fileNames[i]));
               String line;
               while ((line = reader.readLine()) != null) {
                   totalLines++;
               }
               //System.out.println(totalLines);

               reader.close();
           }
       }
       catch(IOException e){
           e.printStackTrace();
       }
        return totalLines;
    }

    /**
     * This function uses threads to calculate the number of total lines.
     * @param fileNames is the names of the files to count lines of.
     * @return the total number of lines of all files.
     */


    public int getNumOfLinesThreads(String[] fileNames){
        int totalLines = 0;

        MyThread[] threads = new MyThread[fileNames.length];

        // create and start each thread
        for (int i = 0 ; i < fileNames.length ; i++){
            threads[i] = new MyThread(fileNames[i]);
            threads[i].start();
        }

        // wait for all threads to complete
        for (int i = 0 ; i < fileNames.length ; i++){
            try{
                threads[i].join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            int num = threads[i].getNumLines();
            totalLines += num;
        }

        return totalLines;
    }



    public int getNumOfLinesThreadPool(String[] fileNames){
        int totalLines = 0;

        ExecutorService threads = Executors.newFixedThreadPool(fileNames.length);
        ArrayList<Future<Integer>> tasks = new ArrayList<>();
        for (int i = 0 ; i < fileNames.length ; i++){
            MyThreadCallable task = new MyThreadCallable(fileNames[i]);

            tasks.add(threads.submit(task));
        }
        for (Future<Integer> taskcurr : tasks) {
            try {
                totalLines += taskcurr.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        threads.shutdown();
        return totalLines;
    }



}
