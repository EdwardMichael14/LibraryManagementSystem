package streams;

import java.io.DataInputStream;
import java.io.IOException;

public class CustomDataInputReader {
    public static void main(String[] args) {
        System.out.println("Enter your name: ");
        try(DataInputStream dataInputStream = new DataInputStream(System.in);) {
            byte[] data = dataInputStream.readAllBytes();
            System.out.println("Your name is " + " " + new String(data));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
