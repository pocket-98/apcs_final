// JFrame for game

package ui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Toolkit;

import window.ClosableWindow;
import window.ResizableComponent;
import ui.MenuPanel;

public class AdBlockerFrame extends JFrame implements ClosableWindow.Listener, ResizableComponent.Listener
{

	// Frame Constants
	private String title = "Ad Blocker";
	private String subtitle = "The Game";
	private int width;
	private int height;

	// GUI Items
	private MenuPanel menuPanel;

	public AdBlockerFrame()
	{
		super();
		setSystemLookAndFeel();
		getContentPane().setLayout(null); //cardlayout
		setTitle(title);
		resized();

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		ResizableComponent rs = new ResizableComponent(this);
		addComponentListener(rs);

		System.out.println("Opening Window");
		menuPanel = new MenuPanel(title, subtitle, width, height, this);
		add(menuPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
	}

	private void setSystemLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println("Warning: Couldn't change theme, reverting to default");
		}
	}

	public void newGame()
	{
		System.out.println("Starting New Game");
	}

	public void contGame()
	{
		System.out.println("Continuing Game");
	}

	public void help()
	{
		System.out.println("Showing Help");
	}

	public void resized()
	{
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize(width, height);
	}

	public void closed()
	{
		System.out.println("Closing Window");
		System.exit(0);
	}

}
