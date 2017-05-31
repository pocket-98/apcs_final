// Holds single enemy

package game.gameelement;

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

	public int[] getFront()
	{
		int[] front = new int[3];
		if (velocity > 0)
		{
			front[0] = x + width;
		}
		else
		{
			front[0] = x;
		}
		front[1] = y;
		front[2] = y+width;
		return front;
	}

	public void move()
	{
		x += velocity;
	}

}