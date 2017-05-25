// Thread for handling stuff

package lan;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import utils.LANUtils;

public class ServerHandlerThread extends Thread
{

	private Socket client;
	private int id;
	private Listener l;
	private boolean running;
	private BufferedReader in;
	private PrintWriter out;

	public ServerHandlerThread(Socket c, int i, Listener ls) throws IOException
	{
		super();
		client = c;
		id = i;
		l = ls;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
		running = true;
	}

	public Socket getClient()
	{
		return client;
	}

	public void kill() throws IOException
	{
		running = false;
		client.close();
	}

	public void run()
	{
		try
		{
			String hostPort = LANUtils.getHostPort(client);
			String msg;
			while (running)
			{
				out.println("lol");
				msg = in.readLine();
				if (msg != null)
				{
					System.out.println(hostPort + " >> " + msg);
					switch (msg.toLowerCase().trim())
					{
						case "bye":
							out.println("bye");
							running = false;
							break;
					}
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Error: couldn't connect to client");
		}
		if (!client.isClosed())
		{
			l.removeClient(id);
		}
	}

	public interface Listener
	{
		public void removeClient(int id);
	}
}
