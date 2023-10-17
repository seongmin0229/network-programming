package chapter2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {
    public static void main(String args[]){
        if(args.length != 2){
            System.out.println("사용법 : java FileCopy 파일명1 파일명2");
            System.exit(0);
        }

        FileReader fr = null;
        FileWriter fw = null;

        try{
            fr = new FileReader(args[0]);
            fw = new FileWriter(args[1]);

            int readcount = 0;
            char[] buffer = new char[512];

            while((readcount = fr.read(buffer)) != -1){
                fw.write(buffer, 0, readcount);
            }
        }catch(IOException e){

        }finally {
            try{
                if(fr != null) fr.close();
                if(fw != null) fw.close();
            }catch(IOException e){

            }
        }
    }
}
