// Mouse adapter with listener to simplify all mouse actions

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter
{

	public static final int NO_BUTTON = MouseEvent.NOBUTTON;
	public static final int LEFT_BUTTON = MouseEvent.BUTTON1;
	public static final int RIGHT_BUTTON = MouseEvent.BUTTON2;
	public static final int MIDDLE_BUTTON = MouseEvent.BUTTON3;

	private Listener l;

	public Mouse(Listener listener)
	{
		l = listener;
	}

	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		int button = e.getButton();
		l.mouseClicked(x, y, button);
	}

	public void mouseMoved(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		int button = e.getButton();
		l.mouseMoved(x, y, button);
	}

	public void mouseDragged(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		int button = e.getButton();
		l.mouseDragged(x, y, button);
	}

	public void mousePressed(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		int button = e.getButton();
		l.mouseClicked(x, y, button);
	}

	public void mouseReleased(MouseEvent e)
	{	
		int x = e.getX();
		int y = e.getY();
		int button = e.getButton();
		l.mouseReleased(x, y, button);
	}

	public void mouseEntered(MouseEvent e)
	{
		l.mouseEntered();
	}

	public void mouseExited(MouseEvent e)
	{
		l.mouseExited();
	}

	public void mouseWheelMoved(MouseWheelEvent e)
	{
		int dist = -e.getWheelRotation();
		l.mouseWheeled(dist);
	}

	public interface Listener
	{
		public void mouseClicked(int x, int y, int button);
		public void mouseMoved(int x, int y, int button);
		public void mouseDragged(int x, int y, int button);
		public void mousePressed(int x, int y, int button);
		public void mouseReleased(int x, int y, int button);
		public void mouseEntered();
		public void mouseExited();
		public void mouseWheeled(int dist);
	}

}
