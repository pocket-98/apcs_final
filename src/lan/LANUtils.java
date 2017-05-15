// Utiilities for LAN

package lan;

import java.net.Socket;

public class LANUtils
{
	public static boolean hostIsActive(String host, int port)
	{
		try
		{
			Socket s = new Socket(host, port);
			s.setSoTimeout(400);
			s.close();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
