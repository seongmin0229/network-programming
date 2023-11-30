package chapter7;

import java.io.*;
import java.net.URLConnection;
import java.net.URL;
import java.util.Date;

public class Last24 {
    public static void main(String[] args){
        Date today = new Date();
        long millisecondsPerDay = 24 * 60 * 60 * 1000;

        try{
            URL u = new URL("https://ecampus.konkuk.ac.kr");
            URLConnection uc = u.openConnection();
//            uc.setRequestProperty("aaa", "bbb");
//            System.out.println(uc.getRequestProperty("aaa"));
            System.out.println("Original if modified since: "
                + new Date(uc.getIfModifiedSince()));
            uc.setIfModifiedSince((new Date(today.getTime()
                - millisecondsPerDay)).getTime());
            System.out.println("Will retrieve file if it's modified since"
                + new Date(uc.getIfModifiedSince()));
            try(InputStream in = new BufferedInputStream(uc.getInputStream())){
                Reader r = new InputStreamReader(in);
                int c;
                while((c = r.read()) != -1){
                    System.out.print((char)c);
                }
                System.out.println();
            }
        }catch (IOException ex){

        }
    }
}