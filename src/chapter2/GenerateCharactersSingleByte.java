package chapter2;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharactersSingleByte {
    public static void main(String[] args){
        try{
            generateCharacters(System.out);
        }catch(IOException e){

        }
    }

    private static void generateCharacters(OutputStream out) throws IOException{
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        int count = 0;

        while(count < 1000){
            for(int i = start; i < start + numberOfCharactersPerLine; i++){
                out.write((byte)((i - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter));
            }

            out.write((byte)'\r');  // carriage return
            out.write((byte)'\n');  // line feed

            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
            count++;

        }
    }
}
