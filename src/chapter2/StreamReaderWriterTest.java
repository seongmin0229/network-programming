package chapter2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StreamReaderWriterTest {
    public static void main(String[] args){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try{
            fis = new FileInputStream("C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\chapter2\\test4.rtf");
            isr = new InputStreamReader(fis, "EUC-KR");
            osw = new OutputStreamWriter(System.out, "EUC-KR");

            char[] buffer = new char[512];
            int readcount = 0;
            while((readcount = isr.read(buffer)) != -1){
                osw.write(buffer, 0, readcount);
            }
            fis.close();
            isr.close();
            osw.close();
        }catch(IOException e){

        }
    }
}
