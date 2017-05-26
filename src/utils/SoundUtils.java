// Utilities for playing sounds

package utils;

import javafx.scene.media.AudioClip;
import java.io.File;
import utils.FileUtils;

public class SoundUtils
{

	public static AudioClip getAudioClip(String path)
	{
		AudioClip ac = null;

		try
		{
			ac = new AudioClip(FileUtils.getResource(path).toString());
		}
		catch (Exception e)
		{
			System.out.println("Error: couldn't find file '" + path + "'");
		}

		return ac;
	}

}
