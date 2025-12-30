package server;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import auth.AuthService;

public class ClientHandler extends Thread {

    private static List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String username;
    private AuthService authService;

    public ClientHandler(Socket socket, AuthService authService) {
        this.clientSocket = socket;
        this.authService = authService;
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
            writer.println("Welcome! Please login or register.");
            while (true) {
                writer.println("Enter: login username password OR register username password");
                String command = reader.readLine();
                if (command == null) break;

                String[] parts = command.split(" ");
                if (parts.length < 3) continue;

                String action = parts[0];
                String user = parts[1];
                String pass = parts[2];

                if (action.equalsIgnoreCase("register")) {
                    if (authService.register(user, pass)) {
                        writer.println("Registration successful! Please login.");
                    } else {
                        writer.println("Username exists, try another.");
                    }
                } else if (action.equalsIgnoreCase("login")) {
                    if (authService.login(user, pass)) {
                        writer.println("Login successful! Welcome " + user);
                        this.username = user;
                        clients.add(this);
                        broadcast(username + " joined the chat");
                        break;
                    } else {
                        writer.println("Login failed. Try again.");
                    }
                }
            }

            // Listen and push messages
            String message;
            while ((message = reader.readLine()) != null) {
                broadcast(username + ": " + message);
            }

        } catch (IOException e) {
            System.out.println(username + " disconnected.");
        } finally {
            try { clientSocket.close(); } catch (IOException e) {}
            clients.remove(this);
            broadcast(username + " left the chat");
        }
    }

    // Push messages to all connected clients
    private void broadcast(String message) {
        for (ClientHandler c : clients) {
            c.writer.println(message);
        }
    }
}
