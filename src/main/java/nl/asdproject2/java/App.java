package nl.asdproject2.java;

public class App 
{
    public static void main( String[] args )
    {
        GreetingClient client = new GreetingClient("localhost", 6789);
        client.run();
    }
}
