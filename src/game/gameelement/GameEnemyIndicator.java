// Holds level for game

package game.gameelement;

import javax.swing.JLabel;
import java.awt.Graphics;
import game.GameElement;

public class GameEnemyIndicator extends JLabel
{

	private int numEnemies;

	public GameEnemyIndicator(int n)
	{
		numEnemies = n;
	}

	public void changeNumEnemies(int dn)
	{
		numEnemies += dn;
	}

	public int getNumEnemies()
	{
		return numEnemies;
	}

	public void paintComponent(Graphics g)
	{
		setText("ENEMIES: " + numEnemies);
		super.paintComponent(g);
	}

}
