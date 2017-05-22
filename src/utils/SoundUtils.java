// Utilities for playing sounds

package utils;

import javafx.scene.media.AudioClip;
import java.io.File;

public class SoundUtils
{

	public static AudioClip getAudioClip(String path)
	{
		AudioClip ac = null;

		try
		{
			File f = new File(path);
			String uri = f.toURI().toString();
			ac = new AudioClip(uri);
		}
		catch (Exception e)
		{
			System.out.println("Error: couldn't find file '" + path + "'");
		}

		return ac;
	}

}
