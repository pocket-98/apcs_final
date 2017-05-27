// JFrame for game

package ui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javafx.scene.media.AudioClip;
import java.awt.Toolkit;
import window.ClosableWindow;
import window.ResizableComponent;
import game.SaveFile;
import utils.SoundUtils;
import ui.MenuPanel;
import ui.HelpPanel;

public class AdBlockerFrame extends JFrame implements ClosableWindow.Listener, ResizableComponent.Listener
{

	// Frame Constants
	private String title = "Ad Blocker";
	private String subtitle = "The Game";
	private int width;
	private int height;

	// GUI Items
	private MenuPanel menuPanel;
	private HelpPanel helpPanel;

	// Sound Items
	private AudioClip music;

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
		helpPanel = new HelpPanel(title, subtitle, width, height, this);
		add(menuPanel);
		//add(helpPanel);

		playBackgroundMusic();

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

	public void menu()
	{
		System.out.println("Opening Main Menu");
		//show menu in card layout
	}

	public void startGame(SaveFile f)
	{
		stopBackgroundMusic();
		System.out.println("Starting Game");
		//start game in card layout
	}

	public void createGame()
	{
		System.out.println("Creating New Game");
		startGame(new SaveFile());
	}

	public void pickGame()
	{
		System.out.println("Choosing Save File");
		//continue game in card layout
	}

	public void showHelp()
	{
		System.out.println("Showing Help");
		//show help in card layout
	}

	private void playBackgroundMusic()
	{
		music = SoundUtils.getAudioClip("res/menu/bread.mp3");
		music.play();
		//System.out.println("Playing Background Music");
	}

	private void stopBackgroundMusic()
	{
		music.stop();
		//System.out.println("Stopping Background Music");
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

	public static void main(String[] args)
	{
		AdBlockerFrame f = new AdBlockerFrame();
	}

}
