package chapter3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class DigestThread extends Thread{
    private String fileName;

    public DigestThread(String fileName){
        this.fileName = fileName;
    }

    public void run(){
        try{
            FileInputStream in = new FileInputStream(fileName);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while(din.read() != -1);
            din.close();
            byte[] digest = sha.digest();

            StringBuilder result = new StringBuilder(fileName);
            result.append(": ");
            result.append(toHexString(digest));
            System.out.println(result);

//            System.out.println(fileName);
//            System.out.println(": ");
//            System.out.println(toHexString(digest));


        } catch (IOException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public static String toHexString(byte[] bytes){
        StringBuilder hexString = new StringBuilder();

        for(int i = 0; i < bytes.length; i++){
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();

    }

    public static void main(String[] args){
        String[] fileNames = new String[]{"C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\chapter3\\test.txt", "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\chapter3\\test1.txt"};
        for(String fileName : fileNames){
            Thread t = new DigestThread(fileName);
            t.start();
        }
        // single thread
        for(String fileName : fileNames){
            try{
                FileInputStream in = new FileInputStream(fileName);
                MessageDigest sha = MessageDigest.getInstance("SHA-256");
                DigestInputStream din = new DigestInputStream(in, sha);
                while(din.read() != -1);
                din.close();
                byte[] digest = sha.digest();

                StringBuilder result = new StringBuilder(fileName);
                result.append(": ");
                result.append(toHexString(digest));
                System.out.println(result);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
}
