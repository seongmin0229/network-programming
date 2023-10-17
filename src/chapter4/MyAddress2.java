package chapter4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress2 {
    public static void main(String[] args){
        try{
            InetAddress ia = InetAddress.getLocalHost();
            String dottedQuad = ia.getHostAddress();
            System.out.println("My Address is " + dottedQuad);
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
    }
}
