// test communication between server and client

package lan;

import java.net.Socket;
import java.net.SocketException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import lan.TestServer;
import utils.LANUtils;
import utils.GameUtils;

public class TestClient
{

	public static final int PORT = TestServer.PORT;

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	public TestClient(String host)
	{
		client = null;
		in = null;
		out = null;
		try
		{
			if (!LANUtils.testHost(host, PORT))
			{
				throw new SocketException();
			}

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
			System.out.println("Error: Input and output streams not defined");
		}
	}

	public void close()
	{
		try
		{
			client.close();
		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't close connection");
		}
	}

	public void send(String msg)
	{
		out.write(msg);
	}

	public void receive()
	{
		try
		{
			System.out.println("Server: " + in.readLine());
		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't communicate with server");
		}
	}

	public static void main(String[] args)
	{
		TestClient c = new TestClient("localhost");
		GameUtils.sleep(1000);
		c.receive();
		c.send("asd");
		GameUtils.sleep(1000);
		c.receive();
		c.send("bbb");
		GameUtils.sleep(1000);
		c.close();
	}

}
