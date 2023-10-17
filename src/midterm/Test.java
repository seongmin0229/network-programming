package midterm;

import javax.print.DocFlavor;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://nas.hoony.me:9998/midtermPractice/file%20(c=" + 1 + ")_(d=" + 26 + ").txt");
            InputStream in = url.openStream();
            InputStreamReader ir = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(ir);
            String line = "";
            while((line = br.readLine()) != null){
                String[] tokens = line.split(" ");
                for(String token : tokens){
                    System.out.println(token);
                }
            }
        }catch(MalformedURLException e){

        }catch(IOException e){

        }
    }
}
