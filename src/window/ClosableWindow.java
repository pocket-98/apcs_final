// Alert window when closed

package window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClosableWindow extends WindowAdapter
{

	private Listener l;

	public ClosableWindow(Listener listener)
	{
		l = listener;
	}

	public void windowClosing(WindowEvent e)
	{
		l.close();
		System.exit(0);
	}

	public interface Listener
	{
		public void close();
	}

}
