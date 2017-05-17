// test communication between server and client

package lan;

import java.net.ServerSocket;
import java.net.Socket;

public class TestServer
{

	public static final int PORT = 2222;


	public TestServer()
	{
		try
		{
			ServerSocket server = new ServerSocket(PORT);
			Socket socket = server.accept();

			System.out.println("Connection Established");

		}
		catch (Exception e)
		{
			System.out.println("Error establishing connection." + e);
		}
	}

	public static void main(String[] args)
	{
		TestServer Server = new TestServer();
	}

}
