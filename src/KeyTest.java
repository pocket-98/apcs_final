//test keyboard input

import javax.swing.JFrame;
import javax.swing.JLabel;
import input.Keyboard;
import input.Key;
import stuff.ClosableWindow;

public class KeyTest extends JFrame implements Keyboard.Listener, ClosableWindow.Listener
{

	private JLabel label = new JLabel("Press A Key");

	public KeyTest()
	{
		super();
		Keyboard kb = new Keyboard(this);
		ClosableWindow cw = new ClosableWindow(this);
		addKeyListener(kb);
		addWindowListener(cw);
		this.add(label);
		setSize(400, 300);
		setVisible(true);
	}

	public void updateText(String s)
	{
		label.setText(s);
	}

	public void keyPressed(Key k)
	{
		System.out.println("Key Pressed: " + k);
		updateText("Key Pressed: " + k);
	}

	public void keyReleased(Key k)
	{
		System.out.println("Key Released: " + k);
	}

	public void keyTyped(Key k)
	{
		System.out.println("Key Typed: " + k);
	}

	public void closed()
	{
		System.out.println("Closed");
	}

	public static void main(String[] args)
	{
		KeyTest t = new KeyTest();
	}
}
