package chapter7;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;

public class SourceViewer2 {
    public static void main(String[] args){
        try{
            URL u = new URL("http://www.naver.com");
            URLConnection uc = u.openConnection();
            try(InputStream raw = uc.getInputStream()){
                InputStream buffer = new BufferedInputStream(raw);
                Reader reader = new InputStreamReader(buffer);
                int c;
                while((c = reader.read()) != -1){
                    System.out.println(c);
                }
            }
        }catch(MalformedURLException ex){

        }catch(IOException ex){

        }
    }
}
