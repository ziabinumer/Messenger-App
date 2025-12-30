package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error setting up streams: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            writer.println("online");
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                writer.println("server: " + message);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) { }
        }
    }
}
