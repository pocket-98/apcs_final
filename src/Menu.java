// Main Menu for Game

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
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

public class Menu extends JFrame implements ClosableWindow.Listener, ResizableComponent.Listener
{
	// Frame Constants
	private String title = "Ad Blocker The Game";
	private String subtitle = "Main Menu";

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
		add(makeJPanel());
		
		add(makeTitlePanel());


		add(makeBackgroundLabel());
		playBackgroundMusic();



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
	}

	private JPanel makeJPanel()
	{
		int w = 682, h = 480;
		JPanel menu = new JPanel(new GridLayout(4, 1));
		menu.setBounds((width - w) / 2, 500, w, h);
		menu.setBackground(new Color(0, 0, 0, 0));
		menu.add(makeNewGameButton(w, h));
		return menu;
	}

	private JPanel makeTitlePanel()
	{
		int w = 682, h = 480;
		JPanel title = new JPanel(null);
		title.setBounds((width - w) / 2, 500, w, h);
		title.setBackground(new Color(0, 0, 0, 0));
		title.add(makeTitleLabel(w, h));
		title.add(makeSubtitleLabel(w, h));
		title.add(makeYoutubeLabel(w, h));
		return title;
	}

	private JButton makeNewGameButton(int w, int h)
	{
		JButton newGamebtn = new JButton("NEW GAME");
		newGamebtn.setIcon(ImageUtils.getImageIcon("res/menu/youtube.png", w, h/4));
		

		//newGamebtn.setBackground(new Color(0, 0, 0, 0));
		newGamebtn.setBorderPainted(false);
		newGamebtn.setFocusPainted(false);
		newGamebtn.setContentAreaFilled(false);

		newGamebtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("New game clicked");
			}
		});
		return newGamebtn;
	}



	private JLabel makeTitleLabel()
	{
		int w = 500, h = 30;
		Font techno = GameUtils.getFont(Font.BOLD, 30);
		Color white = Color.WHITE;
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds((width-w)/2, 100, w, h);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(white);
		titleLabel.setFont(techno);
		return titleLabel;
	}

	private JLabel makeSubtitleLabel(int w, int h)
	{
		//int w = 300, h = 30;
		Font techno = GameUtils.getFont(Font.BOLD, 24);
		Color white = Color.WHITE;
		JLabel subtitleLabel = new JLabel(subtitle);
		subtitleLabel.setBounds((width-w)/2, 130, w, h);
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setForeground(white);
		subtitleLabel.setFont(techno);
		return subtitleLabel;
	}

	private JLabel makeYoutubeLabel(int w, int h) 
	{
		JLabel youtubeLabel = new JLabel();
		youtubeLabel.setBounds((width-w)/2, 50, w, h);
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
