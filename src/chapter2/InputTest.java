package chapter2;

import java.io.IOException;

public class InputTest {
    public static void main(String[] args){
//        int inChar = 0;
//        int inChar2 = 0;
//        System.out.println("Enter a Character : ");
//        try{
//            int i = 0;
//            inChar = System.in.read();
////            System.out.print(inChar + " ");
//            System.out.write(inChar);
//            System.out.flush();
//        }catch (IOException e){
//
//        }




//        try{
//            int bytesRead = 0;
//            int bytesToRead = 1024;
//            byte[] input = new byte[bytesToRead];
//            while(bytesRead < bytesToRead){
//                int result = in.read(input, bytesRead, bytesToRead - bytesRead);
//                if(result == -1)
//                    break;
//                bytesRead += result;
//            }
//        }catch(IOException e){
//
//        }




//        InputStream in = System.in;
//        OutputStream out = System.out;
//
//        try{
//            int input = in.read();
//            System.out.println(input);
//            out.write(input);
//            out.flush();
//        }catch (IOException e){
//            e.printStackTrace();
//        }




        try{
            byte[] b = new byte[System.in.available()];
            System.in.read(b);
        }catch(IOException e){
            System.err.println("error");
        }





    }
}
