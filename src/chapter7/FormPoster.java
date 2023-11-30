package chapter7;

import chapter5.QueryString;

import java.io.*;
import java.net.*;

public class FormPoster {

	private URL url;
	// from Chapter 5, Example 5-8
	private QueryString query = new QueryString();

	public FormPoster (URL url) {
		if (!url.getProtocol().toLowerCase().startsWith("http")) {
			throw new IllegalArgumentException(
					"Posting only works for http URLs");   
		}    
		this.url = url;
	}

	public void add(String name, String value) {
		query.add(name, value);    
	}

	public URL getURL() {
		return this.url; 
	}

	public InputStream post() throws IOException {

		// open the connection and prepare it to POST
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("Connection", "keep-alive");
		uc.setDoOutput(true);
		//    System.out.println(uc.getURL());
		try (OutputStreamWriter out 
				= new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {
			//		try {
			//			OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");

			// The POST line, the Content-type header,
			// and the Content-length headers are sent by the URLConnection.
			// We just need to send the data
			out.write(query.toString());
			System.out.println(query.toString());
			out.write("\r\n");
			out.flush();
			System.out.println(query);
		} catch (IOException ex) {

		}

		// Return the response
		return uc.getInputStream();
	}

	public static void main(String[] args) {
		URL url;
		if (args.length > 0) {
			try {
				url = new URL(args[0]);
			} catch (MalformedURLException ex) {
				System.err.println("Usage: java FormPoster url");
				return;
			}
		} else {
			try {
				url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
				//          url = new URL("http://www.konkuk.ac.kr");
			} catch (MalformedURLException ex) { // shouldn't happen
				System.err.println(ex);
				return;
			}
		}

		FormPoster poster = new FormPoster(url);
		poster.add("name", "Elliotte Rusty Harold");
		poster.add("email", "elharo@ibiblio.org");
		poster.add("want to", "go home");

		//		InputStream in = null;

		try (InputStream in = poster.post()) {
			//		try {
			//			in = poster.post();
			// Read the response
			Reader r = new InputStreamReader(in);
			int c;
			while((c = r.read()) != -1) {
				System.out.print((char) c);
			}
			System.out.println();
		} catch (IOException ex) {
			System.err.println(ex);   
		}
	}
}