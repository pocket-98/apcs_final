// Mouse adapter with listener to simplify all mouse actions

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter
{

	private Listener l;

	public MovableMouse(Listener listener)
	{
		l = listener;
	}

	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		l.clicked(x, y);
	}

	public void mouseMoved(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		l.moved(x, y);
	}

	public void mouseDragged(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		l.dragged(x, y);
	}

	public void mousePressed(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		l.clicked(x, y);
	}

	public void mouseReleased(MouseEvent e)
	{	
		int x = e.getX();
		int y = e.getY();
		l.released(x, y);
	}

	public void mouseEntered(MouseEvent e)
	{
		l.entered();
	}

	public void mouseExited(MouseEvent e)
	{
		l.exited();
	}

	public void mouseWheelMoved(MouseWheelEvent e)
	{
		int dist = -e.getWheelRotation();
		l.wheeled(dist);
	}

	public interface Listener
	{
		public void clicked(int x, int y);
		public void moved(int x, int y);
		public void dragged(int x, int y);
		public void pressed(int x, int y);
		public void released(int x, int y);
		public void entered();
		public void exited();
		public void wheeled(int dist);
	}

}
