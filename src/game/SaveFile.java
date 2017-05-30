// Save and load game status

package game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import game.GameConstants;

public class SaveFile
{

	private File file;
	private String path;
	private int level;
	private int score;

	public SaveFile()
	{
		path = "save.txt";
		file = new File(path);
		level = 1;
		score = 0;
		save();
	}

	public SaveFile(int l)
	{
		path = "save.txt";
		file = new File(path);
		level = l;
		score = 0;
		checkLevel();
		save();
	}

	public SaveFile(int l, int s)
	{
		path = "save.txt";
		file = new File(path);
		level = l;
		score = s;
		checkLevel();
		save();
	}

	public SaveFile(String p)
	{
		path = p;
		file = new File(path);
		level = 1;
		score = 0;
		load();
		save();
	}

	private void load()
	{
		try
		{
			FileReader fileIn = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileIn);
			level = Integer.parseInt(reader.readLine());
			score = Integer.parseInt(reader.readLine());
			reader.close();
			fileIn.close();
			save();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: Couldn't find save file");
		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't load game");
		}
	}

	public void save()
	{
		try
		{
			FileWriter fileOut = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fileOut);
			writer.write(String.valueOf(level));
			writer.newLine();
			writer.write(String.valueOf(score));
			writer.newLine();
			writer.flush();
			writer.close();
			fileOut.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: Couldn't find save file");
		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't save game");
		}
	}

	private void checkLevel()
	{
		if (level < 1)
		{
			level = 1;
			System.out.println("Warning: Set level to 1");
		}
		if (level > GameConstants.NUM_LEVELS)
		{
			level = GameConstants.NUM_LEVELS;
			System.out.println("Warning: Set level to " + GameConstants.NUM_LEVELS);
		}
	}

	public String toString()
	{
		String s = "";
		s += "path:  " + path + "\n";
		s += "level: " + level + "\n";
		s += "score: " + score;
		return s;
	}

	public void setLevel(int l)
	{
		level = l;
		checkLevel();
		save();
	}

	public void setScore(int s)
	{
		score = s;
		save();
	}

	public void changeLevel(int dl)
	{
		level += dl;
		checkLevel();
		save();
	}

	public void changeScore(int ds)
	{
		score += ds;
		save();
	}

	public String getPath()
	{
		return path;
	}

	public int getLevel()
	{
		return level;
	}

	public int getScore()
	{
		return score;
	}

}
