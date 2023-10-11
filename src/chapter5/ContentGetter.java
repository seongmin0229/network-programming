package chapter5;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentGetter {
    public static void main(String[] args){
        try{
            URL u = new URL("https://www.oreilly.com");
//            URL u = new URL("https://home.konkuk.ac.kr/~leehw/Site/nptest/files/logo.png");
            Object o = u.getContent();
            System.out.println(o.getClass().getName());
            int c;
            InputStream r = (InputStream) o;
            while((c = r.read()) != -1) System.out.print((char) c);
            r.close();
            System.out.println("I got a " + o.getClass().getName());
        }catch(MalformedURLException e){
            System.err.println("not a possible URL");
        }catch(IOException e){
            System.err.println(e);
        }
    }
}
