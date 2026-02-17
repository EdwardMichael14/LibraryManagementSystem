package streams;

import java.io.FileOutputStream;
import java.io.IOException;

public class CustomFileOutputWriter {
    public  static void main(String[] args) {

        String json = """
                {
                "name" : "John Doe",
                "Phone number" : "12345"
                }
                """;
        String filePath = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\paths\\user.json";
        try(FileOutputStream fileOutputStream = new FileOutputStream(filePath, true))// we added the boolean args so that the file written will not override the existing one.
        {
            fileOutputStream.write(json.getBytes());
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
