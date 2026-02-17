import org.junit.jupiter.api.Test;
import paths.CustomFileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileWriterTest {

    @Test
    void testCanWriteToFile() throws IOException {
        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src";
        String fileName = "paths/user.json";
        Path filePath = Path.of(fileLocation, fileName);
        String json = """
                {
                "name" : "john",
                "age" : "30",
                "city" : "Atalanta"
                }
                """;

        CustomFileWriter.writeJasonToFile(filePath, json);
        String dataFromFile = Files.readString(filePath);
        assertEquals(json, dataFromFile);
    }

    @Test
    void testCanWriteFromYaml() throws IOException {
        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src";
        String fileName = "paths/user.yml";
        Path filePath = Path.of(fileLocation, fileName);
        String yml = """
                name : mike,
                age : 30,
                city : Los-Angeles
                """;

        CustomFileWriter.writeJasonToFile(filePath, yml);
        String dataFromFile = Files.readString(filePath);
        assertEquals(yml, dataFromFile);
    }

    @Test
    void testCanReadFromJson() throws IOException {

        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\user.json";
        Path filePath = Path.of(fileLocation);
        String json = """
                {
                "name" : "john",
                "age" : "30",
                "city" : "Atalanta"
                }
                """;
        String dataFromFile = CustomFileWriter.readJasonFromFile(filePath);
        assertEquals(json, dataFromFile);
    }

    @Test
    void testCanReadFromYaml() throws IOException {
        String fileLocation = "";

    }

  }

