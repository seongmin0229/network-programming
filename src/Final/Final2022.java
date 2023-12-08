package Final;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class Final2022 {
    private final String HOST = "203.252.148.148";
    private final int PORT = 2022;
    private ByteBuffer contentBuffer;
    private String studentId;
    private ArrayList<Integer> intArray = new ArrayList<>();
    private ArrayList<Float> floatArray = new ArrayList<>();

    public Final2022(String studentId){
        this.studentId = studentId;
    }

    public void nonBlockingIO(){
        Selector selector = null;

        try {
            selector = Selector.open();
            SocketAddress address = new InetSocketAddress(HOST, PORT);
            SocketChannel clientChannel = SocketChannel.open(address);
            clientChannel.configureBlocking(false);

            clientChannel.register(selector, SelectionKey.OP_WRITE);

            this.contentBuffer = ByteBuffer.allocate(130);

            while(true){
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();

                     if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        for(char c: studentId.toCharArray()){
                            this.contentBuffer.putChar(c);
                        }
                        contentBuffer.flip();
                        channel.write(contentBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer receiveBuffer = ByteBuffer.allocate(9999);
                        channel.read(receiveBuffer);
                        System.out.println(receiveBuffer);
                        byte[] str1 = new byte[19];
                        byte[] str2 = new byte[31];
                        receiveBuffer.flip();
                        receiveBuffer.limit(19);
                        receiveBuffer.get(str1);
                        receiveBuffer.limit(130);
                        receiveBuffer.position(99);
                        receiveBuffer.get(str2);

                        String hw1 = new String(str1, "EUC-KR");
                        String hw2 = new String(str2, "EUC-KR");

                        byte[] tmp = new byte[80];
                        receiveBuffer.flip();

                        receiveBuffer.limit(99);
                        receiveBuffer.position(19);
//                        receiveBuffer.get(tmp);
//                        byte[] intFloatData = receiveBuffer.slice();


//                        System.out.println(msg);
                        channel.close();



                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 2; j++){
                                float floatData = receiveBuffer.getFloat();
                                floatArray.add(floatData);
//                                System.out.println("floatData : " + floatData);
                            }
                            for(int j = 0; j < 2; j++){
                                int intData = receiveBuffer.getInt();
                                intArray.add(intData);
//                                System.out.println("intData : " + intData);
                            }
                        }
                        System.out.println("한1 : " + hw1);
                        System.out.println("한2 : " + hw2);
                        System.out.println("max float data : " + Collections.max(floatArray));
                        System.out.println("max int data : " + Collections.max(intArray));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Final2022 final2022 = new Final2022("201911266");
        final2022.nonBlockingIO();
    }
}
