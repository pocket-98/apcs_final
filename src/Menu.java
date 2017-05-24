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

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
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
	private JPanel titlePanel;
	private JPanel menuPanel;
	private JLabel background;

	// Sound Items
	private AudioClip music;

	public Menu()
	{
		super();
		getContentPane().setLayout(null);
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
		add(makeBackground());

		playBackgroundMusic();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
	}

	private JPanel makeTitlePanel()
	{
		int h = height/2-5;
		int w = 91*h/64;
		Font techno = GameUtils.getFont(Font.BOLD, 30);
		Color white = Color.WHITE;

		titlePanel = new JPanel(null);
		titlePanel.setBounds((width - w) / 2, 15, w, h);
		titlePanel.setBackground(new Color(0, 0, 0, 0));

		// Title Label
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(0, 30, w, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(white);
		titleLabel.setFont(techno);

		// Subtitle Label
		JLabel subtitleLabel = new JLabel(subtitle);
		subtitleLabel.setBounds(0, 80, w, 30);
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setForeground(white);
		subtitleLabel.setFont(techno);

		// Youtube Image
		JLabel youtubeLabel = new JLabel();
		youtubeLabel.setBounds(0, 0, w, h);
		youtubeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		youtubeLabel.setIcon(ImageUtils.getImageIcon("res/menu/youtube.png", w, h));

		titlePanel.add(titleLabel);
		titlePanel.add(subtitleLabel);
		titlePanel.add(youtubeLabel);
		return titlePanel;
	}

	private JPanel makeMenuPanel()
	{
		int h = height/2-5;
		int w = 3*h/2;
		
		menuPanel = new JPanel(new GridLayout(4, 1));
		menuPanel.setBounds((width - w) / 2, 3 * height / 5, w, h);
		menuPanel.setBackground(new Color(0, 0, 0, 0));
		
		// New Game Button
		ImageIcon yt = ImageUtils.getImageIcon("res/menu/youtube.png", w, h/4);
		JButton newGame = new TrannyButton("NEW GAME", yt);
		newGame.setSize(w, h/4);
		newGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("NEW GAME CLICKED");
			}
		});

		// Continue Game Button
		JButton cont = new TrannyButton("CONTINUE");
		cont.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("CONTINUE CLICKED");
			}
		});

		// Help Button
		JButton help = new TrannyButton("HELP");
		help.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("HELP CLICKED");
			}
		});

		// Exit Button
		JButton exit = new TrannyButton("EXIT");		
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("EXIT CLICKED");
				System.exit(0);
			}
		});

		menuPanel.add(newGame);
		menuPanel.add(cont);
		menuPanel.add(help);
		menuPanel.add(exit);

		menuPanel.setVisible(true);
		return menuPanel;
	}

	private JLabel makeBackground() 
	{
		int level = 1;
		background = new JLabel();
		background.setBounds(0, 0, width, height);
		background.setIcon(GameUtils.getLevelBackgroundIcon(level, width, height));
		return background;
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
