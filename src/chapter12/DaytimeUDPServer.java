package chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaytimeUDPServer {
    private final static int PORT = 13;
    private final static Logger audit = Logger.getLogger("request");
    private final static Logger errors = Logger.getLogger("errors");

    public static void main(String[] args){
        try(DatagramSocket socket = new DatagramSocket(PORT)){
            while(true){
                try{
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(request);

                    String daytime = new Date().toString();
                    byte[] data = daytime.getBytes("US-ASCII");
                    DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                    socket.send(response);
                    audit.info(daytime + " " + request.getAddress());
                }catch(IOException e){
                    errors.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        }catch (IOException e){
            errors.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
