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

	public void run()
	{
		try
		{
			String clientIP = client.getInetAddress().getHostAddress();
			String msgIn;
			String msgOut;
			while (running)
			{
				msgOut = "serverserverserver";
				out.write(msgOut);
				out.flush();
				msgIn = in.readLine();
				if (msgIn != null)
					System.out.println("Client: " + msgIn);
				if (msgIn == "bbb")
				{
					running = false;
				}
			}

			System.out.println("Connection closed: " + clientIP);
			client.close();
		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't communication with client");
		}

		l.removeClient(id);
	}

	public interface Listener
	{
		public void removeClient(int id);
	}
}
