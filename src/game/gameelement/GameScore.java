// Holds score for game

package game.gameelement;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
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
	
	public void paint(Graphics g)
	{
		setText("SCORE: " + save.getScore());
		g.setFont(getFont());
		paintComponent(g);
	}
}
