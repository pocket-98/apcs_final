// Save and load game status

package game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

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
		save();
	}

	public SaveFile(int l, int s)
	{
		path = "save.txt";
		file = new File(path);
		level = l;
		score = s;
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
	}

	public void setScore(int s)
	{
		score = s;
	}

	public void changeLevel(int dl)
	{
		level += dl;
	}

	public void changeScore(int ds)
	{
		score += ds;
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
