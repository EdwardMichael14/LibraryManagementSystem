package serialization;

import java.io.Serializable;

public class Post implements Serializable {

    private String content;
    private String id;
    private String author;

    public Post(String content, String id, String author) {
        this.content = content;
        this.id = id;
        this.author = author;
    }

    public String getContent() {
        return content;
    }


    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }
}
