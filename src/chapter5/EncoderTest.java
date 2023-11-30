package chapter5;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncoderTest {
    public static void main(String args[]){
        try{
            System.out.println(URLEncoder.encode("this string has spaces", "UTF-8"));
        }catch(UnsupportedEncodingException ex){
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }
}
