package chapter8;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketInfo {
    public static void main(String args[]){
        for(String host : args){
            try{
                Socket theSocket = new Socket(host, 80);
                System.out.println("Connected to " + theSocket.getInetAddress()
                + " on port " + theSocket.getPort() + " from port "
                + theSocket.getLocalPort() + " of "
                + theSocket.getLocalAddress());
            }catch(UnknownHostException e){
                System.err.println(e);
            }catch(SocketException e){
                System.err.println(e);
            }catch(IOException e){
                System.err.println(e);
            }
        }
    }
}
