// Help Panel for Game

package ui;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import input.SimpleMouseListener;
import utils.FileUtils;
import utils.ImageUtils;
import utils.GameUtils;
import ui.AdBlockerFrame;
import ui.TransparentButton;

public class HelpPanel extends JPanel
{

	// Panel Constants
	private String title;
	private String subtitle;
	private int width;
	private int height;
	private AdBlockerFrame frame;

	// GUI Items
	private JLabel titleLabel;
	private JLabel subtitleLabel;
	private JLabel helpLabel;
	private JButton backButton;
	private JLabel background;

	public HelpPanel(String t, String s, int w, int h, AdBlockerFrame f)
	{
		super(null);
		title = t;
		subtitle = s;
		width = w;
		height = h;
		frame = f;
		setBounds(0, 0, width, height);

		add(makeTitleLabel());
		add(makeSubtitleLabel());
		add(makeHelpLabel());
		add(makeBackButton());
		add(makeBackground());

		setVisible(true);
	}

	private JLabel makeTitleLabel()
	{
		titleLabel = new JLabel(title);
		titleLabel.setBounds(0, 30, width, 60);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(FileUtils.getFont(Font.BOLD, 48));
		return titleLabel;
	}

	private JLabel makeSubtitleLabel()
	{
		subtitleLabel = new JLabel("Help");
		subtitleLabel.setBounds(0, 80, width, 60);
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setForeground(Color.WHITE);
		subtitleLabel.setFont(FileUtils.getFont(Font.BOLD, 40));
		return subtitleLabel;
	}

	private JLabel makeHelpLabel()
	{
		int w = 3*width/4;
		int h = 2*height/3;
		String html = FileUtils.getResourceContent("res/menu/help.html");
		subtitleLabel = new JLabel(html);
		subtitleLabel.setBounds((width-w)/2, 150, w, h);
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setForeground(Color.WHITE);
		subtitleLabel.setFont(FileUtils.getFont(Font.BOLD, 20));
		return subtitleLabel;
	}

	private JButton makeBackButton()
	{
		int w = width/4;
		int h = height/6;
		backButton = new TransparentButton("Back");
		backButton.setBounds((width-w)/2, 3*height/4, w, h);
		backButton.setFont(FileUtils.getFont(Font.PLAIN, 30));
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.menu();
			}
		});
		return backButton;
	}

	private JLabel makeBackground()
	{
		int level = 1;
		background = new JLabel();
		background.setBounds(0, 0, width, height);
		background.setIcon(GameUtils.getLevelBackgroundIcon(level, width, height));
		return background;
	}

	public void setSize(int w, int h)
	{
		super.setSize(w, h);
		width = w;
		height = h;
	}

}
