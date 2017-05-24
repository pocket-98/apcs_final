// test communication between server and client

package lan;

import java.net.Socket;
import java.net.SocketException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import lan.Server;
import utils.LANUtils;
import utils.GameUtils;

public class TestClient
{

	public static final int PORT = Server.PORT;

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

	public boolean isConnected()
	{
		return client.isConnected();
	}

	public String getLocalHost()
	{
		return client.getLocalAddress().getHostAddress();
	}

	public int getLocalPort()
	{
		return client.getLocalPort();
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
		out.flush();
	}

	public void receive()
	{
		try
		{
			String msg = in.readLine();
			if (msg != null)
			{
				System.out.println("Server: " + msg);
				if (msg.equals("lol"))
				{
					send("bye");
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't communicate with server");
		}
	}

	public static void main(String[] args)
	{
		TestClient c = new TestClient("10.86.10.115");
		String host = c.getLocalHost();
		int port = c.getLocalPort();
		System.out.println(host + ":" + port);
		while (c.isConnected())
		{
			c.receive();
		}
	}

}
