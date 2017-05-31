// Holds level for game

package game.gameelement;

import javax.swing.JLabel;
import java.awt.Graphics;
import game.gameelement.EnemyBank;
import game.GameElement;

public class GameEnemyIndicator extends JLabel
{

	private EnemyBank bank;

	public GameEnemyIndicator(EnemyBank b)
	{
		bank = b;
	}

	public void paintComponent(Graphics g)
	{
		setText("ENEMIES: " + bank.getEnemiesRemaining());
		super.paintComponent(g);
	}

}
