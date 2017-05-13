// Used to hold and compare keyboard keys

package input;

import java.awt.event.KeyEvent;

public class Key implements Comparable
{

	public static final Key KEY_NONE = new Key(0);

	public static final Key KEY_0 = new Key(KeyEvent.VK_0);
	public static final Key KEY_1 = new Key(KeyEvent.VK_1);
	public static final Key KEY_2 = new Key(KeyEvent.VK_2);
	public static final Key KEY_3 = new Key(KeyEvent.VK_3);
	public static final Key KEY_4 = new Key(KeyEvent.VK_4);
	public static final Key KEY_5 = new Key(KeyEvent.VK_5);
	public static final Key KEY_6 = new Key(KeyEvent.VK_6);
	public static final Key KEY_7 = new Key(KeyEvent.VK_7);
	public static final Key KEY_8 = new Key(KeyEvent.VK_8);
	public static final Key KEY_9 = new Key(KeyEvent.VK_9);

	public static final Key KEY_A = new Key(KeyEvent.VK_A);
	public static final Key KEY_B = new Key(KeyEvent.VK_B);
	public static final Key KEY_C = new Key(KeyEvent.VK_C);
	public static final Key KEY_D = new Key(KeyEvent.VK_D);
	public static final Key KEY_E = new Key(KeyEvent.VK_E);
	public static final Key KEY_F = new Key(KeyEvent.VK_F);
	public static final Key KEY_G = new Key(KeyEvent.VK_G);
	public static final Key KEY_H = new Key(KeyEvent.VK_H);
	public static final Key KEY_I = new Key(KeyEvent.VK_I);
	public static final Key KEY_J = new Key(KeyEvent.VK_J);
	public static final Key KEY_K = new Key(KeyEvent.VK_K);
	public static final Key KEY_L = new Key(KeyEvent.VK_L);
	public static final Key KEY_M = new Key(KeyEvent.VK_M);
	public static final Key KEY_N = new Key(KeyEvent.VK_N);
	public static final Key KEY_O = new Key(KeyEvent.VK_O);
	public static final Key KEY_P = new Key(KeyEvent.VK_P);
	public static final Key KEY_Q = new Key(KeyEvent.VK_Q);
	public static final Key KEY_R = new Key(KeyEvent.VK_R);
	public static final Key KEY_S = new Key(KeyEvent.VK_S);
	public static final Key KEY_T = new Key(KeyEvent.VK_T);
	public static final Key KEY_U = new Key(KeyEvent.VK_U);
	public static final Key KEY_V = new Key(KeyEvent.VK_V);
	public static final Key KEY_W = new Key(KeyEvent.VK_W);
	public static final Key KEY_X = new Key(KeyEvent.VK_X);
	public static final Key KEY_Y = new Key(KeyEvent.VK_Y);
	public static final Key KEY_Z = new Key(KeyEvent.VK_Z);

	public static final Key KEY_UP = new Key(KeyEvent.VK_UP);
	public static final Key KEY_DOWN = new Key(KeyEvent.VK_DOWN);
	public static final Key KEY_LEFT = new Key(KeyEvent.VK_LEFT);
	public static final Key KEY_RIGHT = new Key(KeyEvent.VK_RIGHT);

	public static final Key KEY_ALT = new Key(KeyEvent.VK_ALT);
	public static final Key KEY_BACKSPACE = new Key(KeyEvent.VK_BACK_SPACE);
	public static final Key KEY_CONTROL = new Key(KeyEvent.VK_CONTROL);
	public static final Key KEY_ENTER = new Key(KeyEvent.VK_ENTER);
	public static final Key KEY_ESCAPE = new Key(KeyEvent.VK_ESCAPE);
	public static final Key KEY_SHIFT = new Key(KeyEvent.VK_SHIFT);
	public static final Key KEY_SPACE = new Key(KeyEvent.VK_SPACE);

	private int k;

	public Key(KeyEvent key)
	{
		k = key.getExtendedKeyCode();
	}

	public Key(int code)
	{
		k = code;
	}

	public Key(Key key)
	{
		k = key.k;
	}

	public int compareTo(Object other)
	{
		int otherK = ((Key) other).k;

		if (k < otherK)
		{
			return -1;
		}
		else if (k == otherK)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	public boolean equals(Key other)
	{
		return k == other.k;
	}

	public String toString()
	{
		String s = KeyEvent.getKeyText(k);
		return s;
	}

	public int getCode()
	{
		return k;
	}
}
