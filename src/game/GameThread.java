// Thread that can be paused and continued

package game;

public abstract class GameThread extends Thread
{
	private double maxfps;
	private double fps;
	private boolean running;

	public abstract void paint();

	public GameThread(double max)
	{
		super();
		maxfps = max;
	}

	private int deltaTime(long preTime)
	{
		return (int) (System.currentTimeMillis() - preTime);
	}

	public void run()
	{
		running = true;
		long preTime;
		int maxfpsTime = (int) (1000/maxfps);
		int waitTime;
		try
		{
			while (running)
			{
				preTime = System.currentTimeMillis();
				paint();
				
				waitTime = maxfpsTime - deltaTime(preTime);
				if (waitTime > 0)
				{
					sleep(waitTime);
				}

				fps = 1000.0 / deltaTime(preTime);
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Error: Thread interrupted");
		}
	}

	public double fps()
	{
		return fps;
	}

	public void pause()
	{
		running = false;
	}

}
