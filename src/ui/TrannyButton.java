// Transparent JButton

package ui;

import javax.swing.JButton;
import java.awt.Color;

public class TrannyButton extends JButton
{

	public TrannyButton(String text)
	{
		super(text);
		this.setBorderPainted(false);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setForeground(Color.WHITE);
	}

}