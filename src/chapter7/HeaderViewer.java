package chapter7;
import java.io.*;
import java.net.*;
import java.util.*;

public class HeaderViewer {

	public static void main(String[] args) {
		try {
//			URL u = new URL("http://www.oreilly.com");
//			URL u = new URL("http://www.lolcats.com/images/logo.png");
			URL u = new URL("http://www.konkuk.ac.kr");
			URLConnection uc = u.openConnection();
			System.out.println("Content-type: " + uc.getContentType());
			if (uc.getContentEncoding() != null) {
				System.out.println("Content-encoding: " 
						+ uc.getContentEncoding());
			}
			if (uc.getDate() != 0) {
				System.out.println("Date: " + new Date(uc.getDate()));
			}
			if (uc.getLastModified() != 0) {
				System.out.println("Last modified: " 
						+ new Date(uc.getLastModified()));
			}
			if (uc.getExpiration() != 0) {
				System.out.println("Expiration date: " 
						+ new Date(uc.getExpiration()));
			}
			if (uc.getContentLength() != -1) {
				System.out.println("Content-length: " + uc.getContentLength());
			}
		} catch (MalformedURLException ex) {
			System.err.println(args[0] + " is not a URL I understand");
		} catch (IOException ex) {
			System.err.println(ex);
		}      
		System.out.println(); 
	} 
}