package chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class DaytimeUDPClient {
//    private final static int PORT = 37;
    private final static int PORT = 13;
//    private static final String HOSTNAME = "time.nist.gov";

    private static final String HOSTNAME = "localhost";
    public static void main(String[] args){
        try(DatagramSocket socket = new DatagramSocket(0)){
            socket.setSoTimeout(10000);
            InetAddress host = InetAddress.getByName(HOSTNAME);
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, PORT);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.send(request);
            socket.receive(response);
            String result = new String(response.getData(), 0, response.getLength(), "US-ASCII");

//            long time = ByteBuffer.wrap(response.getData()).getLong();
//            System.out.println(time);
            System.out.println(result);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
