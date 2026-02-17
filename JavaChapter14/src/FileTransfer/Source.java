package FileTransfer;


import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Source {
    public static void main(String[] args) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter server IP: ");
                String ip = scanner.nextLine();

                Socket socket = new Socket(ip, 5000);

                String filePath = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\paths\\user.json";
                FileInputStream input = new FileInputStream(filePath);

                OutputStream output = socket.getOutputStream();

                byte[] buffer = new byte[4096];
                int read;

                while ((read = input.read(buffer)) > 0) {
                    output.write(buffer, 0, read);
                }

                input.close();
                socket.close();

                System.out.println("File sent.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


