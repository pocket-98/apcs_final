// Test keyboard input

import javax.swing.JFrame;
import javax.swing.JLabel;
import window.ClosableWindow;
import input.Keyboard;
import input.Key;

public class KeyTest extends JFrame implements Keyboard.Listener, ClosableWindow.Listener
{

	private JLabel label;
	private Keyboard kb;

	public KeyTest()
	{
		super();
		setTitle("Keyboard Test");
		setSize(400, 300);
		
		kb = new Keyboard(this);
		addKeyListener(kb);

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		label = new JLabel("Press A Key");
		this.add(label);
		
		setVisible(true);
	}

	public void updateText()
	{
		String s = "Keys Pressed:  ";
		for (Key k : kb.getPressedKeys())
		{
			s += k + ", ";
		}

		s = s.substring(0, s.length() - 2);
		label.setText(s);
	}

	public void keyPressed(Key k)
	{
		System.out.println("Key Pressed: " + k + " (" + k.getCode() + ")");
		updateText();
	}

	public void keyReleased(Key k)
	{
		System.out.println("Key Released: " + k + " (" + k.getCode() + ")");
		updateText();
	}

	public void keyTyped(Key k)
	{
		System.out.println("Key Typed: " + k + " (" + k.getCode() + ")");
	}

	public void close()
	{
		System.out.println("Closing");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		KeyTest t = new KeyTest();
	}
}
