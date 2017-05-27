// Utilities to fetch a resource

package utils;

import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class FileUtils
{

	private static boolean initializedFont = false;

	public static URL getResource(String path)
	{
		return FileUtils.class.getClassLoader().getResource(path);
	}

	public static InputStream getResourceStream(String path)
	{
		return FileUtils.class.getClassLoader().getResourceAsStream(path);
	}

	public static String getResourceContent(String path)
	{
		String s = "";
		String line = "";
		try
		{
			InputStreamReader in = new InputStreamReader(getResourceStream(path));
			BufferedReader reader = new BufferedReader(in);
			line = reader.readLine();
			while (line != null)
			{
				s += line;
				line = reader.readLine();
			}
			reader.close();
			in.close();
		}
		catch (IOException e)
		{
			System.out.println("Error: Couldn't get resource content");
		}
		return s;
	}

	public static Font initFont()
	{
		Font techno = null;
		String path = "res/font/techno.ttf";
		try
		{
			techno = Font.createFont(Font.TRUETYPE_FONT, FileUtils.getResourceStream(path));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(techno);
			initializedFont = true;
		}
		catch (IOException e)
		{
			System.out.println("Error: couldn't find file '" + path + "'");
		}
		catch (FontFormatException e)
		{
			System.out.println("Error: couldn't initialize techno font");
		}

		return techno;
	}

	public static Font getFont(int style, int size)
	{
		Font techno = new Font("Arial", style, size);
		try
		{
			if (!initializedFont)
			{
				initFont();
			}

			techno = new Font("FORCED SQUARE", style, size);
		}
		catch (Exception e)
		{
			System.out.println("Warning: couldn't load techno font, defaulting to Arial");
		}

		return techno;
	}

}