// Transparent Button

package ui;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import input.SimpleMouseListener;
import game.GameConstants;

public abstract class TransparentButton extends JLabel
{

	private final Color nohover = GameConstants.BUTTON_COLOR;
	private final Color hover = GameConstants.BUTTON_HOVER_COLOR;

	public abstract void onButtonClick();

	public TransparentButton(String text)
	{
		super(text);
		setForeground(nohover);
		setHorizontalAlignment(SwingConstants.CENTER);
		addMouseListener(new SimpleMouseListener()
		{
			public void mouseEntered(MouseEvent e) { setForeground(hover); }
			public void mouseExited(MouseEvent e) { setForeground(nohover); }
			public void mouseClicked(MouseEvent e) { onButtonClick(); }
		});
	}

}
