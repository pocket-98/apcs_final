// test communication between server and client

package lan;

import java.net.ServerSocket;

public class TestServer
{

	public static final int PORT = 2222;

	private ServerSocket server;

	public TestServer()
	{
		try
		{
			server = new ServerSocket(PORT);
		}
		catch (Exception e)
		{
			System.out.println("WRONG");
		}
	}

}
