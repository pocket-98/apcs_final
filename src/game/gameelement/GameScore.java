// Holds score for game

package game.gameelement;

import java.awt.Graphics;
import game.SaveFile;
import game.gameelement.GameText;

public class GameScore extends GameText
{

	private SaveFile save;

	public GameScore(SaveFile s)
	{
		super();
		save = s;
	}
	
	public void paint(Graphics g)
	{
		setText("SCORE: " + save.getScore());
		super.paint(g);
	}
}
