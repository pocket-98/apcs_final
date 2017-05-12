// Used to hold and compare keyboard keys

package src.input;

import java.awt.event.KeyEvent;

public class Key
{

	//public static final Key UP;

	private KeyEvent k;

	public Key(KeyEvent key)
	{
		k = key;
	}

	public boolean equals(Key other)
	{
		boolean eq = k.getExtendedKeyCode() == other.k.getExtendedKeyCode();
		return eq;
	}

	public String toString()
	{
		int code = k.getExtendedKeyCode();
		String s = KeyEvent.getKeyText(code);
		return s;
	}
}