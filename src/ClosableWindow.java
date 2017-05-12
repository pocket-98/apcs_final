// Closable window adapter with listener

package src;

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
		l.closed();
		System.exit(0);
	}

	public interface Listener
	{
		public void closed();
	}

}
