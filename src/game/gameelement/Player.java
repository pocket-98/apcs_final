// Holds image and methods to move player

package game.gameelement;

import game.GameElement;

public class Player extends GameElement
{

	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	private int speed;

	public Player(String path, int width, int height)
	{
		super(path, width, height, true);
		minX = 0;
		maxX = 0;
		minY = 0;
		maxY = 0;
		speed = 5;
	}

	private void validatePosition()
	{
		if (y > maxY)
		{
			y = maxY;
		}

		if (y < minY)
		{
			y = minY;
		}

		if (Math.abs(x-minX) < Math.abs(x-maxX))
		{
			x = minX;
		}
		else
		{
			x = maxX;
		}
	}

	public void moveUp()
	{
		y -= speed;
		validatePosition();
	}

	public void moveDown()
	{
		y += speed;
		validatePosition();
	}

	public void setSpeed(int s)
	{
		speed = s;
	}

	public void setMinX(int x)
	{
		minX = x;
		validatePosition();
	}

	public void setMaxX(int x)
	{
		maxX = x;
		validatePosition();
	}

	public void setMinY(int y)
	{
		minY = y;
		validatePosition();
	}

	public void setMaxY(int y)
	{
		maxY = y;
		validatePosition();
	}

	public int getSpeed()
	{
		return speed;
	}

	public int getMinX()
	{
		return minX;
	}

	public int getMaxX()
	{
		return maxX;
	}

	public int getMinY()
	{
		return minY;
	}

	public int getMaxY()
	{
		return maxY;
	}

}
