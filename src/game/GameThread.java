// Thread that can be paused and continued

package game;

public abstract class GameThread extends Thread
{

	private final int FPS_NUM_FRAMES = 10;

	private double maxfps;
	private double[] fps;
	private boolean running;

	public abstract void paint();

	public GameThread(double max)
	{
		super();
		maxfps = max;
		fps = new double[FPS_NUM_FRAMES];
		running = false;

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
				for (int i = 0; i < FPS_NUM_FRAMES; i++)
				{
					preTime = System.currentTimeMillis();
					paint();
					
					waitTime = maxfpsTime - deltaTime(preTime);
					if (waitTime > 0)
					{
						sleep(waitTime);
					}

					fps[i] = 1000.0 / deltaTime(preTime);

					//System.out.println(fps());
					if (!running)
					{
						break;
					}
				}
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Error: Thread interrupted");
		}
	}

	public double fps()
	{
		double mean = 0.0;
		int count = 0;
		for (double f : fps)
		{
			if (f > 0.0)
			{
				mean += f;
				count++;
			}
		}
		return mean/count;
	}

	public void pause()
	{
		running = false;
	}

}
