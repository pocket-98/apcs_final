package src.input;

import java.awt.Frame;
import src.ClosableWindow;
import src.input.Keyboard;
import src.input.Key;

public class t extends Frame implements Keyboard.Listener, ClosableWindow.Listener
{
	public t()
	{
		super();
		Keyboard kb = new Keyboard(this);
		ClosableWindow cw = new ClosableWindow(this);
		addKeyListener(kb);
		addWindowListener(cw);
		setSize(400, 300);
		setVisible(true);
	}

	public void keyPressed(Key k)
	{
		System.out.println("Key Pressed: " + k);
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
		Frame asdf = new t();
	}
}
