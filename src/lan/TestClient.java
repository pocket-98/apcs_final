// test communication between server and client

package lan;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
			if (LANUtils.testHost(host, PORT))
			{
				client = new Socket(host, PORT);
				InputStreamReader tmp =  new InputStreamReader(client.getInputStream());
				in = new BufferedReader(tmp);
				out = new PrintWriter(client.getOutputStream(), true);
			}
			else
			{
				System.out.println("WRONG");
			}
		}
		catch (Exception e)
		{
			System.out.println("WRONG");
		}
	}

	public String getStuff()
	{
		return "asdf";
	}

}
