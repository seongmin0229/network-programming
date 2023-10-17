package chapter6;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
public class SourceViewerSimpleTest {
    public static void main(String args[]){
        InputStream in = null;
        try{
            URL u = new URL("");
            in = u.openStream();
            int c;
            while((c = in.read()) != -1) System.out.write(c);
        }catch(IOException e){
            System.err.println(e);
        }finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch(IOException e){

            }
        }
    }
}
