// Utilities for getting scaled images

package image;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageUtils
{

	public static BufferedImage getImage(String path, int width, int height)
	{
		Image scaled;
		BufferedImage raw;
		BufferedImage finalImage;
		Graphics finalImageGraphics;
		
		finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		try
		{
			raw = ImageIO.read(new File(path));
			scaled = raw.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			finalImageGraphics = finalImage.getGraphics();
			finalImageGraphics.drawImage(scaled, 0, 0, null);
			finalImageGraphics.dispose();
		}
		catch(IOException e)
		{
			System.out.println(e);
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
			raw = ImageIO.read(new File(path));
			scaled = raw.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			finalImage = new ImageIcon(scaled);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

		return finalImage;
	}

	public static void saveImage(BufferedImage im,  String path)
	{
		try
		{
			String[] pathArray = path.split("\\.");
			String format = pathArray[pathArray.length-1];
			ImageIO.write(im, format, new File(path));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

}