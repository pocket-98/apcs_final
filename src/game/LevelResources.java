// Load resources for level

package game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import utils.FileUtils;
import utils.GameUtils;

public class LevelResources
{

	private int level;
	private String path;
	private String name;
	private String bg;
	private String music;
	private String[] ads;
	private String[] screens;

	public LevelResources(int l)
	{
		level = l;
		path = GameUtils.getLevelPath(level);
		String[] info = FileUtils.getResourceContent(path + "info.txt").split("\n\n");
		String[] nameBgMusic = info[0].split("\n");
		name = nameBgMusic[0];
		bg = nameBgMusic[1];
		music = nameBgMusic[2];
		ads = info[1].split("\n");
		screens = info[2].split("\n");
	}

	public String toString()
	{
		String s = "";
		s += "path:    " + path + "\n";
		s += "name:    " + name + "\n";
		s += "bg:      " + bg + "\n";
		s += "music:   " + music + "\n";
		s += "ads:     " + Arrays.toString(ads) + "\n";
		s += "screens: " + Arrays.toString(screens);
		return s;
	}

	public int level()
	{
		return level;
	}

	public String path()
	{
		return path;
	}

	public String name()
	{
		return name;
	}

	public String bg()
	{
		return bg;
	}

	public String music()
	{
		return music;
	}

	public String[] ads()
	{
		return ads;
	}

	public String[] screens()
	{
		return screens;
	}

}
