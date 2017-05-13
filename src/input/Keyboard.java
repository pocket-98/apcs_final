// Keyboard adapter with listener to simplify keyboard actions

package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import input.Key;

public class Keyboard extends KeyAdapter
{

	private Listener l;

	public Keyboard(Listener listener)
	{
		l = listener;
	}

	public void keyPressed(KeyEvent e)
	{
		Key k = new Key(e);
		l.keyPressed(k);
	}

	public interface Listener
	{
		public void keyPressed(Key k);
		public void keyReleased(Key k);
		public void keyTyped(Key k);
	}

}
