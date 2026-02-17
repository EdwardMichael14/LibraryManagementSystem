package paths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Paths {

    public static void main(String[] args) {

        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src";
        String fileName = "paths/user.json";

        Path path = Path.of(fileLocation, fileName);

//        System.out.println(path.getFileSystem());
        try {
            Files.createFile(path);
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
