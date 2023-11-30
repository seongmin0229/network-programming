package chapter11;

import java.nio.ByteBuffer;

public class DataConversionTest {
    public static void main(String[] args){
        ByteBuffer buf = ByteBuffer.allocate(16);
        int i = 0;
        while(buf.hasRemaining()){
            buf.put((byte)(i));
            i++;
        }
        buf.flip();

        System.out.println(buf);
        showBuffer(buf, "int");
        showBuffer(buf, "char");
        showBuffer(buf, "float");
        showBuffer(buf, "long");
    }
    public static void showBuffer(ByteBuffer buf, String type){
        if(type.equals("int")){
            while(buf.hasRemaining()){
                System.out.println(buf.getInt());
            }
            buf.flip();
        }else if(type.equals("char")){
            while(buf.hasRemaining()){
                System.out.println(buf.getChar());
            }
            buf.flip();
        }else if(type.equals("float")){
            while(buf.hasRemaining()){
                System.out.println(buf.getFloat());
            }
            buf.flip();
        }else if(type.equals("long")){
            while(buf.hasRemaining()){
                System.out.println(buf.getLong());
            }
            buf.flip();
        }
    }
}
