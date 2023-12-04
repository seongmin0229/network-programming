package chapter9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String args[]){
        String hostName = "192.168.65.138";
        int portNumber = 7;
        try(Socket echoSocket = new Socket(hostName, 80)){
            BufferedReader br = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(echoSocket.getOutputStream());


        }catch(IOException e){

        }
    }
}
