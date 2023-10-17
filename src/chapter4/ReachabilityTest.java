package chapter4;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReachabilityTest {
    public static void main(String[] args){
        try{
            byte[] addr = {(byte)202, 30, 38, 108};
            InetAddress address = InetAddress.getByName("");
            int timeout = 3000;
            int ttl = 10;

            if(address.isReachable(timeout)){
                System.out.println(address.getHostName() + " CAN BE reached within " + timeout);
            }else{
                System.out.println(address.getHostName() + " CANNOT BE reached within " + timeout);
            }
        }catch(UnknownHostException ex){
            System.out.println("Unknown hostname");
        }catch (IOException e){

        }
    }
}
