// Holds score for game

package game.gameelement;

import javax.swing.JLabel;
import java.awt.Graphics;
import game.SaveFile;
import game.GameElement;

public class GameScore extends JLabel
{

	private SaveFile save;

	public GameScore(SaveFile s)
	{
		save = s;
	}
	
	public void paintComponent(Graphics g)
	{
		setText("SCORE: " + save.getScore());
		super.paintComponent(g);
	}
}
