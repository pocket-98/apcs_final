// Main launcher for game

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Toolkit;
import window.ClosableWindow;
import window.ResizableComponent;

public class Main extends JFrame implements ClosableWindow.Listener, ResizableComponent.Listener
{
	// Frame Constants
	private String title = "Ad Blocker The Game";
	private String subtitle = "Main Menu";
	private int width;
	private int height;

	// GUI Items
	private MenuPanel menuPanel;

	public Main()
	{
		super();
		getContentPane().setLayout(null); //cardlayout
		setSize(width, height);
		setTitle(title);
		resized();

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		ResizableComponent rs = new ResizableComponent(this);
		addComponentListener(rs);

		System.out.println("Opening Window");
		menuPanel = new MenuPanel(title, subtitle, width, height);
		add(menuPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
	}

	public void resized()
	{
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		menuPanel.setSize(width, height);
		setSize(width, height);
	}

	public void closed()
	{
		System.out.println("Closing");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		try
		{
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println("Warning: Couldn't change theme, reverting to default");
		}

		JFrame m = new Main();
	}
}
