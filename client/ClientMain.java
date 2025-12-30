package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // Thread to listen to server messages
            new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = reader.readLine()) != null) {
                        System.out.println(serverMsg);
                    }
                } catch (IOException e) { }
            }).start();

            // Send user input to server
            String input;
            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                writer.println(input);
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
