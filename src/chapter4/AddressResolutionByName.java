package chapter4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressResolutionByName {
    public static void main(String[] args){
        try{
            InetAddress address = InetAddress.getByName("www.konkuk.ac.kr");
            System.out.println(address);

            InetAddress address2 = InetAddress.getByName("202.30.38.108");
            System.out.println(address2);
            System.out.println(address2.getHostName());

            InetAddress[] addresses = InetAddress.getAllByName("www.daum.net");

            for(InetAddress address3 : addresses){
                System.out.println(address3);
            }
        } catch(UnknownHostException e){
            e.printStackTrace();
        }
    }
}
