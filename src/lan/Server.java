// Server

package lan;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import utils.LANUtils;
import utils.GameUtils;
import lan.ServerHandlerThread;

import java.util.Scanner;

public class Server extends ServerSocket implements ServerHandlerThread.Listener
{

	public static final int PORT = 2222;
	public static final int MAX_CLIENTS = 5;

	private boolean listening;
	private Thread serverThread;
	private ServerHandlerThread[] clientThreads;
	private boolean[] clientsConnected;

	public Server() throws IOException
	{
		super(PORT);
		if (!isBound())
		{
			System.out.println("Error: couldn't bind server to port " + PORT);
			throw new IOException();
		}
		System.out.println("Server Listening on following addresses:");
		System.out.println(LANUtils.getIPs());
		System.out.println();
		clientThreads = new ServerHandlerThread[MAX_CLIENTS];
		clientsConnected = new boolean[MAX_CLIENTS];
	}

	public void handle(Socket client, int id)
	{
		try
		{
			clientThreads[id] = new ServerHandlerThread(client, id, this);
			clientsConnected[id] = true;
			clientThreads[id].start();
		}
		catch (IOException e)
		{
			System.out.println("Error: Handler thread not started");
		}
	}

	public void start()
	{
		listening = true;
		serverThread = new Thread()
		{
			public void run()
			{
				try
				{
					while (listening)
					{
						Socket client = accept();
						for (int i = 0; i < MAX_CLIENTS; i++)
						{
							if (!clientsConnected[i])
							{
								String host = client.getInetAddress().getHostAddress();
								int port = client.getPort();
								System.out.println("Connection established: " + host + ":" + port);
								handle(client, i);
								break;
							}
						}
						sleep(1000);
					}
				}
				catch (IOException e)
				{

				}
				catch (InterruptedException e)
				{

				}
			}

		};
		serverThread.start();
	}

	public void removeClient(int id)
	{
		try
		{
			Socket client = clientThreads[id].getClient();
			String host = client.getInetAddress().getHostAddress();
			int port = client.getPort();
			String con =  " (" + client.isConnected() + ")";
			clientThreads[id].kill();
			clientsConnected[id] = false;
			System.out.println("Connection closed: " + host + ":" + port + con);
		}
		catch(Exception e)
		{
			System.out.println("Error: client can't be disconnected");
		}
	}

	public void kill()
	{
		if (listening)
		{
			listening = false;
			try
			{
				close();
				for (int i = 0; i < MAX_CLIENTS; i++)
				{
					if (clientsConnected[i])
					{
						removeClient(i);
					}
				}
			}
			catch (Exception e)
			{

			}
			System.out.println("Server Stopped");
		}
	}

	public static void main(String[] args)
	{
		try
		{
			Server server = new Server();
			server.start();
			Scanner s = new Scanner(System.in);
			s.nextLine();
			server.kill();
		}
		catch (Exception e)
		{

		}
	}

}
