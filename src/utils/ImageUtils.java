// Utilities for getting scaled images from paths and saving images

package utils;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import utils.FileUtils;

public class ImageUtils
{

	public static BufferedImage getImage(String path, int width, int height)
	{
		return getImage(path, width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public static BufferedImage getImage(String path, int width, int height, int type)
	{
		Image scaled;
		BufferedImage raw;
		BufferedImage finalImage = null;
		try
		{
			raw = ImageIO.read(FileUtils.getResourceStream(path));
			finalImage = scaleImage(raw, width, height, type);
		}
		catch(IOException e)
		{
			System.out.println("Error: couldn't find file '" + path + "'");
		}

		return finalImage;
	}

	public static ImageIcon getImageIcon(String path, int width, int height)
	{
		BufferedImage raw;
		Image scaled;
		ImageIcon finalImage;

		finalImage = new ImageIcon();

		try
		{
			raw = ImageIO.read(FileUtils.getResourceStream(path));
			scaled = raw.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT);
			finalImage = new ImageIcon(scaled);
		}
		catch(IOException e)
		{
			System.out.println("Error: couldn't find file '" + path + "'");
		}

		return finalImage;
	}

	public static BufferedImage scaleImage(BufferedImage im, int width, int height)
	{
		return scaleImage(im, width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public static BufferedImage scaleImage(BufferedImage im, int width, int height, int type)
	{
		BufferedImage finalImage = new BufferedImage(width, height, type);
		Graphics finalImageGraphics = finalImage.getGraphics();
		finalImageGraphics.drawImage(im, 0, 0, width, height, null);
		finalImageGraphics.dispose();
		return finalImage;
	}

	public static void saveImage(BufferedImage im,  String path)
	{
		try
		{
			String[] pathArray = path.split("\\.");
			String format = pathArray[pathArray.length-1];
			File f = new File(path);
			f.createNewFile();
			ImageIO.write(im, format, f);
		}
		catch (IOException e)
		{
			System.out.println("Error: couldn't find file '" + path + "'");
		}
	}

	public static void main(String[] args)
	{
		int w = 1280;
		int h = 720;
		BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = im.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		g.dispose();
		saveImage(im, "../../bin/res/level1/white.png");
	}

}