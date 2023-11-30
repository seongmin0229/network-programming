package chapter9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerLee {
    static final int PORT = 7;
    public static void main(String args[]){
        try(ServerSocket server = new ServerSocket(PORT)){
            while(true){
                try(Socket connection = server.accept()){
                    System.out.println();
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                    String str;


                    while((str = br.readLine()) != null){
                        if(str.length() == 0){
                            break;
                        }
                        out.write(str + "\r\n");
                        out.flush();
                    }
                    str = br.readLine();
                    out.write(str + "\r\n");
                    out.flush();
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
