// Transparent JButton

package ui;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TransparentButton extends JButton
{

	public TransparentButton(String text)
	{
		super(text);
		setTransparent();
	}

	public TransparentButton(String text, ImageIcon icon)
	{
		super(text, icon);
		setTransparent();
	}

	public void setTransparent()
	{
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		//this.setOpaque(false);
		//this.setForeground(Color.WHITE);
	}

}
