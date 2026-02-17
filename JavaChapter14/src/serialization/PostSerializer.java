package serialization;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PostSerializer {
    public static void serialize(Post post) {

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(System.out);){
            objectOutputStream.writeObject(post);
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public static void serializeWithPath(Post post, Path path) {
        try(OutputStream outputStream = Files.newOutputStream(path);
            ObjectOutputStream objectOutputStream =new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(post);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
