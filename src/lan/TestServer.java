// test communication between server and client

import java.net.ServerSocket;

public class TestServer
{

	public static final int PORT = 2222;

	private ServerSocket ss;

	public TestServer()
	{
		ss = new ServerSocket(PORT);
	}

}