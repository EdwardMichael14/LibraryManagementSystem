package FileTransfer;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Destination {
    public static void main(String[] args) {
            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                System.out.println("Waiting for connection...");

                Socket socket = serverSocket.accept();
                System.out.println("Connected!");

                InputStream input = socket.getInputStream();


                String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Regex\\src\\FileTransfer\\recieveFile";
                FileOutputStream output = new FileOutputStream(fileLocation);

                byte[] buffer = new byte[4096];
                int read;

//                output.write(input.read(buffer));

                while ((read = input.read(buffer)) > 0) {
                    output.write(buffer, 0, read);
                }

                output.close();
                socket.close();
                serverSocket.close();

                System.out.println("File received.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


