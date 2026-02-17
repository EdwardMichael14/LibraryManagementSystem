package streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomDataReader {
    public static void main (String[] args) throws FileNotFoundException {

        String path = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\paths\\user.json";
        var  fileInputStream = new FileInputStream(path);
        Scanner scanner =new Scanner(fileInputStream);
        StringBuilder builder = new StringBuilder();
        while(scanner.hasNextLine()) {
            builder.append(scanner.nextLine()).append("\n");
        }
        System.out.println(builder);
        }
    }

