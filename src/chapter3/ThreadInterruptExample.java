package chapter3;

public class ThreadInterruptExample extends Thread{
    public void run(){
        try{
            Thread.sleep(2000);
            System.out.println("run() : after sleeping 2000ms");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        ThreadInterruptExample t1 = new ThreadInterruptExample();
        t1.start();
        try{
            Thread.sleep(1900);
            t1.interrupt();
        }catch (Exception e){
            System.out.println("Exeption handled : " + e);
        }
    }
}
