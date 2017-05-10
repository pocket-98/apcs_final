// Pavan Dayal
// Ghetto Microsoft Paint
// Lab 13 - 100 Points

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;

public class PaintApp extends Frame implements MovableMouse.Listener, ClosableWindow.Listener, ResizableComponent.Listener
{

	private final boolean DEBUGGING = true;
	private boolean initialized;
	private String title;
	private int width, height;
	private int top, left, bottom, right;
	private int margin;
	private int stroke;
	private int oldX, oldY;
	private int mouseX, mouseY;
	private int mouseAlpha;
	private Rectangle paintArea;
	private Rectangle[] palette;
	private Color[] paletteColors;
	private Color fg, bg;
	private BufferedImage bufferImage;
	private Graphics buffer;
	private BufferedImage toolsImage;
	private Graphics tools;
	private BufferedImage canvasImage;
	private Graphics canvas;
	private BufferedImage mouseImage;
	private List<QueueItem> queue;



	public PaintApp(String t, int w, int h)
	{
		super(t);

		if (DEBUGGING)
		{
			System.out.println("Creating Window");
		}

		ClosableWindow cw = new ClosableWindow(this);
		ResizableComponent cr = new ResizableComponent(this);
		MovableMouse mm = new MovableMouse(this);

		this.addWindowListener(cw);
		this.addComponentListener(cr);
		this.addMouseListener(mm);
		this.addMouseMotionListener(mm);
		this.addMouseWheelListener(mm);

		title = t;
		width = w;
		height = h;

		top = 20;
		left = 5;
		bottom = 5;
		right = 5;

		margin = 10;
		stroke = 5;

		oldX = -1;
		oldY = -1;

		mouseX = 0;
		mouseY = 0;
		mouseAlpha = 80;

		fg = Color.black;
		bg = Color.white;

		palette = new Rectangle[8];
		paletteColors = new Color[8];
		paletteColors[0] = Color.black;
		paletteColors[1] = Color.white;
		paletteColors[2] = Color.red;
		paletteColors[3] = Color.yellow;
		paletteColors[4] = Color.green;
		paletteColors[5] = Color.cyan;
		paletteColors[6] = Color.blue;
		paletteColors[7] = Color.magenta;

		initialized = false;
		queue = new ArrayList<QueueItem>();

		setSize(w, h);
		setVisible(true);
	}

	/*****************************************************/
	/*                   Paint Methods                   */
	/*****************************************************/

	public void paint(Graphics g)
	{
		if (!initialized)
		{
			updateDimensions();
		}

		QueueItem q;
		while (queue.size() > 0)
		{
			q = queue.remove(0);
			switch (q.task())
			{
				case "paintPoint":
					paintPoint(q.x(), q.y());
					break;
				case "paintBrush":
					paintBrush(q.x(), q.y());
					break;
				default:
					break;
			}
		}

		buffer.drawImage(canvasImage, 0, 0, this);
		buffer.drawImage(mouseImage, mouseX-stroke, mouseY-stroke, this);
		buffer.drawImage(toolsImage, 0, 0, this);
		g.drawImage(bufferImage, 0, 0, this);
	}

	private void clearCanvas()
	{
		canvas.setColor(bg);
		canvas.fillRect(0, 0, width, height);
	}

	private void paintTools()
	{
		if (DEBUGGING)
		{
			System.out.println("Painting Tools");
		}

		//paint margins
		int toolHeight = 60;
		Color seagreen = new Color(40, 200, 150);

		tools.setColor(seagreen);
		tools.fillRect(0, top, width, 2*margin+toolHeight);			//top
		tools.fillRect(left, 0, margin, height);					//left
		tools.fillRect(0, height-margin-bottom, width, margin);		//bottom
		tools.fillRect(width-margin-right, 0, margin, height);		//right

		//paint color palette
		int paletteSize = 3 * toolHeight / 4;
		int spacing = 0;
		int x, y;
		for (int i = 0; i < palette.length; i++)
		{
			tools.setColor(paletteColors[i]);
			x = left + margin + (paletteSize+spacing)*(i+1);
			y = top + margin + (toolHeight-paletteSize)/2;
			palette[i] = new Rectangle(x, y, paletteSize, paletteSize);
			tools.fillRect(x, y, paletteSize, paletteSize);
		}
		
	}

	private void paintBrush(int x, int y)
	{
		if (DEBUGGING)
		{
			System.out.println("Painting with Brush at (" + x + "," + y + ")");
		}

		if (oldX >= 0 && oldY >= 0)
		{
			int dist = (int) Math.sqrt((oldX-x)*(oldX-x) + (oldY-y)*(oldY-y));
			int newX, newY;

			canvas.setColor(fg);
			for (int d = 0; d < dist; d++)
			{
				newX = oldX + d*(x-oldX)/dist;
				newY = oldY + d*(y-oldY)/dist;
				paintPoint(newX, newY);
			}
		}
		else
		{
			paintPoint(x, y);
		}

		oldX = x;
		oldY = y;
	}

	private void paintPoint(int x, int y)
	{
		canvas.setColor(fg);
		canvas.fillOval(x-stroke, y-stroke, 2*stroke, 2*stroke);
	}

	/*****************************************************/
	/*                   Helper Methods                  */
	/*****************************************************/

	private void updateDimensions()
	{
		Insets i = getInsets();
		top = i.top;
		left = i.left;
		bottom = i.bottom;
		right = i.right;

		width = getWidth();
		height = getHeight();

		bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buffer = bufferImage.getGraphics();

		toolsImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		tools = toolsImage.getGraphics();

		canvasImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		canvas = canvasImage.getGraphics();
		clearCanvas();

		paintArea = new Rectangle(0, 0, width, height);
		setMouseCursor();

		paintTools();

		initialized = true;
	}

	private void setMouseCursor()
	{
		mouseImage = new BufferedImage(2*stroke+1, 2*stroke+1, BufferedImage.TYPE_INT_ARGB);
		Graphics m = mouseImage.getGraphics();

		m.setColor(new Color(fg.getRed(), fg.getGreen(), fg.getBlue(), mouseAlpha));
		m.fillOval(0, 0, 2*stroke, 2*stroke);

		m.setColor(Color.black);
		m.drawOval(0, 0, 2*stroke, 2*stroke);
	}

	private void delay(int time)
	{
		long start = System.currentTimeMillis();
		long end = start;
		while (end - start < time)
		{
			end = System.currentTimeMillis();
		}
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	/*****************************************************/
	/*                    Mouse Methods                  */
	/*****************************************************/

	public void clicked(int x, int y)
	{
		boolean pickedColor = false;
		for (int i = 0; i < palette.length; i++)
		{
			if (palette[i].contains(x, y))
			{
				fg = paletteColors[i];
				setMouseCursor();
				pickedColor = true;
				break;
			}
		}

		if (pickedColor)
		{
			//pick tool
		}
		else
		{
			pressed(x, y);
		}

		released();
	}

	public void moved(int x, int y)
	{
		if (paintArea.contains(x, y))
		{
			mouseX = x;
			mouseY = y;
			repaint();
		}
	}

	public void dragged(int x, int y)
	{
		if (paintArea.contains(x, y))
		{
			mouseX = x;
			mouseY = y;
			queue.add(new QueueItem("paintBrush", x, y));
			repaint();
		}
	}

	public void pressed(int x, int y)
	{
		if (paintArea.contains(x, y))
		{
			queue.add(new QueueItem("paintBrush", x, y));
			repaint();
		}
	}

	public void released()
	{
		oldX = -1;
		oldY = -1;
	}

	public void wheeled(int distance)
	{
		stroke += distance;

		if (stroke < 1)
		{
			stroke = 1;
		}

		if (DEBUGGING)
		{
			System.out.println("Setting Brush Size to " + stroke);
		}

		setMouseCursor();
		repaint();
	}

	/*****************************************************/
	/*                   Window Methods                  */
	/*****************************************************/

	public void resized()
	{
		if (initialized)
		{
			initialized = false;
			repaint();
		}
	}

	public void closed()
	{
		if (DEBUGGING)
		{
			System.out.println("Exiting");
		}
	}

	/*****************************************************/
	/*                     Main Method                   */
	/*****************************************************/

	public static void main(String args[])
	{
		PaintApp p = new PaintApp("Ghetto Paint", 1024, 768);
	}

}
