package streams;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;


public class CloudDataReader {
    public static void main(String[] args) {

        final String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\filelocation\\post.json";
        String url =  "https://jsonplaceholder.typicode.com/posts";
        String data = readDataFrom(url);

       try (FileWriter writer = new FileWriter(fileLocation);){
           writer.write(data);
       }catch (IOException ex){
           ex.printStackTrace();
       }
    }
    public static String readDataFrom(String cloudLocation){
        URI uri = URI.create(cloudLocation);
        try(InputStream inputStream = uri.toURL().openStream()){
            byte[] data = inputStream.readAllBytes();
            return new String(data);
        }catch(IOException error){
            error.printStackTrace();
            throw new RuntimeException(error);

        }

    }

}
