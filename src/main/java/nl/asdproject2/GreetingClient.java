package nl.asdproject2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GreetingClient
{
    private String serverName;
    private int port;
    private Socket client;
    private String username;

    public GreetingClient(String serverName, int port)
    {
        this.serverName = serverName;
        this.port = port;

        try
        {
            this.username = getTheUsername();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
            Writer.write("Connecting to " + serverName + " on port " + port);
            client = new Socket(serverName, port);
            Writer.write("Just connected to " + client.getRemoteSocketAddress());

            new ClientInputThread(client.getInputStream()).start();

            startMessaging();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void startMessaging()
    {
        String message = "";
        while (!message.toLowerCase().equals("close"))
        {
            try
            {
                message = input();
                Writer.writeUTF(client.getOutputStream(), username + ": " + message);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        close();
    }

    private void close()
    {
        try
        {
            client.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String input() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    private String getTheUsername() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your username: ");
        return reader.readLine();
    }
}