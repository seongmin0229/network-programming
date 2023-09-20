package chapter2;

import java.io.*;

public class FileStreamCopy {
    public static void main(String args[]){
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try{
            fis = new FileInputStream("C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\test1.txt");
            fos = new FileOutputStream("C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\test2.txt");

            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int readcount = 0;
            byte[] buffer = new byte[1];

            // no buffered stream
            while((fis.read(buffer)) != -1){
                fos.write(buffer);
            }

//            // buffered stream
//            while((readcount = bis.read()) != -1){
//                bos.write(readcount);
//            }
//            bos.flush();

            System.out.println("복사가 완료되었습니다.");
        }catch(IOException e){
            System.out.println(e);
        }finally {
            if(fis != null){
                try{
                    fis.close();
                }catch(IOException e){

                }
            }
            if(fos != null){
                try{
                    fos.close();
                }catch(IOException e){

                }
            }
        }
    }
}
