package streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomPrintStreamer {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\paths\\user.json";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        System.setIn(fileInputStream);
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
