package midterm;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.net.*;

import static java.lang.Thread.sleep;

class Result{
    public int skipped;
    public int[] numArray;

    public Result(){
        this.skipped = 0;
        this.numArray = new int[2000];
    }
}
class FileCountInterface implements Callable<Result> {
    private int start;
    private int end;
    private Result result;

    public FileCountInterface(int start, int end){
        this.start = start;
        this.end = end;
        this.result = new Result();
    }

    private InputStream existFileName(int c, int d) throws UnsupportedEncodingException {
        String[] possibleUrls = {
                "http://nas.hoony.me:9998/midtermPractice/file%20(c=" + c + ")_(d=" + d + ").txt",
                "http://nas.hoony.me:9998/midtermPractice/file%20" + URLEncoder.encode("<", "UTF-8") + "c=" + c  + URLEncoder.encode(">", "UTF-8") +  "_(d=" + d + ").txt",
                "http://nas.hoony.me:9998/midtermPractice/file%20(c=" + c + ")_" + URLEncoder.encode("<", "UTF-8") + "d=" + d + URLEncoder.encode(">", "UTF-8") +  ".txt",
                "http://nas.hoony.me:9998/midtermPractice/file%20" + URLEncoder.encode("<", "UTF-8") + "c=" + c + URLEncoder.encode(">", "UTF-8") + "_" + URLEncoder.encode("<", "UTF-8") + "d=" + d + URLEncoder.encode(">", "UTF-8") + ".txt"
        };
//        String[] possibleUrls = {
//                "http://nas.hoony.me:9998/midtermPractice/file%20(c=" + c + ")_(d=" + d + ").txt",
//                "http://nas.hoony.me:9998/midtermPractice/file%20<c=" + c + ">_(d=" + d + ").txt",
//                "http://nas.hoony.me:9998/midtermPractice/file%20(c=" + c + ")_<d=" + d + ">.txt",
//                "http://nas.hoony.me:9998/midtermPractice/file%20<c=" + c + ">_<d=" + d + ">.txt"
//        };

        for (String url : possibleUrls) {
            try {
                URL u = new URL(url);
                InputStream in = u.openStream();
                return in;
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }
        return null;
    }

    @Override
    public Result call() throws Exception {
        InputStream in = null;
        InputStreamReader ir = null;
        BufferedReader br = null;
        for(int c = start; c <= end; c++) {
            for (int d = 1; d <= 50; d++) {
                in = existFileName(c, d);
                if (in == null) {
                    result.skipped++;
                    continue;
                }


                try {
                    ir = new InputStreamReader(in);
                    br = new BufferedReader(ir);

                    String line;

                    while ((line = br.readLine()) != null) {
                        String[] tokens = line.split(" "); // 공백을 기준으로 문자열을 분할합니다.
                        for (String token : tokens) {
                            try {
                                int number = Integer.parseInt(token);
//                                System.out.println(number);
                                result.numArray[number]++;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {

                        }
                    }
                    if (ir != null) {
                        try {
                            ir.close();
                        } catch (IOException e) {

                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {

                        }
                    }
                }
            }
        }
        return this.result;
    }
}

public class MidTerm2022{

    public final static int THREAD_NUM = 10;

    public static void result(){

    }
    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        for(int a = 0; a < 1; a++){
            int[] numArray = new int[2000];
            int maxValue = Integer.MIN_VALUE, minValue = Integer.MIN_VALUE, max_freq_num = 0, max_freq = Integer.MIN_VALUE,
                    min_freq_num = 0, min_freq = Integer.MAX_VALUE;
            int skipped = 0;
            ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM);
            Future<Result> future[] = new Future[10];
            for(int i = 0; i <= 9; i++){
                future[i] = service.submit(new FileCountInterface(i * 5 + 1, i * 5 + 5));
            }
            try{
                for(int i = 0; i <= 9; i++){
                    Result result = future[i].get();
                    for(int j = 1; j <= 1999; j++){
                        numArray[j] += result.numArray[j];
                    }
                    skipped += result.skipped;
                }
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            for(int i = 1; i < 2000; i++){
                if(numArray[i] != 0){
                    minValue = i;
                    break;
                }
            }
            for(int i = 1; i < 2000; i++){
                if(numArray[i] != 0){
                    maxValue = i;
                }
                if(numArray[i] != 0 && numArray[i] > max_freq){
                    max_freq_num = i;
                    max_freq = numArray[i];
                }
                if(numArray[i] != 0 && numArray[i] < min_freq){
                    min_freq_num = i;
                    min_freq = numArray[i];
                }
            }
            System.out.println("max_freq_num : " + max_freq_num + " max_freq : " + max_freq);
            System.out.println("min_freq_num : " + min_freq_num + " min_freq : " + min_freq);
            System.out.println("Min : " + minValue + " Max : " + maxValue);
            System.out.println("skipped : " + skipped);
            service.shutdown();

        }

        Long end = System.currentTimeMillis();
        System.out.print("average run-time : " + (end - start) / 1000);

//        // test
//        FileCountInterface fci = new FileCountInterface(1, 1);
//        Thread test = new Thread(fci);
    }
}