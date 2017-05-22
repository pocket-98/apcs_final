// Utilities for game logic

package game;

import javax.swing.ImageIcon;
import java.io.File;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import image.ImageUtils;

public class GameUtils
{

	private static boolean initializedFont = false;

	public static String getLevelPath(int level)
	{
		String path = "res/level" + level + "/";
		return path;
	}

	public static ImageIcon getLevelBackgroundIcon(int level, int width, int height)
	{
		String path = getLevelPath(level) + "background.png";
		return ImageUtils.getImageIcon(path, width, height);
	}

	public static Font initFont()
	{
		Font techno = null;
		try
		{
			techno = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/techno.ttf"));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(techno);
			initializedFont = true;
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return techno;
	}

	public static Font getFont(int style, int size)
	{
		Font techno = new Font("Arial", style, size);
		try
		{
			if (!initializedFont)
			{
				initFont();
			}

			techno = new Font("techno", style, size);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return techno;
	}

}