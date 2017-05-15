// Utiilities for LAN

package lan;

import java.net.Socket;
import java.net.SocketException;
import java.net.NetworkInterface;
import java.net.InterfaceAddress;
import java.net.InetAddress;
import java.net.Inet4Address;
import java.util.Enumeration;

public class LANUtils
{
	public static boolean hostIsActive(String host, int port)
	{
		try
		{
			Socket s = new Socket();
			s.setSoTimeout(400);
			//set address host port
			//connect
			s.close();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static String getIPs()
	{
		try
		{
			Enumeration<NetworkInterface> nets;
			NetworkInterface n;
			Enumeration<InetAddress> addrs;
			InetAddress a;

			String s = "";
			nets = NetworkInterface.getNetworkInterfaces();
			while (nets.hasMoreElements())
			{
				n = nets.nextElement();
				if (n.isUp())
				{
					s += n.getName() + "\n";
					addrs = n.getInetAddresses();
					while (addrs.hasMoreElements())
					{
						a = addrs.nextElement();
						if (a instanceof Inet4Address)
						{
							s += " * " + a.getHostAddress() + "\n";
						}
					}
				}
			}

			return s.trim();
		}
		catch (Exception e)
		{
			return e.toString();
		}

	}

	public static void main(String[] args)
	{
		System.out.println(getIPs());
	}
}
