package chapter11;

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.io.*;

public class ChargenClient {

	public static int DEFAULT_PORT = 19;

	public static void main(String[] args) {

//		if (args.length == 0) {
//			System.out.println("Usage: java ChargenClient host [port]"); 
//			return;
//		}  

		int port = DEFAULT_PORT;
		String host = "localhost";
//		try {
//			port = Integer.parseInt(args[1]);
//		} catch (RuntimeException ex) {
//			port = DEFAULT_PORT;   
//		}

		try {
			SocketAddress address = new InetSocketAddress(host, port);
			SocketChannel client = SocketChannel.open(address);

			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);
			
			File f = new File("file.txt");
			WritableByteChannel out2 = Channels.newChannel(new FileOutputStream(f));

			int i = 0;
			while (client.read(buffer) != -1) {
				i++;
				buffer.flip();
				out.write(buffer);
				buffer.flip();
				out2.write(buffer);
				buffer.flip();
				if (i>100) {
					break;
				}
			}     
		} catch (IOException ex) {
			ex.printStackTrace();   
		}
	}
}