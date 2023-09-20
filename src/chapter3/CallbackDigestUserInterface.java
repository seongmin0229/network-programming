package chapter3;

public class CallbackDigestUserInterface {
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

    public static void receiveDigest(byte[] digest, String name){
        StringBuilder result = new StringBuilder(name);
        result.append(": ");
        result.append(toHexString(digest));
        System.out.println(result.toString());
    }

    public static void main(String[] args) throws InterruptedException{
        String[] fileNames = new String[]{"C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\chapter3\\test.txt", "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\chapter3\\test1.txt"};
        for(String fileName : fileNames){
            CallbackDigest cb = new CallbackDigest(fileName);
            Thread t = new Thread(cb);
            t.start();
        }
    }

}
