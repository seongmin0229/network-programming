package midterm;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

class Data{

    Data(){
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        max_row = 0;
        max_col = 0;
        min_row = 0;
        min_col = 0;
    }
    public int max;
    public int min;
    public int max_row;
    public int max_col;
    public int min_row;
    public int min_col;
    public int max_c;
    public int max_d;
    public int min_c;
    public int min_d;
}

class Worker implements Callable<Data>{
    private Data result;
    private int start;
    private int end;

    private String baseURL;

    Worker(int start, int end){
        this.start = start;
        this.end = end;
        result = new Data();
    }

    private String existFileName(int c, int d) {
        String filePath = "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Archive\\file (c=%s)_(d=%s).txt".formatted(c, d);
        File file = new File(filePath);
        if(file.exists()){
            return filePath;
        }
        filePath = "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Archive\\file (c=%s)__d=%s_.txt".formatted(c, d);
        file = new File(filePath);
        if(file.exists()){
            return filePath;
        }
        filePath = "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Archive\\file _c=%s__(d=%s).txt".formatted(c, d);
        file = new File(filePath);
        if(file.exists()){
            return filePath;
        }
        filePath = "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Archive\\file _c=%s___d=%s_.txt".formatted(c, d);
        file = new File(filePath);
        if(file.exists()){
            return filePath;
        }
        return null;
    }

    @Override
    public Data call() throws Exception {
        FileReader fr = null;
        BufferedReader br = null;
        int row;
        int col;
        for(int c = start; c <= end; c++){
            for(int d = 1; d <= 50; d++){
                try{
                    baseURL = existFileName(c, d);
                    if(baseURL == null){
                        continue;
                    }
                    fr = new FileReader(baseURL);
                    br = new BufferedReader(fr);
                    String line = "";
                    row = 1;
                    while((line = br.readLine()) != null){
                        String[] tokens = line.split(" ");
                        col = 1;
                        for(String token : tokens){
                            int num = Integer.parseInt(token);
                            if(this.result.max < num){
                                this.result.max = num;
                                this.result.max_row = row;
                                this.result.max_col = col;
                                this.result.max_c = c;
                                this.result.max_d = d;
                            }
                            if(this.result.min > num){
                                this.result.min = num;
                                this.result.min_row = row;
                                this.result.min_col = col;
                                this.result.min_c = c;
                                this.result.min_d = d;
                            }
                            col++;
                        }
                        row++;
                    }
                }catch(IOException e){

                }

            }
        }
//        for(int c = start; c <= end; c++){
//            for(int d = 1; d <= 50; d++){
//                try{
//                    baseURL = existFileName(c, d);
//                    if(baseURL == null){
//                        continue;
//                    }
//                    fr = new FileReader(baseURL);
//                    br = new BufferedReader(fr);
//                    String line = "";
//                    row = 1;
//                    while((line = br.readLine()) != null){
//                        String[] tokens = line.split(" ");
//                        col = 1;
//                        for(String token : tokens){
//                            int num = Integer.parseInt(token);
//                            if(1997 == num){
//                                System.out.println(num + "is in row : " + row + " col : " + col + " in file " + c + ", " + d);
//                            }
//                            col++;
//                        }
//                        row++;
//                    }
//                }catch(IOException e){
//
//                }
//
//            }
//        }
        return result;
    }
}
public class MidTerm2021{
    final public static int THREAD_NUM = 10;

    public static void main(String[] args) throws InterruptedException {
        Data totalResult = new Data();
        Long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM);
        Future<Data> future[] = new Future[10];
        try{
            for(int i = 0; i <= 9; i++){
                future[i] = service.submit(new Worker(i * 5 + 1, i * 5 + 5));
                Data result = future[i].get();
                if(totalResult.max < result.max){
                    totalResult.max = result.max;
                    totalResult.max_row = result.max_row;
                    totalResult.max_col = result.max_col;
                    totalResult.max_c = result.max_c;
                    totalResult.max_d = result.max_d;
                }
                if(totalResult.min > result.min){
                    totalResult.min = result.min;
                    totalResult.min_row = result.min_row;
                    totalResult.min_col = result.min_col;
                    totalResult.min_c = result.min_c;
                    totalResult.min_d = result.min_d;
                }
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(totalResult);
        service.shutdown();
    }

}


