// Utilities for game logic

package utils;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import utils.ImageUtils;

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
		String path = "res/font/techno.ttf";
		try
		{
			techno = Font.createFont(Font.TRUETYPE_FONT, new File(path));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(techno);
			initializedFont = true;
		}
		catch (IOException e)
		{
			System.out.println("Error: couldn't find file '" + path + "'");
		}
		catch (FontFormatException e)
		{
			System.out.println("Error: couldn't initialize techno font");
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
			System.out.println("Warning: couldn't load techno font, defaulting to Arial");
		}

		return techno;
	}

}