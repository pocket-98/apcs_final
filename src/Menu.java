// Main Menu for Game

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javafx.scene.media.AudioClip;
import java.io.File;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import window.ClosableWindow;
import window.ResizableComponent;
import utils.ImageUtils;
import utils.SoundUtils;
import utils.GameUtils;
import ui.TrannyButton;


public class Menu extends JFrame implements ClosableWindow.Listener, ResizableComponent.Listener
{
	// Frame Constants
	private String title = "Ad Blocker The Game";
	private String subtitle = "Main Menu";
	private int width;
	private int height;

	// GUI Items

	private JLabel backgroundLabel;

	// Sound Items
	private AudioClip music;

	public Menu()
	{
		super();
		getContentPane().setLayout(null); //set to grid layout
		setSize(width, height);
		setTitle(title);
		resized();

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		ResizableComponent rs = new ResizableComponent(this);
		addComponentListener(rs);
		
		System.out.println("Opening Window");
		add(makeTitlePanel());
		add(makeMenuPanel());


		add(makeBackgroundLabel());
		playBackgroundMusic();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
	}

	private JPanel makeMenuPanel()
	{
		int w = width / 2;
		int h = width/4;
		JPanel menu = new JPanel(new GridLayout(4, 1));
		menu.setBounds((width - w) / 2, 3 * height / 5, w, h);
		menu.setBackground(new Color(0, 0, 0, 0));
		menu.add(makeNewGameButton(w, h));
		menu.add(makeContinueButton(w, h));
		menu.add(makeHelpButton(w, h));
		menu.add(makeExitButton(w, h));
		menu.setVisible(true);
		return menu;
	}
	

	private JButton makeNewGameButton(int w, int h)
	{
		JButton newGamebtn = new TrannyButton("NEW GAME");
		//newGamebtn.setIcon(ImageUtils.getImageIcon("res/menu/youtube.png", w, h/4));
		newGamebtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("NEW GAME CLICKED");
			}
		});
		return newGamebtn;
	}

	private JButton makeContinueButton(int w, int h)
	{
		JButton continuebtn = new TrannyButton("CONTINUE");

		continuebtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("CONTINUE CLICKED");
			}
		});
		return continuebtn;
	}

	private JButton makeHelpButton(int w, int h)
	{
		JButton helpbtn = new TrannyButton("HELP");
		//helpbtn.setIcon(ImageUtils.getImageIcon("res/menu/youtube.png", w, h/4));
		
		helpbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("HELP CLICKED");
			}
		});
		return helpbtn;
	}

	private JButton makeExitButton(int w, int h)
	{
		JButton exitbtn = new TrannyButton("EXIT");
		//exitbtn.setIcon(ImageUtils.getImageIcon("res/menu/youtube.png", w, h/4));
		
		exitbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("EXIT CLICKED");
				System.exit(0);
			}
		});
		return exitbtn;
	}

	private JPanel makeTitlePanel()
	{
		int w = 682, h = 480;
		JPanel title = new JPanel(null);
		title.setBounds((width - w) / 2, 15, w, h);
		title.setBackground(new Color(0, 0, 0, 0));
		title.add(makeTitleLabel(w, h));
		title.add(makeSubtitleLabel(w, h));
		title.add(makeYoutubeLabel(w, h));
		return title;
	}

	private JLabel makeTitleLabel(int w, int h)
	{
		Font techno = GameUtils.getFont(Font.BOLD, 30);
		Color white = Color.WHITE;
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(0, 30, w, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(white);
		titleLabel.setFont(techno);
		return titleLabel;
	}

	private JLabel makeSubtitleLabel(int w, int h)
	{
		Font techno = GameUtils.getFont(Font.BOLD, 24);
		Color white = Color.WHITE;
		JLabel subtitleLabel = new JLabel(subtitle);
		subtitleLabel.setBounds(0, 90, w, 30);
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setForeground(white);
		subtitleLabel.setFont(techno);
		return subtitleLabel;
	}

	private JLabel makeYoutubeLabel(int w, int h) 
	{
		JLabel youtubeLabel = new JLabel();
		youtubeLabel.setBounds(0, 0, w, h);
		youtubeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		youtubeLabel.setIcon(ImageUtils.getImageIcon("res/menu/youtube.png", w, h));
		return youtubeLabel;
	}

	private JLabel makeBackgroundLabel() 
	{
		int level = 1;
		backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, width, height);
		backgroundLabel.setIcon(GameUtils.getLevelBackgroundIcon(level, width, height));
		return backgroundLabel;
	}

	private void playBackgroundMusic()
	{
		music = SoundUtils.getAudioClip("res/menu/bread.mp3");
		music.play();
		System.out.println("Playing Background Music");
	}

	public static void warningMsgBox(String message)
	{
		String warning = "An unexpected error has occured. Not all resources were loaded.";
        JOptionPane.showMessageDialog(null, warning, message, JOptionPane.ERROR_MESSAGE);
    }

	public void resized()
	{
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize(width, height);
	}

	public void closed()
	{
		System.out.println("Closing");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		Menu menu = new Menu();
	}

}
