package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteFile {

    public static void main(String[] args) {
        try{
            String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\filelocation\\post.json";
            Path path = Path.of(fileLocation);
            Files.deleteIfExists(path);

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
