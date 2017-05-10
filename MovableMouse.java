// Pavan Dayal
// Movable Mouse Adapter
// Lab 13 - 100 Points

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MovableMouse extends MouseAdapter
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
		l.released();
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mouseWheelMoved(MouseWheelEvent e)
	{
		int distance = -e.getWheelRotation();
		l.wheeled(distance);
	}

	public interface Listener
	{
		public void clicked(int x, int y);
		public void moved(int x, int y);
		public void dragged(int x, int y);
		public void pressed(int x, int y);
		public void released();
		public void wheeled(int distance);
	}

}
