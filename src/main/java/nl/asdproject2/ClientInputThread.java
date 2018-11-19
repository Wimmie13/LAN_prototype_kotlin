package nl.asdproject2;

import com.sun.xml.internal.ws.client.ClientTransportException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClientInputThread extends Thread
{
    private InputStream inFromServer;

    public ClientInputThread(InputStream inFromServer)
    {
        this.inFromServer = inFromServer;
    }

    public void run()
    {
        while (true)
        {
            DataInputStream in = new DataInputStream(inFromServer);
            try
            {
                System.out.println(in.readUTF());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
