package chapter3;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {
    public final static int THREAD_COUNT = 4;

    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        String[] fileNames = new String[]{"C:\\Users\\LSM\\konkuk\\2023-2\\network-programming\\src\\chapter3\\test.txt", "C:\\Users\\LSM\\konkuk\\2023-2\\network-programming\\src\\chapter3\\test1.txt"};
        for(String fileName : fileNames){
            File f = new File(fileName);
            if(f.exists()){
                if(f.isDirectory()){
                    File[] files = f.listFiles();
                    for(int i = 0; i < files.length; i++){
                        if(!files[i].isDirectory()){
                            Runnable task = new GZipRunnable(files[i]);
                            pool.submit(task);
                        }
                    }
                }else{
                    Runnable task = new GZipRunnable(f);
                    pool.submit(task);
                }
            }
        }
        pool.shutdown();
    }

}
