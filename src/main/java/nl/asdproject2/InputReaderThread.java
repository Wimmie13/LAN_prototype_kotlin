package nl.asdproject2;

import java.io.*;
import java.util.Observer;

public class InputReaderThread extends Thread implements Observer
{
    private InputStream serverIn;
    private OutputStream serverOut;

    public InputReaderThread(InputStream serverIn, OutputStream serverOut)
    {
        this.serverIn = serverIn;
        this.serverOut = serverOut;
    }


    @Override
    public void run() {
        while (true)
        {
            DataInputStream in = new DataInputStream(serverIn);
            try
            {
                String message = in.readUTF();
                System.out.println(message);
                GreetingServer.update(message);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg)
    {
        DataOutputStream out = new DataOutputStream(serverOut);
        try
        {
            out.writeUTF(arg.toString());
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
