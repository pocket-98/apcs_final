// Holds possible enemies

package game.gameelement;

import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import game.gameelement.Enemy;
import game.GameElement;
import utils.ImageUtils;

public class EnemyBank extends GameElement
{

	private final AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.CLEAR);
	private final AlphaComposite normal = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);

	private final int numEnemies = 150;

	private String path;
	private String[] ads;
	private Graphics2D vg;
	private Enemy[] enemies;
	private boolean[] isAlive;
	private int minY;
	private int maxY;
	private int minSize;
	private int maxSize;
	private double minVelocity;
	private double maxVelocity;

	public EnemyBank(String p, String[] a, int width, int height)
	{
		super(width, height, true);
		path = p;
		ads = a;
		vg = (Graphics2D) image.getGraphics();
		enemies = new Enemy[numEnemies];
		isAlive = new boolean[numEnemies];
		for (int i = 0; i < numEnemies; i++)
		{
			isAlive[i] = true;
		}

		minY = 0;
		maxY = height;
		minSize = width/16;
		maxSize = width/6;
		minVelocity = 5;
		maxVelocity = 15;
	}

	private int rand(int a, int b)
	{
		int range = 1 + b - a;
		double r = Math.random();
		return a + (int)(r*range);
	}

	private double rand(double a, double b)
	{
		double range = b - a;
		double r = Math.random();
		return a + r*range;
	}

	public void resize(int w, int h)
	{
		super.resize(w, h);
		vg = (Graphics2D) image.getGraphics();
	}

	public void setEnemySize(int min, int max)
	{
		minSize = min;
		maxSize = max;
	}

	public void setEnemyVelocity(double min, double max)
	{
		minVelocity = min;
		maxVelocity = max;
	}

	public void setEnemyY(int min, int max)
	{
		minY = min;
		maxY = max;
	}

	public void move()
	{
		int enemyX;
		int enemySize;
		for (int i = 0; i < numEnemies; i++)
		{
			if (enemies[i] != null && isAlive[i])
			{
				enemies[i].move();
				enemyX = enemies[i].getX();
				enemySize = enemies[i].getWidth();
				if (enemyX < -enemySize || enemyX > width)
				{
					enemies[i] = null;
					isAlive[i] = false;
				}
			}

		}
	}

	public void spawn()
	{
		String im = path + ads[rand(0, ads.length-1)];
		double s = Math.random();
		int size = minSize + (int)(s*(maxSize-minSize+1));
		double velocity = minVelocity + (1-s)*(maxVelocity-minVelocity);
		int ypos = rand(minY, maxY-size);
		int xpos = -size;
		if (rand(0, 1) == 1) //start from right side instead
		{
			velocity *= -1;
			xpos = width;
		}

		int id = -1;
		for (int i = 0; i < numEnemies; i++)
		{
			if (enemies[i] == null && isAlive[i])
			{
				id = i;
			}
		}
		if (id >= 0)
		{
			Enemy e = new Enemy(id, im, size, velocity);
			e.setXRange(-size, width);
			e.setX(xpos);
			e.setY(ypos);
			enemies[id] = e;
		}
	}

	public void paint(Graphics g)
	{
		vg.setComposite(alpha);
		vg.fillRect(0, 0, width, height);
		vg.setComposite(normal);
		for (int i = 0; i < numEnemies; i++)
		{
			if (enemies[i] != null && isAlive[i])
			{
				enemies[i].paint(vg);
			}
		}
		super.paint(g);
	}

}