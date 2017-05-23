// test communication between server and client

package lan;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import utils.LANUtils;
import utils.GameUtils;
import lan.ServerHandlerThread;

import java.util.Scanner;

public class TestServer implements ServerHandlerThread.Listener
{

	public static final int PORT = 2222;
	public static final int MAX_CLIENTS = 5;

	private boolean listening;
	private ServerSocket server;
	private Thread serverThread;
	private Thread[] clientThreads;
	private boolean[] clientsConnected;

	public TestServer()
	{
		try
		{
			server = new ServerSocket(PORT);
			System.out.println("Server Listening on following addresses:");
			System.out.println(LANUtils.getIPs());
			System.out.println();
			clientThreads = new Thread[MAX_CLIENTS];
			clientsConnected = new boolean[MAX_CLIENTS];
			listening = true;
			listen();
		}
		catch (IOException e)
		{
			System.out.println("Error: couldn't bind server to port " + PORT);
		}
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

	public void listen()
	{
		serverThread = new Thread()
		{
			public void run()
			{
				while (listening)
				{
					try
					{
						Socket client = server.accept();
						String clientIP = client.getInetAddress().getHostAddress();
						System.out.println("Connection established: " + clientIP);
						for (int i = 0; i < MAX_CLIENTS; i++)
						{
							if (!clientsConnected[i])
							{
								handle(client, i);
								break;
							}
						}
					}
					catch (IOException e)
					{
						System.out.println("Error: Client not accepted");
					}
				}
			}
		};

		serverThread.start();
		
	}

	public void stop()
	{
		if (listening)
		{
			System.out.println("Server Stopped");
			listening = false;
			serverThread.stop();
		}
	}

	public static void main(String[] args)
	{
		TestServer server = new TestServer();

		Scanner s = new Scanner(System.in);
		s.nextLine();

		server.stop();
	}

}
