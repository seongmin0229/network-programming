package midterm;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

class FileCountInterface implements Runnable{
    private String fileName;
    private Map map;
    private CountDownLatch latch;

    public FileCountInterface(String fileName, Map map, CountDownLatch latch){
        this.fileName = fileName;
        this.map = map;
        this.latch = latch;
    }
    @Override
    public void run() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.err.println("File not found: " + fileName);
            latch.countDown(); // 예외 처리 후 바로 countDown() 호출
            return; // 파일이 없으면 더 이상 실행하지 않음
        }
        FileReader fr = null;
        BufferedReader br = null;


        try{
            fr = new FileReader(fileName);
            System.out.println(fileName + " opened");
            br = new BufferedReader(fr);

            String line;

            while((line = br.readLine()) != null){
                String[] tokens = line.split(" "); // 공백을 기준으로 문자열을 분할합니다.
                for (String token : tokens) {
                    try {
                        int number = Integer.parseInt(token);
                        System.out.println(number);
                        synchronized (map){
                            map.put(number, (Integer)map.getOrDefault(number, 0) + 1);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread());
            System.out.println(map);
            latch.countDown();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException e){

                }
            }
            if(fr != null){
                try{
                    fr.close();
                }catch(IOException e){

                }
            }
        }
    }



}

public class MidTerm2022{
    public final static int THREAD_COUNT = 100;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        Map<Integer, Integer> numMap = new HashMap<>();
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        for(int i = 1; i <= 50; i++){
            for(int j = 1; j <= 50; j++){
                Runnable task = new FileCountInterface("C:\\Users\\LSM\\konkuk\\2023-2\\network-programming\\src\\Archive\\file (c="+i+")_(d="+j+").txt", numMap, latch);
                pool.submit(task);
            }
        }

        try{
            latch.await();
            System.out.println(numMap);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        pool.shutdown();
    }
}
