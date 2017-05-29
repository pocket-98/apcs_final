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
	private String player;
	private String[] ads;

	public LevelResources(int l)
	{
		level = l;
		path = GameUtils.getLevelPath(level);
		String[] info = FileUtils.getResourceContent(path + "info.txt").split("\n\n");
		String[] nameBgMusicPlayer = info[0].split("\n");
		name = nameBgMusicPlayer[0];
		bg = nameBgMusicPlayer[1];
		music = nameBgMusicPlayer[2];
		player = nameBgMusicPlayer[3];
		ads = info[1].split("\n");
	}

	public String toString()
	{
		String s = "";
		s += "path:   " + path + "\n";
		s += "name:   " + name + "\n";
		s += "bg:     " + bg + "\n";
		s += "music:  " + music + "\n";
		s += "player: " + player + "\n";
		s += "ads:    " + Arrays.toString(ads) + "\n";
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

	public String player()
	{
		return player;
	}

	public String[] ads()
	{
		return ads;
	}

}
