// Utilities for playing sounds

package utils;

import javax.sound.sampled.Clip;
import javafx.scene.media.AudioClip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SoundUtils
{

	public static Clip getClip(String path)
	{
		Clip c = null;

		try
		{
			c = AudioSystem.getClip();
			AudioInputStream in = AudioSystem.getAudioInputStream(new File(path));
			c.open(in);
		}
		catch (IOException e)
		{
			System.out.println("Error: couldn't find file '" + path + "'");
		}
		catch (UnsupportedAudioFileException e)
		{
			System.out.println("Error: file type not recognized\n"+e);
		}
		catch (LineUnavailableException e)
		{
			System.out.println("Error: couldn't play audio file");
		}

		return c;
	}

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
