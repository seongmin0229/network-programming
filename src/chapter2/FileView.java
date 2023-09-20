package chapter2;

import java.io.FileInputStream;
import java.io.IOException;

public class FileView {
    public static void main(String[] args){
        long start = System.nanoTime();


        FileInputStream fis = null;

        try{
            fis = new FileInputStream("C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\test.txt");
            int i = 0;
            while((i = fis.read()) != -1){
                System.out.write(i);
            }
        }catch(IOException e){
            System.out.println(e);
        }finally{
            try{
                fis.close();
            }catch(IOException e){

            }
        }

        long end = System.nanoTime();
        System.out.println("Run-time : " + (end-start) / 1000.0);
    }
}
