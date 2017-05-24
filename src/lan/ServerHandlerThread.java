// Thread for handling stuff

package lan;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class ServerHandlerThread extends Thread
{

	private Socket client;
	private int id;
	private Listener l;
	private boolean running;
	private BufferedReader in;
	private PrintWriter out;

	public ServerHandlerThread(Socket c, int i, Listener ls)
	{
		super();
		client = c;
		id = i;
		l = ls;
		running = false;
		in = null;
		out = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
			running = true;
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

	public void kill()
	{
		try
		{
			client.close();
		}
		catch (Exception e)
		{

		}
	}

	public void run()
	{
		try
		{
			String msg;
			while (running)
			{
				out.write("lol");
				out.flush();
				msg = in.readLine();
				if (msg != null)
				{
					System.out.println("Client: " + msg);
				}
				if (msg.equals("bbb"))
				{
					out.write("gtfo");
					out.flush();
					running = false;
				}
			}

		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't communication with client");
		}
		finally
		{
			l.removeClient(id);
		}
	}

	public interface Listener
	{
		public void removeClient(int id);
	}
}
