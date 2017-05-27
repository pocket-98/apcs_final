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
	private String name;
	private int level;

	public SaveFile()
	{
		file = new File("save1.txt");
		name = "Default";
		level = 1;
		save();
	}

	public SaveFile(String path)
	{
		file = new File(path);
		name = "Default";
		level = 1;
		load();
		save();
	}

	public SaveFile(String n, int l)
	{
		file = new File("save1.txt");
		name = n;
		level = l;
		save();
	}

	private void load()
	{
		try
		{
			FileReader fileIn = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileIn);
			System.out.println(reader.readLine());
			System.out.println(reader.readLine());
			System.out.println(reader.readLine());
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
			writer.write("test");
			writer.newLine();
			writer.write("test2");
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

	public void setName(String n)
	{
		name = n;
	}

	public void setLevel(int l)
	{
		level = l;
	}

	public String getName()
	{
		return name;
	}

	public int getLevel()
	{
		return level;
	}

}
