package midterm;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable{
    private int start;
    private int end;

    public Task(int start, int end){
        this.start = start;
        this.end = end;
    }

    private void existFileName(int c, int d) {
        String[] possibleUrls = {
                "http://nas.hoony.me:9998/midtermPractice/file%20(c=" + c + ")_(d=" + d + ").txt",
                "http://nas.hoony.me:9998/midtermPractice/file%20<c=" + c + ">_(d=" + d + ").txt",
                "http://nas.hoony.me:9998/midtermPractice/file%20(c=" + c + ")_<d=" + d + ">.txt",
                "http://nas.hoony.me:9998/midtermPractice/file%20<c=" + c + ">_<d=" + d + ">.txt"
        };

        String[] possibleFileName = {
                "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Test\\file (c=%s)_(d=%s).txt",
                "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Test\\file <c=%s>_(d=%s).txt",
                "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Test\\file (c=%s)_<d=%s>.txt",
                "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Test\\file <c=%s>_<d=%s>.txt"
        };

        for (int i = 0; i < possibleUrls.length; i++) {
            try {
                URL u = new URL(possibleUrls[i]);
                downloadFile(u, possibleFileName[i].formatted(c, d));
                System.out.println(possibleFileName[i].formatted(c, d) + " downloaded");
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
    }

    public void downloadFile(URL url, String outputFileName) throws IOException
    {
        File file = new File(outputFileName);
        if(!file.exists()){
            file.createNewFile();
        }
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }
    @Override
    public void run() {
        for(int c = start; c <= end; c++) {
            for (int d = 1; d <= 50; d++) {
                existFileName(c, d);
            }
        }
    }
}
public class FileDownload {
    public static void downloadFile(URL url, String outputFileName) throws IOException
    {
        File file = new File(outputFileName);
        if(!file.exists()){
            file.createNewFile();
        }
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

    public final static int THREAD_NUM = 10;

    public static void result(){

    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM);
        for(int i = 0; i <= 9; i++){
            service.submit(new FileCountInterface(i * 5 + 1, i * 5 + 5));
        }
        service.shutdown();


//        // test
//        FileCountInterface fci = new FileCountInterface(1, 1);
//        Thread test = new Thread(fci);
    }
}
