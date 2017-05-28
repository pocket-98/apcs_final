// Main Menu Panel for Game

package ui;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

import input.SimpleMouseListener;
import utils.FileUtils;
import utils.ImageUtils;
import utils.SoundUtils;
import utils.GameUtils;
import ui.TransparentButton;
import ui.AdBlockerFrame;

public class MenuPanel extends JPanel
{

	// Panel Constants
	private String title;
	private String subtitle;
	private int width;
	private int height;
	private AdBlockerFrame frame;

	// GUI Items
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JLabel background;

	public MenuPanel(String t, String s, int w, int h, AdBlockerFrame f)
	{
		super(null);
		title = t;
		subtitle = s;
		width = w;
		height = h;
		frame = f;
		setBounds(0, 0, width, height);

		add(makeTitlePanel());
		add(makeButtonPanel());
		add(makeBackground());

		setVisible(true);
	}

	private JPanel makeTitlePanel()
	{
		int h = height/2-5;
		int w = 91*h/64;
		Font technoTitle = FileUtils.getFont(Font.BOLD, 48);
		Font technoSubtitle = FileUtils.getFont(Font.BOLD, 40);
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
		JButton newGame = new TransparentButton("NEW GAME");
		newGame.setFont(techno);
		newGame.setForeground(light);
		newGame.addMouseListener(new SimpleMouseListener()
		{
			public void mouseEntered(MouseEvent e) { newGame.setForeground(white); }
			public void mouseExited(MouseEvent e) { newGame.setForeground(light); }
			public void mouseClicked(MouseEvent e) { newGame(); }
		});

		// Continue Game Button
		JButton cont = new TransparentButton("CONTINUE");
		cont.setFont(techno);
		cont.setForeground(light);
		cont.addMouseListener(new SimpleMouseListener()
		{
			public void mouseEntered(MouseEvent e) { cont.setForeground(white); }
			public void mouseExited(MouseEvent e) { cont.setForeground(light); }
			public void mouseClicked(MouseEvent e) { contGame(); }
		});

		// Help Button
		JButton help = new TransparentButton("HELP");
		help.setFont(techno);
		help.setForeground(light);
		help.addMouseListener(new SimpleMouseListener()
		{
			public void mouseEntered(MouseEvent e) { help.setForeground(white); }
			public void mouseExited(MouseEvent e) { help.setForeground(light); }
			public void mouseClicked(MouseEvent e) { showHelp(); }
		});


		// Exit Button
		JButton exit = new TransparentButton("EXIT");
		exit.setFont(techno);
		exit.setForeground(light);
		exit.addMouseListener(new SimpleMouseListener()
		{
			public void mouseEntered(MouseEvent e) { exit.setForeground(white); }
			public void mouseExited(MouseEvent e) { exit.setForeground(light); }
			public void mouseClicked(MouseEvent e) { frame.closed(); }
		});


		buttonPanel.add(newGame);
		//buttonPanel.add(cont);
		buttonPanel.add(help);
		buttonPanel.add(exit);

		buttonPanel.setVisible(true);
		return buttonPanel;
	}

	public void newGame()
	{
		frame.createGame();
	}

	public void contGame()
	{
		frame.pickGame();
	}

	public void showHelp()
	{
		frame.showHelp();
	}

	private JLabel makeBackground()
	{
		background = new JLabel();
		background.setBounds(0, 0, width, height);
		background.setIcon(ImageUtils.getImageIcon("res/menu/background.png", width, height));
		return background;
	}

	public void setSize(int w, int h)
	{
		super.setSize(w, h);
		width = w;
		height = h;
	}

}
