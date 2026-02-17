package serialization;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PostSerializerTest {

    @Test
    void serializeTest() {
        String content = "My first post";
        String id = "abcde";
        String author = "John Doe";

        Post post = new Post(content, id, author);
        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\serialization\\output";
        String fileName = "Post";

        Path path = Paths.get(fileLocation, fileName);
        PostSerializer.serializeWithPath(post, path);
    }

    @Test
    void testCanDeserialize() {
        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\serialization\\output";
        String fileName = "Post";
        Path path = Paths.get(fileLocation, fileName);
        Post post = PostDeserializer.deserialize(path);

        System.out.println(post);
        assertNotNull(post);
        assertEquals("My first post", post.getContent());
        assertEquals("abcde", post.getId());
        assertEquals("John Doe", post.getAuthor());


    }
}