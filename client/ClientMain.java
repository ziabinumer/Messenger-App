package client;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
