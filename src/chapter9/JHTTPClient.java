package chapter9;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JHTTPClient {
    public static void main(String[] args){
        String host = "localhost";
        int port = 80;
        try{
            URL url = new URL("http://localhost:80");
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            uc.setRequestMethod("DELETE");
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e){

        }

    }
}
