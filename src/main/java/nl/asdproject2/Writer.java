package nl.asdproject2;

import java.io.*;

public class Writer
{
    public static void write(String output)
    {
        System.out.println(output);
    }

    public static void writeUTF(OutputStream outToServer, String output) throws IOException
    {
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(output);
        out.flush();
    }

    public static void writeIn(InputStream inputStream) throws IOException
    {
        DataInputStream in = new DataInputStream(inputStream);
        System.out.println(in.readUTF());
    }
}
