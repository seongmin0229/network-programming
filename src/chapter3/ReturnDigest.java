package chapter3;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigest extends Thread{
    private String fileName;
    private byte[] digest;

    public ReturnDigest(String fileName){
        this.fileName = fileName;
    }

    public void run(){
        try{
            FileInputStream in = new FileInputStream(fileName);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while(din.read() != -1);
            din.close();
            digest = sha.digest();
        } catch (IOException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        }
    }

    public byte[] getDigest(){
        return digest;
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
        ReturnDigest[] digests = new ReturnDigest[fileNames.length];

        for(int i = 0; i < fileNames.length; i++){
            digests[i] = new ReturnDigest(fileNames[i]);
            digests[i].start();
        }

        for(int i = 0; i < fileNames.length; i++){
            StringBuffer result = new StringBuffer(fileNames[i]);
            result.append(": ");
            byte[] digest;
            while(digests[i].getDigest() == null){
                System.out.println("");
            };
            digest = digests[i].getDigest();
            result.append(toHexString(digest));

            System.out.println(result);
        }
    }

}
