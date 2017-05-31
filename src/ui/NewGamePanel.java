// Create New Game Panel

package ui;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import utils.FileUtils;
import utils.ImageUtils;
import game.GameConstants;
import game.SaveFile;
import ui.AdBlockerFrame;
import ui.TransparentButton;

public class NewGamePanel extends JPanel
{

	// Panel Constants
	private String title;
	private String subtitle;
	private int width;
	private int height;
	private AdBlockerFrame frame;

	// GUI Items
	private JLabel titleLabel;
	private TransparentButton backButton;
	private TransparentButton createGameButton;
	private JLabel background;

	public NewGamePanel(String t, String s, int w, int h, AdBlockerFrame f)
	{
		super(null);
		title = t;
		subtitle = s;
		width = w;
		height = h;
		frame = f;
		setBounds(0, 0, width, height);

		makeTitleLabel();
		makeCreateGameButton();
		makeBackButton();
		makeBackground();

		setVisible(true);
	}

	private void makeTitleLabel()
	{
		titleLabel = new JLabel(title + ": " + subtitle);
		titleLabel.setBounds(0, 30, width, 60);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(GameConstants.TEXT_COLOR);
		titleLabel.setFont(FileUtils.getFont(Font.BOLD, 48));
		add(titleLabel);
	}

	private void makeBackButton()
	{
		int w = width/4;
		int h = height/6;
		backButton = new TransparentButton("BACK")
		{
			public void onButtonClick() { frame.showMenu(); }
		};
		backButton.setBounds((width-w)/3, 3*height/4, w, h);
		backButton.setFont(FileUtils.getFont(Font.PLAIN, 30));
		add(backButton);
	}

	private void makeCreateGameButton()
	{
		int w = width/4;
		int h = height/6;
		createGameButton = new TransparentButton("CREATE GAME")
		{
			public void onButtonClick() { startGame(); }
		};
		createGameButton.setBounds(2*(width-w)/3, 3*height/4, w, h);
		createGameButton.setFont(FileUtils.getFont(Font.PLAIN, 30));
		add(createGameButton);
	}

	private void makeBackground()
	{
		background = new JLabel();
		background.setBounds(0, 0, width, height);
		background.setIcon(ImageUtils.getImageIcon("res/menu/background.png", width, height));
		add(background);
	}

	private void startGame()
	{
		frame.startGame(new SaveFile());
	}

}