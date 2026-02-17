package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PostDeserializer {
    public static Post deserialize(Path path) {

        try(InputStream inputStream = Files.newInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            Object object = objectInputStream.readObject();
            return (Post) object;
            }catch (IOException | ClassNotFoundException ex){
                ex.printStackTrace();
        }
        return  null;
    }
}
