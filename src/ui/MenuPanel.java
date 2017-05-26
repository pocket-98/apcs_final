// Main Menu Panel for Game

package ui;

import javax.swing.SwingConstants;
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
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

import utils.FileUtils;
import utils.ImageUtils;
import utils.SoundUtils;
import utils.GameUtils;
import input.SimpleMouseListener;
import ui.TrannyButton;


public class MenuPanel extends JPanel
{
	// Frame Constants
	private String title;
	private String subtitle;
	private int width;
	private int height;

	// GUI Items
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JLabel background;

	// Sound Items
	private AudioClip music;

	public MenuPanel(String t, String s, int w, int h)
	{
		super(null);
		title = t;
		subtitle = s;
		width = w;
		height = h;
		setBounds(0, 0, width, height);

		add(makeTitlePanel());
		add(makeButtonPanel());
		add(makeBackground());

		playBackgroundMusic();
		setVisible(true);
	}

	private JPanel makeTitlePanel()
	{
		int h = height/2-5;
		int w = 91*h/64;
		Font technoTitle = FileUtils.getFont(Font.BOLD, 48);
		Font technoSubtitle = FileUtils.getFont(Font.BOLD, 36);
		Color white = Color.WHITE;

		titlePanel = new JPanel(null);
		titlePanel.setBounds((width - w) / 2, 15, w, h);
		titlePanel.setOpaque(false);

		// Title Label
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(0, 30, w, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(white);
		titleLabel.setFont(technoTitle);

		// Subtitle Label
		JLabel subtitleLabel = new JLabel(subtitle);
		subtitleLabel.setBounds(0, h-60, w, 30);
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setForeground(white);
		subtitleLabel.setFont(technoSubtitle);

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

	private JPanel makeButtonPanel()
	{
		int h = height/3;
		int w = 3*h/2;

		Font techno = FileUtils.getFont(Font.PLAIN, 30);
		Color white = Color.WHITE;
		Color light = new Color(200, 200, 200);

		buttonPanel = new JPanel(new GridLayout(4, 1));
		buttonPanel.setBounds((width-w)/2, 5*height/9, w, h);
		buttonPanel.setOpaque(false);

		// New Game Button
		//ImageIcon yt = ImageUtils.getImageIcon("res/menu/youtube.png", w, h/4);
		JButton newGame = new TrannyButton("NEW GAME");
		newGame.setFont(techno);
		newGame.setForeground(light);
		newGame.setSize(w, h/4);
		newGame.addMouseListener(new SimpleMouseListener()
		{
			public void mouseEntered(MouseEvent e)
			{newGame.setForeground(white);}
			public void mouseExited(MouseEvent e)
			{newGame.setForeground(light);}
		});
		newGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("NEW GAME CLICKED");
			}
		});

		// Continue Game Button
		JButton cont = new TrannyButton("CONTINUE");
		cont.setFont(techno);
		cont.setForeground(light);
		cont.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("CONTINUE CLICKED");
			}
		});

		// Help Button
		JButton help = new TrannyButton("HELP");
		help.setFont(techno);
		help.setForeground(light);
		help.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("HELP CLICKED");
			}
		});

		// Exit Button
		JButton exit = new TrannyButton("EXIT");
		exit.setFont(techno);
		exit.setForeground(light);
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("EXIT CLICKED");
				System.exit(0);
			}
		});

		buttonPanel.add(newGame);
		buttonPanel.add(cont);
		buttonPanel.add(help);
		buttonPanel.add(exit);

		buttonPanel.setVisible(true);
		return buttonPanel;
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

	public void setSize(int w, int h)
	{
		super.setSize(w, h);
		width = w;
		height = h;
	}

	public static void warningMsgBox(String message)
	{
		String warning = "An unexpected error has occured. Not all resources were loaded.";
		JOptionPane.showMessageDialog(null, warning, message, JOptionPane.ERROR_MESSAGE);
	}

}
