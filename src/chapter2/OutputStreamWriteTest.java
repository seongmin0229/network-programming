package chapter2;

public class OutputStreamWriteTest {
    public static void main(String[] args) {
        for(int i = 0; i < 300; i++){
            System.out.print("write : ");
            System.out.write(i);    // read a least significant byte
            System.out.print("   ");
            System.out.print("print : ");
            System.out.print(i);    // print ASCII of the byte value
            System.out.flush();
            System.out.println();
        }
    }
}