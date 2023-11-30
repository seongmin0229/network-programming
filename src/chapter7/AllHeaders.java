package chapter7;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;
public class AllHeaders {
    public static void main(String[] args){
        try{
            URL u = new URL("https://www.oreilly.com");
            URLConnection uc = u.openConnection();
            int j = 0;
            for(;;j++){
                String header = uc.getHeaderField(j);
                if(header == null) break;
                System.out.println(header);
            }
        }catch(MalformedURLException ex){

        }catch(IOException ex){

        }
    }
}
