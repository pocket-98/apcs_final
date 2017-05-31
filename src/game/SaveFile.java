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

	private final String PATH = GameConstants.SAVE_FILE;

	private File file;
	private String difficulty;
	private double multiplier;
	private int level;
	private int score;

	public SaveFile()
	{
		file = new File(PATH);
		difficulty = GameConstants.DIFFICULTY_LABELS[0];
		multiplier = GameConstants.DIFFICULTY_MULTIPLIERS[0];
		level = 1;
		score = 0;
		save();
	}

	public SaveFile(int l)
	{
		file = new File(PATH);
		difficulty = GameConstants.DIFFICULTY_LABELS[0];
		multiplier = GameConstants.DIFFICULTY_MULTIPLIERS[0];
		level = l;
		score = 0;
		checkLevel();
		save();
	}

	public SaveFile(int l, int s)
	{
		file = new File(PATH);
		difficulty = GameConstants.DIFFICULTY_LABELS[0];
		multiplier = GameConstants.DIFFICULTY_MULTIPLIERS[0];
		level = l;
		score = s;
		checkLevel();
		save();
	}
	
	public SaveFile(String d, double m, int l, int s)
	{
		file = new File(PATH);
		difficulty = d;
		multiplier = m;
		level = l;
		score = s;
		checkLevel();
		save();
	}


	public SaveFile(boolean load)
	{
		file = new File(PATH);
		difficulty = GameConstants.DIFFICULTY_LABELS[0];
		multiplier = GameConstants.DIFFICULTY_MULTIPLIERS[0];
		level = 1;
		score = 0;
		if (load)
		{
			load();
		}
		save();
	}

	private void load()
	{
		try
		{
			FileReader fileIn = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileIn);
			difficulty = reader.readLine().trim();
			multiplier = Double.parseDouble(reader.readLine());
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
			writer.write(difficulty);
			writer.newLine();
			writer.write(String.valueOf(multiplier));
			writer.newLine();
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
		s += "PATH:  " + PATH + "\n";
		s += "level: " + level + "\n";
		s += "score: " + score;
		return s;
	}

	public void setDifficulty(String d)
	{
		difficulty = d;
		save();
	}

	public void setMultiplier(double m)
	{
		multiplier = m;
		save();
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
		return PATH;
	}

	public String getDifficulty()
	{
		return difficulty;
	}

	public double getMultiplier()
	{
		return multiplier;
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
