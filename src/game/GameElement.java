// Element in a game that can be painted

package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.ImageUtils;

public class GameElement
{

	protected String path;
	protected int width;
	protected int height;
	protected boolean transparent;
	protected BufferedImage image;
	protected int x;
	protected int y;
	protected int offsetX;
	protected int offsetY;

	public GameElement(String p, int w, int h, boolean t)
	{
		path = p;
		width = w;
		height = h;
		transparent = t;
		x = 0;
		y = 0;
		offsetX = 0;
		offsetY = 0;

		int type = getType();
		image = ImageUtils.getImage(path, width, height, type);
	}

	public GameElement(BufferedImage im, int w, int h, boolean t)
	{
		path = null;
		width = w;
		height = h;
		transparent = t;
		x = 0;
		y = 0;
		offsetX = 0;
		offsetY = 0;

		int type = getType();
		image = ImageUtils.scaleImage(im, width, height, type);		
	}

	public GameElement(int w, int h, boolean t)
	{
		path = null;
		width = w;
		height = h;
		transparent = t;
		x = 0;
		y = 0;
		offsetX = 0;
		offsetY = 0;

		int type = getType();
		image = new BufferedImage(width, height, type);
	}

	public void setImage(String p, int w, int h)
	{
		path = p;
		resize(w, h);
	}

	private int getType()
	{
		int type = BufferedImage.TYPE_INT_RGB;
		if (transparent)
		{
			type = BufferedImage.TYPE_INT_ARGB;
		}
		return type;
	}

	public void setBounds(int newX, int newY, int w, int h)
	{
		setX(newX);
		setY(newY);
		resize(w, h);
	}

	public void setX(int newX)
	{
		x = newX;
	}

	public void setY(int newY)
	{
		y = newY;
	}

	public void setOffsetX(int newX)
	{
		offsetX = newX;
	}

	public void setOffsetY(int newY)
	{
		offsetY = newY;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getOffsetX()
	{
		return offsetX;
	}

	public int getOffsetY()
	{
		return offsetY;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void resize(int w, int h)
	{
		width = w;
		height = h;
		int type = getType();
		if (path == null)
		{
			image = ImageUtils.scaleImage(image, width, height, type);
		}
		else
		{
			image = ImageUtils.getImage(path, width, height, type);
		}
	}

	public Graphics getGraphics()
	{
		return image.getGraphics();
	}

	public void paint(Graphics g)
	{
		g.drawImage(image, x+offsetX, y+offsetY, null);
	}

}
