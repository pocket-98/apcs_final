// Main Game Panel

package ui;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javafx.scene.media.AudioClip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;

import input.Mouse;
import input.Keyboard;
import input.Key;
import utils.FileUtils;
import utils.SoundUtils;
import utils.GameUtils;
import ui.AdBlockerFrame;

import game.GameConstants;
import game.SaveFile;
import game.LevelResources;
import game.GameElement;
import game.gameelement.GameBackground;
import game.gameelement.GameLevel;
import game.gameelement.GameScore;
import game.gameelement.GameEnemyIndicator;

public class GamePanel extends JPanel implements Mouse.Listener, Keyboard.Listener
{

	// Panel Constants
	private SaveFile save;
	private LevelResources res;
	private int width;
	private int height;
	private AdBlockerFrame frame;

	// Input Devices
	private Mouse mouse;
	private Keyboard keyboard;

	// GUI Items
	private BufferedImage buffer;
	private GameLevel level;
	private GameScore score;
	private GameEnemyIndicator enemyIndicator;
	private GameBackground bg;

	// Sound Items
	private AudioClip music;

	public GamePanel(SaveFile s, int w, int h, AdBlockerFrame f)
	{
		super(null);
		save = s;
		res = new LevelResources(s.getLevel());
		width = w;
		height = h;
		frame = f;

		mouse = new Mouse(this);
		keyboard = new Keyboard(this);

		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);
		addKeyListener(keyboard);

		// Virtual Graphics Buffer
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// Level
		level = new GameLevel(res.level(), res.name());
		level.setBounds(50, height/60, width, height/12);
		level.setFont(FileUtils.getFont(Font.PLAIN, height/24));
		level.setForeground(GameConstants.LEVEL_COLOR);
		level.setHorizontalAlignment(SwingConstants.LEFT);
		add(level);

		// Score
		score = new GameScore(save);
		score.setBounds(0, 0, width, height/12);
		score.setFont(FileUtils.getFont(Font.BOLD, height/18));
		score.setForeground(GameConstants.SCORE_COLOR);
		score.setHorizontalAlignment(SwingConstants.CENTER);
		add(score);

		// Enemy Indicator
		enemyIndicator = new GameEnemyIndicator(10);
		enemyIndicator.setBounds(0, 0, width-50, height/12);
		enemyIndicator.setFont(FileUtils.getFont(Font.BOLD, height/24));
		enemyIndicator.setForeground(GameConstants.ENEMY_INDICATOR_COLOR);
		enemyIndicator.setHorizontalAlignment(SwingConstants.RIGHT);
		add(enemyIndicator);

		// Background
		bg = new GameBackground(res.path()+res.bg(), width, height);

		playBackgroundMusic();

		setBounds(0, 0, width, height);
		setFocusable(true);
		setVisible(true);
	}

	/**************************************************
	 *                  Paint Methods                 *
	 **************************************************/

	public void paintComponent(Graphics g)
	{		
		Graphics vg = buffer.getGraphics();

		bg.paint(vg);
		// paint level and score
		super.paintChildren(vg);

		vg.dispose();
		g.drawImage(buffer, 0, 0, null);
	};

	/**************************************************
	 *              Mouse/Keyboard Methods            *
	 **************************************************/

	public void mousePressed(int x, int y, int button)
	{
		
	}

	public void mouseReleased(int x, int y, int button)
	{
		
	}

	public void mouseClicked(int x, int y, int button)
	{
		
	}

	public void mouseMoved(int x, int y, int button)
	{
		save.changeScore(50);
		repaint();
	}

	public void mouseDragged(int x, int y, int button)
	{
		
	}

	public void mouseEntered(int x, int y, int button)
	{
		
	}

	public void mouseExited(int x, int y, int button)
	{
		
	}

	public void mouseWheeled(int dist)
	{
		
	}

	public void keyPressed(Key k)
	{
		save.changeScore(-200);
		if (k.equals(Key.KEY_ESCAPE))
		{
			frame.closed();
		}
		else if (k.equals(Key.KEY_SPACE))
		{
			enemyIndicator.changeNumEnemies(-1);
		}
		repaint();
	}

	public void keyReleased(Key k)
	{

	}

	public void keyTyped(Key k)
	{

	}

	/**************************************************
	 *                 Helper Methods                 *
	 **************************************************/

	private void playBackgroundMusic()
	{
		music = SoundUtils.getAudioClip(res.path() + res.music());
		music.setCycleCount(AudioClip.INDEFINITE);
		music.play();
	}

	private void stopBackgroundMusic()
	{
		music.stop();
	}

	/**************************************************
	 *                   Main Method                  *
	 **************************************************/

	public static void main(String[] args)
	{
		int level = 2;
		int score = 0;
		AdBlockerFrame f = new AdBlockerFrame();
		f.getContentPane().removeAll();
		f.startGame(new SaveFile(level, score));
	}

}
