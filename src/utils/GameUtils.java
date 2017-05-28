// Utilities for game logic

package utils;

import javax.swing.ImageIcon;
import javafx.scene.media.AudioClip;
import java.util.concurrent.TimeUnit;
import utils.ImageUtils;
import utils.SoundUtils;
import utils.FileUtils;

public class GameUtils
{

	public static String getLevelPath(int level)
	{
		String path = "res/level" + level + "/";
		return path;
	}

	public static ImageIcon getLevelBackgroundIcons(int level, int width, int height)
	{
		String path = getLevelPath(level) + "background.png";
		return ImageUtils.getImageIcon(path, width, height);
	}

	public static AudioClip getLevelMusic(int level)
	{
		String path = getLevelPath(level) + "bread.mp3";
		return SoundUtils.getAudioClip(path);
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