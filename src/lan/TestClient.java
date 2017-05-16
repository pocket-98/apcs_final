// test communication between server and client

package lan;

import java.net.Socket;
import java.net.SocketException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import lan.TestServer;
import lan.LANUtils;

public class TestClient
{

	public static final int PORT = TestServer.PORT;

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	public TestClient(String host)
	{
		try
		{
			client = new Socket(host, PORT);
			InputStreamReader tmp =  new InputStreamReader(client.getInputStream());
			in = new BufferedReader(tmp);
			out = new PrintWriter(client.getOutputStream(), true);
		}
		catch (SocketException e)
		{
			System.out.println("Error: Host not up");
		}
		catch (IOException e)
		{
			System.out.println("Error: input output streams");
		}
	}

	public String getStuff()
	{
		return "asdf";
	}

}
