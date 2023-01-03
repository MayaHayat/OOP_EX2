package Part_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyThread extends Thread {
    private String fileName;
    private int numLines;

    public MyThread(String fileName){
        this.fileName = fileName;
    }

    public int getNumLines() {
        return numLines;
    }

    @Override
    public void run(){
        int count = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            while (reader.readLine()!= null){
                count++;
                //System.out.println(count + "    "+this.fileName);
            }
            this.numLines = count;
           // System.out.println("Thread started " + this.numLines);
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
