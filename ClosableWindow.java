// Pavan Dayal
// Closable Window Adapter
// Lab 13 - 100 Points

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
