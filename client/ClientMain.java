package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import messaging.Message;
import messaging.MessageType;

public class ClientMain {

    private static String username;

    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 5000;

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            // Thread to read messages from server
            new Thread(() -> {
                try {
                    Message msg;
                    while ((msg = (Message) in.readObject()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException | ClassNotFoundException e) { }
            }).start();

            // Send user input to server
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                // First 3 commands are for login/register, send as String
                if (input.startsWith("login") || input.startsWith("register")) {
                    out.writeObject(input);
                    out.flush();
                    if (input.startsWith("login")) {
                        String[] parts = input.split(" ");
                        if (parts.length >= 3) username = parts[1];
                    }
                } else {
                    // Regular message
                    if (username == null) {
                        System.out.println("Please login first.");
                        continue;
                    }
                    Message msg = new Message(username, null, MessageType.TEXT, input);
                    out.writeObject(msg);
                    out.flush();
                }
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
