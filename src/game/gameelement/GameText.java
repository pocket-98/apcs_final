// Holds text for game

package game.gameelement;

import javax.swing.JLabel;
import java.awt.Graphics;

public class GameText extends JLabel
{

	public void paint(Graphics g)
	{
		/*g.setColor(getForeground());
		g.setFont(getFont());
		g.drawString(getText(), x+offsetX, y+offsetY);*/
		super.paint(g);
	}

}
