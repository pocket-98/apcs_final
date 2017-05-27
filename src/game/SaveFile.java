// Save and load game status

package game;

import java.io.File;
import java.io.IOException;

public class SaveFile
{

	private File file;

	public SaveFile()
	{
		file = new File("save1.txt");
	}

	public SaveFile(String path)
	{
		file = new File(path);
	}
}
