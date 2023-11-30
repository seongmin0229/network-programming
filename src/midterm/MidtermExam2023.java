package midterm;

import java.io.*;
import java.util.concurrent.*;
import java.net.*;

import static java.lang.Thread.sleep;

class Top{
    public int c;
    public int d;
    public int sum;
    public Top(){
        this.c = 0;
        this.d = 0;
        this.sum = 0;
    }
}
class Result{
    public int skipped;
    public Top[] top5;
    public Top[] low5;

    public Result(){
        this.top5 = new Top[5];
        this.low5 = new Top[5];
        this.skipped = 0;
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
        for(int i = 0; i < 5; i++){
            this.result.top5[i] = new Top();
            this.result.low5[i] = new Top();
            this.result.low5[i].sum = Integer.MAX_VALUE;
        }
    }

    private InputStream existFileName(int c, int d) throws UnsupportedEncodingException {
        String[] possibleUrls = {
                "http://203.252.148.148:12345/NP%202023/file%20" + URLEncoder.encode("(", "UTF-8") + "c=" + c + URLEncoder.encode(")", "UTF-8") + "_" + URLEncoder.encode("(", "UTF-8") + "d=" + d + URLEncoder.encode(")", "UTF-8") + ".txt",
                "http://203.252.148.148:12345/NP%202023/file%20" + URLEncoder.encode("@", "UTF-8") + "c=" + c + URLEncoder.encode("@", "UTF-8") + "_" + URLEncoder.encode("(", "UTF-8") + "d=" + d + URLEncoder.encode(")", "UTF-8") + ".txt",
                "http://203.252.148.148:12345/NP%202023/file%20" + URLEncoder.encode("(", "UTF-8") + "c=" + c + URLEncoder.encode(")", "UTF-8") + "_" + URLEncoder.encode("@", "UTF-8") + "d=" + d + URLEncoder.encode("@", "UTF-8") + ".txt",
                "http://203.252.148.148:12345/NP%202023/file%20" + URLEncoder.encode("@", "UTF-8") + "c=" + c + URLEncoder.encode("@", "UTF-8") + "_" + URLEncoder.encode("@", "UTF-8") + "d=" + d + URLEncoder.encode("@", "UTF-8") + ".txt"
        };

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
                    br = new BufferedReader(ir, 65536);
                    int sum = 0;
                    String line;

                    while ((line = br.readLine()) != null) {
                        String[] tokens = line.split(" "); // 공백을 기준으로 문자열을 분할합니다.
                        for (String token : tokens) {
                            try {
                                int number = Integer.parseInt(token);
//                                System.out.println(number);
//                                result.numArray[number]++;
                                sum+=number;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    for(Top top : result.top5){
                        if(top.sum < sum){
                            top.sum = sum;
                            top.c = c;
                            top.d = d;
                            break;
                        }
                    }
                    for(Top low : result.low5){
                        if(low.sum > sum){
                            low.sum = sum;
                            low.c = c;
                            low.d = d;
                            break;
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

public class MidtermExam2023{

    public final static int THREAD_NUM = 4;


    public static void bubleSort(Top[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0 ; j < arr.length - i - 1 ; j++) {
                if(arr[j].sum > arr[j+1].sum) {
                    Top temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        for(int a = 0; a < 5; a++){
            Top[] finalTop5 = new Top[50];
            Top[] finalLow5 = new Top[50];
            int skipped = 0;
            ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM);
            Future<Result> future[] = new Future[10];
            for(int i = 0; i <= 9; i++){
                future[i] = service.submit(new FileCountInterface(i * 5 + 1, i * 5 + 5));
            }
            try{
                for(int i = 0; i <= 9; i++){
                    Result result = future[i].get();
                    for(int j = 0; j < result.top5.length; j++){
                        finalTop5[i * 5 + j] = result.top5[j];
                        finalLow5[i * 5 + j] = result.low5[j];
                    }
                    skipped += result.skipped;
                }
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            bubleSort(finalTop5);
            bubleSort(finalLow5);
            System.out.println("Top5");
            for(int i = 49; i > 44; i--){
                System.out.println("c = " + finalTop5[i].c + ", d = " + finalTop5[i].d + ", sum = " + finalTop5[i].sum);
            }
            System.out.println();
            System.out.println("Bottom5");
            for(int i = 0; i < 5; i++){
                System.out.println("c = " + finalLow5[i].c + ", d = " + finalLow5[i].d + ", sum = " + finalLow5[i].sum);
            }
            System.out.println();
            System.out.println("missing : " + skipped);
            service.shutdown();
        }

        Long end = System.currentTimeMillis();
        System.out.print("average run-time : " + (end - start) / 1000 / 5);

//        // test
//        FileCountInterface fci = new FileCountInterface(1, 1);
//        Thread test = new Thread(fci);
    }
}