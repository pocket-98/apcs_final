// Holds score for game

package game.gameelement;

import java.awt.Graphics;
import game.GameThread;
import game.gameelement.GameText;

public class GameFPSCounter extends GameText
{

	private final int DECIMALS = 2;
	private GameThread gameThread;

	public GameFPSCounter(GameThread gt)
	{
		super();
		gameThread = gt;
	}

	private double round(double d)
	{
		int exp = (int) Math.pow(10, DECIMALS);
		return Math.round(exp * d) / exp;
	}

	public void setGameThread(GameThread gt)
	{
		gameThread = gt;
	}

	public void paint(Graphics g)
	{
		setText("FPS: " + round(gameThread.fps()));
		super.paint(g);
	}
}
