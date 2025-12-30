package client;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
