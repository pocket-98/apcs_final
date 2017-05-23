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
	private ServerThread serverThread;
	private Thread[] clientThreads;
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
		clientThreads = new Thread[MAX_CLIENTS];
		clientsConnected = new boolean[MAX_CLIENTS];
		listen();
	}

	public boolean isListening()
	{
		return listening;
	}

	public void listen()
	{
		listening = true;
		ServerThread serverThread = new ServerThread(this);
		serverThread.start();
	}

	public void removeClient(int id)
	{
		clientsConnected[id] = false;
	}

	public void handle(Socket client, int id)
	{
		clientThreads[id] = new ServerHandlerThread(client, id, this);
		clientsConnected[id] = true;
		clientThreads[id].start();
	}

	public void stop()
	{
		if (listening)
		{
			System.out.println("Server Stopped");
			listening = false;
			try
			{
				close();
			}
			catch (Exception e)
			{

			}
		}
	}

	public static void main(String[] args)
	{
		try
		{
			Server server = new Server();
			Scanner s = new Scanner(System.in);
			s.nextLine();
			server.stop();
		}
		catch (Exception e)
		{

		}
	}

	private static class ServerThread extends Thread
	{
		private Server server;
		public ServerThread(Server s)
		{
			super();
			server = s;
		}
		public void run()
		{
			try
			{
				while (server.isListening())
				{
					Socket client = server.accept();
					for (int i = 0; i < MAX_CLIENTS; i++)
					{
						if (!server.clientsConnected[i])
						{
							String clientIP = client.getInetAddress().getHostAddress();
							System.out.println("Connection established: " + clientIP);
							server.handle(client, i);
							break;
						}
					}
				}
			}
			catch (IOException e)
			{
				System.out.println("Error: Client not accepted");
			}
		}
	}

}
