// Keyboard adapter with listener to simplify keyboard actions

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

	private void add(Key k)
	{
		pressed.add(k);
	}

	private void remove(Key k)
	{
		pressed.remove(k);
	}

	private boolean contains(Key k)
	{
		return pressed.contains(k);
	}

	public void keyPressed(KeyEvent e)
	{
		Key k = new Key(e);
		add(k);
		l.keyPressed(k);
	}

	public void keyReleased(KeyEvent e)
	{
		Key k = new Key(e);
		remove(k);
		l.keyReleased(k);
	}

	public void keyTyped(KeyEvent e)
	{
		Key k = new Key(e);
		l.keyTyped(k);
	}

	public boolean isPressed(Key k)
	{
		return contains(k);
	}

	public List<Key> getPressed()
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
