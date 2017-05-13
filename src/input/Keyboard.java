// Keyboard to track pressed keys

package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class Keyboard extends KeyAdapter
{

	private Listener l;
	private Set<Key> pressed;

	public Keyboard(Listener listener)
	{
		l = listener;
		pressed = new TreeSet<Key>();
	}

	public void keyPressed(KeyEvent e)
	{
		Key k = new Key(e);
		if (!k.equals(Key.KEY_NONE))
		{
			pressed.add(k);
			l.keyPressed(k);
		}
	}

	public void keyReleased(KeyEvent e)
	{
		Key k = new Key(e);
		if (!k.equals(Key.KEY_NONE))
		{
			pressed.remove(k);
			l.keyReleased(k);
		}
	}

	public void keyTyped(KeyEvent e)
	{
		Key k = new Key(e);
		if (!k.equals(Key.KEY_NONE))
		{
			l.keyTyped(k);
		}
	}

	public boolean isPressed(Key k)
	{
		return pressed.contains(k);
	}

	public List<Key> getPressedKeys()
	{
		return new ArrayList<Key>(pressed);
	}

	public interface Listener
	{
		public void keyPressed(Key k);
		public void keyReleased(Key k);
		public void keyTyped(Key k);
	}

}
