// Holds level for game

package game.gameelement;

import java.awt.Graphics;
import game.gameelement.GameText;

public class GameLevel extends GameText
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
