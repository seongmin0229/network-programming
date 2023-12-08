package midterm;

import com.sun.net.httpserver.HttpHandler;

import javax.print.DocFlavor;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://naver.com");
            URLConnection uc = url.openConnection();
            uc.setDoOutput(true);
            OutputStream out = uc.getOutputStream();
            OutputStream buffer = new BufferedOutputStream(out);
            OutputStreamWriter result = new OutputStreamWriter(buffer);

            result.write(" ");
//            uc.setConnectTimeout(10);
//            try(InputStream in = uc.getInputStream()){
//                System.out.println("connected");
//                uc.setConnectTimeout(10);
//            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
