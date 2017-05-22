// Main Menu for Game

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.io.File;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import window.ClosableWindow;
import window.ResizableComponent;
import image.ImageUtils;
import game.GameUtils;

public class Menu extends JFrame implements ClosableWindow.Listener, ResizableComponent.Listener
{
	// Frame Constants
	private int width;
	private int height;
	private String title = "Ad Blocker The Game";
	private String subtitle = "Main Menu";

	// GUI Items
	private JLabel youtubeLabel;
	private JLabel titleLabel;
	private JLabel subtitleLabel;
	private JLabel backgroundLabel;

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
		
		add(makeTitleLabel());
		add(makeSubtitleLabel());
		add(makeYoutubeLabel());
		add(makeBackgroundLabel());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
	}

	private JLabel makeTitleLabel()
	{
		int w = 500, h = 30;
		Font techno = GameUtils.getFont(Font.BOLD, 30);
		Color white = Color.WHITE;
		titleLabel = new JLabel(title);
		titleLabel.setBounds((width-w)/2, 100, w, h);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(white);
		titleLabel.setFont(techno);
		return titleLabel;
	}

	private JLabel makeSubtitleLabel()
	{
		int w = 300, h = 30;
		Font techno = GameUtils.getFont(Font.BOLD, 24);
		Color white = Color.WHITE;
		subtitleLabel = new JLabel(subtitle);
		subtitleLabel.setBounds((width-w)/2, 130, w, h);
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setForeground(white);
		subtitleLabel.setFont(techno);
		return subtitleLabel;
	}

	private JLabel makeYoutubeLabel() 
	{
		int w = 682, h = 480;
		youtubeLabel = new JLabel();
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