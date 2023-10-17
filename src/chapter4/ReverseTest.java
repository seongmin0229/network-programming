package chapter4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReverseTest {
    public static void main(String[] args){
        try{
            InetAddress ia = InetAddress.getByName("172.217.161.36");
            System.out.println(ia.getCanonicalHostName());
            System.out.println(ia.getHostName());
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
    }
}
