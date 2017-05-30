// Holds possible enemies

package game.gameelement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import game.gameelement.Enemy;
import game.GameElement;

public class EnemyBank extends GameElement
{

	private String path;
	private String[] ads;
	private List<Enemy> enemies;
	private Graphics vg;

	public EnemyBank(String p, String[] a, int width, int height)
	{
		super(width, height, true);
		path = p;
		ads = a;
		setEnemySize(width, height);
	}

	public void setEnemySize(int w, int h)
	{		
		enemies = new ArrayList<Enemy>();
		for (String a : ads)
		{
			enemies.add(new Enemy(path + a, w, h));
		}
	}

	public void paint(Graphics g)
	{
		Graphics vg = image.getGraphics();
		for (Enemy e : enemies)
		{
			e.paint(vg);
		}
		super.paint(g);
	}

}