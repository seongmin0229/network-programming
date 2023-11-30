package chapter11;

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.io.IOException;

public class IntgenClient {

	public static int DEFAULT_PORT = 1919;

	public static void main(String[] args) {

		//	  System.out.println(SelectionKey.OP_ACCEPT);
		//	  System.out.println(SelectionKey.OP_WRITE);
		//	  System.out.println(SelectionKey.OP_READ);
		//	  System.out.println(SelectionKey.OP_CONNECT);
		//    if (args.length == 0) {
		//      System.out.println("Usage: java IntgenClient host [port]"); 
		//      return;
		//    }  

		int port = DEFAULT_PORT;
		//    try {
		//      port = Integer.parseInt(args[1]);
		//    } catch (RuntimeException ex) {
		//      port = DEFAULT_PORT;   
		//    }

		try {
			SocketAddress address = new InetSocketAddress("192.168.64.123", port);
			SocketChannel client  = SocketChannel.open(address);
			ByteBuffer    buffer  = ByteBuffer.allocate(4);
			IntBuffer     view    = buffer.asIntBuffer();

			for (int expected = 0; ; expected++) {
				client.read(buffer);
				//System.out.println("buffer: " + buffer);
				//System.out.println("view: " + view);
				int actual = view.get();
				buffer.clear();
				view.rewind();

				if (actual != expected) {
					System.err.println("Expected " + expected + "; received " + actual);
					break;
				}
				System.out.println(actual);
			}     
		} catch(IOException ex) {
			ex.printStackTrace();   
		}
	}
}