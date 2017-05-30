// Holds image and methods to move player

package game.gameelement;

import game.GameElement;

public class Player extends GameElement
{

	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	private double velocity;
	private double acceleration;
	private double friction;

	public Player(String path, int width, int height)
	{
		super(path, width, height, true);
		minX = 0;
		maxX = 0;
		minY = 0;
		maxY = 0;
		acceleration = 0.8;
		friction = 0.1;
	}

	private void validatePosition()
	{
		if (y > maxY)
		{
			y = maxY;
			velocity = 0;
		}

		if (y < minY)
		{
			y = minY;
			velocity = 0;
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

	public void increaseVelocity()
	{
		if (velocity > 0)
		{
			velocity -= acceleration; //turn around extra fast
		}
		
		velocity -= acceleration;
	}

	public void decreaseVelocity()
	{
		if (velocity < 0)
		{
			velocity += acceleration; //turn around extra fast
		}
		
		velocity += acceleration;
	}

	public void move()
	{
		velocity -= Math.copySign(friction*velocity, velocity);
		y = (int) Math.round(y + velocity);
		validatePosition();
	}

	public void jumpLeft()
	{
		x = minX;
	}

	public void jumpRight()
	{
		x = maxX;
	}

	public void setAcceleration(double a)
	{
		acceleration = a;
	}

	public void setFriction(double f)
	{
		friction = f;
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

	public double getAcceleration()
	{
		return acceleration;
	}

	public double getFriction()
	{
		return friction;
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
