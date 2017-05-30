// Holds actions too go back to menu on game pause

package game.gameelement;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import input.SimpleMouseListener;
import game.GameConstants;
import utils.FileUtils;
import ui.TransparentButton;

public abstract class PauseMenu extends JPanel
{

	private JLabel pauseLabel;
	private JButton backButton;

	public abstract void backButtonPressed();

	public PauseMenu(int w, int h)
	{
		super(null);
		setSize(w, h);

		makePauseLabel();
		makeBackButton();

		setBackground(GameConstants.PAUSE_BG_COLOR);

		setVisible(false);
	}

	public void setLabelFont(Font f)
	{
		pauseLabel.setFont(f);
	}

	public void makePauseLabel()
	{
		pauseLabel = new JLabel("PAUSE");
		pauseLabel.setForeground(GameConstants.PAUSE_FG_COLOR);
		pauseLabel.setBounds(0, 0, getWidth(), 2*getHeight()/3);
		pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(pauseLabel);
	}

	public void makeBackButton()
	{
		Color hover = GameConstants.BUTTON_HOVER_COLOR;
		Color nohover = GameConstants.BUTTON_COLOR;
		backButton = new TransparentButton("MAIN MENU");
		backButton.setFont(FileUtils.getFont(Font.BOLD, 36));
		backButton.setForeground(nohover);
		backButton.setBounds(getWidth()/3, 2*getHeight()/3, getWidth()/3, getHeight()/6);
		backButton.addMouseListener(new SimpleMouseListener()
		{
			public void mouseEntered(MouseEvent e) { backButton.setForeground(hover); }
			public void mouseExited(MouseEvent e) { backButton.setForeground(nohover); }
			public void mouseClicked(MouseEvent e) { backButtonPressed(); }
		});
		add(backButton);
	}

}
