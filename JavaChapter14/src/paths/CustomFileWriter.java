package paths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomFileWriter {

    public static void writeJasonToFile(Path path, String json) throws IOException {
        Files.writeString(path, json);

    }
    public static void writeYmlToFile(Path path, String yml) throws IOException {
        Files.writeString(path, yml);
    }

    public static String  readJasonFromFile(Path path) throws IOException {
        return Files.readString(path);
    }

    public static String  readYmlFromFile(Path path) throws IOException {
        return Files.readString(path);
    }
}
