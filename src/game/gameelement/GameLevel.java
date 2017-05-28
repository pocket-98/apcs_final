// Holds level for game

package game.gameelement;

import javax.swing.JLabel;
import game.GameElement;

public class GameLevel extends JLabel
{

	private int level;
	private String name;

	public GameLevel(int l, String n)
	{
		level = l;
		name = n;
		setText("<html>LEVEL " + level + ": <br/>" + name + "</html>");
	}

}
