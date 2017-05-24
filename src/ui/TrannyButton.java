// Transparent JButton

package ui;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TrannyButton extends JButton
{

	public TrannyButton(String text)
	{
		super(text);
		setTranny();
	}

	public TrannyButton(String text, ImageIcon icon)
	{
		super(text, icon);
		setTranny();
	}

	public void setTranny()
	{
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		//this.setOpaque(false);
		this.setBackground(new Color(0, 0, 0, 120));
		this.setForeground(Color.WHITE);
	}

}