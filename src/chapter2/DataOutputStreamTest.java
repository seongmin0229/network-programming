package chapter2;

import java.io.*;

public class DataOutputStreamTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        boolean addTab = false;

        fos = new FileOutputStream("C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\data.bin");
        dos = new DataOutputStream(fos);
        dos.writeBoolean(false);
        if(addTab) dos.writeChar('\n');
        dos.writeByte((byte)125);
        if(addTab) dos.writeChar('\n');
        dos.writeInt(10);
        if(addTab) dos.writeChar('\n');
        dos.writeDouble(200.5);
        if (addTab) dos.writeChar('\n');

        dos.writeUTF("hello world");

        System.out.println("저장하였습니다.");

        fos.close();
        dos.close();

        FileInputStream fis = null;
        DataInputStream dis = null;

        fis = new FileInputStream("data.bin");
        dis = new DataInputStream(fis);

        boolean boolVar = dis.readBoolean();
        if(addTab) dis.readChar();
        byte byteVar = dis.readByte();
        if(addTab) dis.readChar();
        int intVar = dis.readInt();
        if(addTab) dis.readChar();
        double doubleVar = dis.readDouble();
        if (addTab) dis.readChar();
        String stringVar = dis.readUTF();

        System.out.println(boolVar);
        System.out.println(byteVar);
        System.out.println(intVar);
        System.out.println(doubleVar);
        System.out.println(stringVar);

        fis.close();
        dos.close();
    }
}
