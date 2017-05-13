// Resizable component adapter with listener

package stuff;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizableComponent extends ComponentAdapter
{

	private Listener l;

	public ResizableComponent(Listener listener)
	{
		l = listener;
	}

	public void componentHidden(ComponentEvent e) {}

	public void componentMoved(ComponentEvent e) {}

	public void componentResized(ComponentEvent e)
	{
		l.resized();
	}

	public void componentShown(ComponentEvent e) {}

	public interface Listener
	{
		public void resized();
	}

}
