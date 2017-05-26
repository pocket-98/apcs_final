// Utilities for game logic

package utils;

import javax.swing.ImageIcon;
import java.util.concurrent.TimeUnit;
import utils.ImageUtils;
import utils.FileUtils;

public class GameUtils
{

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

	public static void sleep(int ms)
	{
		try
		{
			TimeUnit.MILLISECONDS.sleep(ms);
		}
		catch (InterruptedException e)
		{
			System.out.println("Error: couldn't sleep");
		}
	}

}