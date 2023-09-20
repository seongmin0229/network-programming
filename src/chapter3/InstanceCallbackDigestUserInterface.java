package chapter3;

public class InstanceCallbackDigestUserInterface {
    private String fileName;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String fileName){
        this.fileName = fileName;
    }

    public void calculateDigest(){
        InstanceCallbackDigest cb = new InstanceCallbackDigest(fileName, this);
        Thread t = new Thread(cb);
        t.start();
    }

    void receiveDigest(byte[] digest){
        this.digest = digest;
        System.out.println(this);
    }

    @Override
    public String toString(){
        String result = fileName + ": ";
        if(digest != null){
            result += toHexString(digest);
        }else{
            result += "digest not available";
        }
        return result;
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
            InstanceCallbackDigestUserInterface d =  new InstanceCallbackDigestUserInterface(fileName);
            d.calculateDigest();
        }
    }
}
