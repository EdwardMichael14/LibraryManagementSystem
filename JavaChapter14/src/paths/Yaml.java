package paths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Yaml {

    public static void main(String[] args) {

        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src";
        String ymlName = "paths/user.yml";

        Path path = Path.of(fileLocation, ymlName);

//        System.out.println(path.getFileSystem());
        try {
            Files.createFile(path);
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
