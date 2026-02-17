package streams;

import java.io.FileInputStream;
import java.io.IOException;

public class CustomFileInputReader {
    public static  void main(String[] args) {
        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\paths\\user.json";
       try (FileInputStream fileInputStream = new FileInputStream(fileLocation)) {
           byte[] data = fileInputStream.readAllBytes();
           System.out.println(new String(data));
       }catch (IOException e){
           e.printStackTrace();
       }
    }
}
