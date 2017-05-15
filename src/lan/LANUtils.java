// Utiilities for LAN

package lan;

import java.net.Socket;

public class LANUtils
{
	public static boolean hostIsActive(String host, int port)
	{
		try
		{
			Socket s //= new Socket();
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
}
