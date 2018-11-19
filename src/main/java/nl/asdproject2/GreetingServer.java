package nl.asdproject2;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GreetingServer extends Observable{
    private ServerSocket serverSocket;
    private static List<Observer> clients;
    private static GreetingServer greetingServer;

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
        greetingServer = this;
        run();
    }

    public void run() {
        while(true) {
            try {
                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());

                InputReaderThread client = new InputReaderThread(server.getInputStream(), server.getOutputStream());
                clients.add(client);
                client.start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args) {
        int port = 6789;
        try {
            new GreetingServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(String message)
    {
        for (Observer client : clients)
        {
            client.update(greetingServer, message);
        }
    }
}