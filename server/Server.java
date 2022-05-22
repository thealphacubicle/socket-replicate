import java.io.*;
import java.net.*;

public class Server
{
    // initialize socket and input/output streams
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;

    //constructor
    public Server(int port)
    {
        try
        {
            // create a socket to connect to the server
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running...");

            // wait for connection from client
            socket = serverSocket.accept();
            System.out.println("Connected to " + socket.getInetAddress().getHostName());


            // initialize input (recieve from terminal) and output streams through socket
            din = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dout = new DataOutputStream(System.out);

            StringBuilder stringBuilder = new StringBuilder();

            //accept input while "over" is not entered
            while(!stringBuilder.toString().contains("over"))
            {
                stringBuilder.append(din.readUTF());
            }

            dout.writeUTF(stringBuilder.toString());
            System.out.println();
        }

        catch(IOException ioe)
        {
            System.out.println("I/O Exception" +ioe);
        }

        catch(Exception e)
        {
            System.out.println("Exception" +e);
        }

        finally
        {
            // close connection
            try
            {
                System.out.println("Closing connection...");
                socket.close();
                serverSocket.close();
                din.close();
                dout.close();
            }

            catch(IOException ioe)
            {
                System.out.println("I/O Exception" +ioe);
            }
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}