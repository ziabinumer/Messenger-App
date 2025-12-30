package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import auth.AuthService;

public class ServerMain {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Server starting on port " + PORT);
        AuthService authService = new AuthService();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                ClientHandler handler = new ClientHandler(clientSocket, authService);
                handler.start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
