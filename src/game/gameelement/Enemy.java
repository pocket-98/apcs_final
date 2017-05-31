// Holds single enemy

package game.gameelement;

import java.awt.Rectangle;
import game.GameElement;

public class Enemy extends GameElement
{

	private int id;
	private double velocity;
	private int minX;
	private int maxX;

	public Enemy(int i, String path, int size, double vel)
	{
		super(path, size, size, true);
		id = i;
		velocity = vel;
		minX = -size;
		maxX = 2*size;
	}

	public void setXRange(int min, int max)
	{
		minX = min;
		maxX = max;
	}

	public int getId()
	{
		return id;
	}

	public boolean getLeft()
	{
		return velocity > 0;
	}

	public Rectangle getRekt()
	{
		return new Rectangle(x, y, width, height);
	}

	public void move()
	{
		x += velocity;
	}

}