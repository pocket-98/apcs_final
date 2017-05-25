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

	public Socket getClient()
	{
		return client;
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
		out.println(msg);
	}

	public String receive()
	{
		String msg = null;
		try
		{
			msg = in.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't communicate with server");
		}
		return msg;
	}

	public static void main(String[] args)
	{
		TestClient c = new TestClient("localhost");
		boolean running = true;
		String hostPort = LANUtils.getHostPort(c.getClient());
		String msg;
		while (running)
		{
			msg = c.receive();
			if (msg != null)
			{
				System.out.println(hostPort + " >> " + msg);
				switch (msg.toLowerCase().trim())
				{
					case "lol":
						c.send("bye");
						break;
					case "bye":
						running = false;
						c.close();
						break;
					default:
						break;
				}
			}
		}
	}

}
