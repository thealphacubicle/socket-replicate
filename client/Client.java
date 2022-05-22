import java.net.*;
import java.io.*;

public class Client
{
    //initialize socket and input/output streams
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;

    // constructor
    public Client(String address, int port)
    {
        try
        {
            // create a socket to connect to the server
            socket = new Socket(address, port);
            System.out.println("Connected to " + address + " on port " + port);

            // initialize input (recieve from terminal) and output streams through socket
            din = new DataInputStream(System.in);
            dout = new DataOutputStream(socket.getOutputStream());
        }

        catch(UnknownHostException e)
        {
            System.out.println("Server not found: " + e.getMessage());
        }

        catch(IOException e)
        {
            System.out.println("I/O error: " + e.getMessage());
        }

        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        String line = "";
        
        // while the user does not type "exit"
        while(!line.equals("exit"))
        {
            try
            {
                // read a line from the terminal and then write it to the server
                line = din.readLine();
                dout.writeUTF(line);
            }

            catch(IOException e)
            {
                System.out.println("I/O error: " + e.getMessage());
            }
            finally{}
        }

        // close the socket
        try
        {
            socket.close();
            din.close();
            dout.close();
        }

        catch(IOException e)
        {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
        
}