package chapter2;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharacterByteArray {
    public static void main(String[] args){
        try{
            generateCharacters(System.out);
        }catch(IOException e){

        }
    }

    private static void generateCharacters(OutputStream out) throws IOException{
//        BufferedOutputStream bos = new BufferedOutputStream(out);
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        int count = 0;
        byte[] data = new byte[numberOfCharactersPerLine];

        while(count < 1000){
            int temp = 0;
            for(int i = start; i < start + numberOfCharactersPerLine; i++){
               data[temp] = (byte)((i - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter);
//                bos.write((byte)((i - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter));
                temp++;
            }
            out.write(data);

            out.write((byte)'\r');  // carriage return
            out.write((byte)'\n');  // line feed
//            bos.write((byte)'\r');  // carriage return
//            bos.write((byte)'\n');  // line feed
//
//            bos.flush();

            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
            count++;

        }
    }
}
