// Main Game Panel

package ui;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

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
import game.GameThread;
import game.gameelement.Player;
import game.gameelement.EnemyBank;
import game.gameelement.GameLevel;
import game.gameelement.GameScore;
import game.gameelement.GameEnemyIndicator;
import game.gameelement.GameFPSCounter;
import game.gameelement.GameBackground;
import game.gameelement.PauseMenu;

public class GamePanel extends JPanel implements Mouse.Listener, Keyboard.Listener, EnemyBank.Listener
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

	// Virtual Graphics Buffers
	private BufferedImage buffer;
	private Graphics vg;
	private GameThread gameThread;

	// Game Elements
	private Player player;
	private EnemyBank enemyBank;
	private GameLevel level;
	private GameScore score;
	private GameEnemyIndicator enemyIndicator;
	private GameFPSCounter fpsCounter;
	private GameBackground bg;
	private PauseMenu pauseMenu;

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

		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		vg = buffer.getGraphics();

		makePlayer();
		makeEnemyBank();
		makeLevel();
		makeScore();
		makeEnemyIndicator();
		makeFPSCounter();
		makeBackground();
		makePauseMenu();

		playBackgroundMusic();

		setBounds(0, 0, width, height);
		setFocusable(true);
		setVisible(true);

		startGameThread();
	}

	public void makePlayer()
	{
		player = new Player(res.path()+res.player(), width/16, height/9);
		player.setMinX(width/3);
		player.setMaxX(29*width/48);
		player.setMinY(height/8);
		player.setMaxY(15*height/18);
		player.setAcceleration(60.0/GameConstants.MAX_FPS);
		player.setFriction(0.1);
	}

	public void makeEnemyBank()
	{
		int playerRange = player.getHeight()+player.getMaxY()-player.getMinY();
		enemyBank = new EnemyBank(res.path(), res.ads(), width, playerRange, 100, this);
		enemyBank.setY(player.getMinY());
		enemyBank.setEnemyVelocity(60.0/GameConstants.MAX_FPS, 400.0/GameConstants.MAX_FPS);

	}

	public void makeLevel()
	{
		level = new GameLevel(res.level(), res.name());
		level.setBounds(50, height/60, width/3, height/12);
		level.setFont(FileUtils.getFont(Font.PLAIN, height/24));
		level.setForeground(GameConstants.LEVEL_COLOR);
		level.setHorizontalAlignment(SwingConstants.LEFT);
		add(level);
	}

	public void makeScore()
	{
		score = new GameScore(save);
		score.setBounds(width/3, 0, width/3, height/12);
		score.setFont(FileUtils.getFont(Font.BOLD, height/18));
		score.setForeground(GameConstants.SCORE_COLOR);
		score.setHorizontalAlignment(SwingConstants.CENTER);
		add(score);
	}

	public void makeEnemyIndicator()
	{
		enemyIndicator = new GameEnemyIndicator(enemyBank);
		enemyIndicator.setBounds(2*width/3, 0, width/3-50, height/12);
		enemyIndicator.setFont(FileUtils.getFont(Font.BOLD, height/24));
		enemyIndicator.setForeground(GameConstants.ENEMY_INDICATOR_COLOR);
		enemyIndicator.setHorizontalAlignment(SwingConstants.RIGHT);
		add(enemyIndicator);
	}

	public void makeFPSCounter()
	{
		fpsCounter = new GameFPSCounter(null);
		fpsCounter.setBounds(50, 7*height/8, width/3, height/8);
		fpsCounter.setFont(FileUtils.getFont(Font.BOLD, height/24));
		fpsCounter.setForeground(GameConstants.FPS_COLOR);
		fpsCounter.setHorizontalAlignment(SwingConstants.LEFT);
		add(fpsCounter);
	}

	public void makeBackground()
	{
		bg = new GameBackground(res.path()+res.bg(), width, height);
	}

	public void makePauseMenu()
	{
		pauseMenu = new PauseMenu(width, height/2)
		{
			public void backButtonPressed()
			{
				endGame(true);
			}
		};
		pauseMenu.setLocation(0, height/6);
		pauseMenu.setLabelFont(FileUtils.getFont(Font.BOLD, height/3));
		add(pauseMenu);
	}

	public void startGameThread()
	{
		gameThread = new GameThread(GameConstants.MAX_FPS)
		{
			public void paint()
			{
				checkCheatCodes();
				move();
				repaint();
			}
		};
		fpsCounter.setGameThread(gameThread);
		gameThread.start();
	}

	/**************************************************
	 *               Paint/Logic Methods              *
	 **************************************************/

	public void enemyBlocked()
	{
		save.changeScore(200);
		SoundUtils.getAudioClip("res/lol/airhorn.mp3").play();
	}

	public void enemyAttacked()
	{
		SoundUtils.getAudioClip("res/lol/buzzer.mp3").play();
	}

	public void move()
	{
		checkArrowKeys();
		enemyBank.move();
		player.move();
	}

	public void paint(Graphics g)
	{
		if (gameThread.isRunning())
		{
			paintGameElements();
		}
		g.drawImage(buffer, 0, 0, null);
	}

	public void paintGameElements()
	{
		bg.paint(vg);
		enemyBank.paint(vg);
		player.paint(vg);
		super.paintChildren(vg);
	}

	/**************************************************
	 *                Game State Methods              *
	 **************************************************/

	public void pauseGame()
	{
		gameThread.resetFPS();
		gameThread.kill();
		pauseMenu.setVisible(true);
		paintGameElements();
		repaint();
	}

	public void continueGame()
	{
		pauseMenu.setVisible(false);
		startGameThread();
	}

	public void endGame(boolean showMenu)
	{
		stopBackgroundMusic();
		gameThread.resetFPS();
		gameThread.kill();
		pauseMenu.setVisible(false);
		if (showMenu)
		{
			frame.endGame();
		}
	}

	/**************************************************
	 *              Mouse/Keyboard Methods            *
	 **************************************************/

	public void mousePressed(int x, int y, int button) {}

	public void mouseReleased(int x, int y, int button) {}

	public void mouseClicked(int x, int y, int button)
	{
		
	}

	public void mouseMoved(int x, int y, int button)
	{
		if (gameThread.isRunning())
		{
			save.changeScore(-50);
		}
	}

	public void mouseDragged(int x, int y, int button) {}

	public void mouseEntered(int x, int y, int button) {}

	public void mouseExited(int x, int y, int button) {}

	public void mouseWheeled(int dist) {}

	public void keyPressed(Key k)
	{
		if (k.equals(Key.KEY_ESCAPE))
		{
			if (gameThread.isRunning())
			{
				pauseGame();
			}
			else
			{
				continueGame();
			}
		}
	}

	public void keyReleased(Key k) {}

	public void keyTyped(Key k) {}

	public void checkArrowKeys()
	{
		if (gameThread.isRunning())
		{
			if (keyboard.isPressed(Key.KEY_UP))
			{
				player.increaseVelocity();
			}

			if (keyboard.isPressed(Key.KEY_DOWN))
			{
				player.decreaseVelocity();
			}

			if (keyboard.isPressed(Key.KEY_LEFT))
			{
				player.jumpLeft();
			}

			if (keyboard.isPressed(Key.KEY_RIGHT))
			{
				player.jumpRight();
			}

			if (keyboard.isPressed(Key.KEY_SPACE))
			{
				enemyBank.spawn();
			}
		}
	}
	public void checkCheatCodes()
	{
		// cheat codes (alt + shift + w,a,s,d)
		if (keyboard.isPressed(Key.KEY_SHIFT) && keyboard.isPressed(Key.KEY_ALT))
		{
			if (keyboard.isPressed(Key.KEY_W))
			{
				save.changeScore(5000);
				System.out.println("Hacked Score: " + save.getScore());
			}
			if (keyboard.isPressed(Key.KEY_A))
			{
				save.changeLevel(-1);
				System.out.println("Hacked Level: " + save.getLevel());
				endGame(false);
				frame.startGame(save);
			}
			if (keyboard.isPressed(Key.KEY_S))
			{
				save.changeScore(-5000);
				System.out.println("Hacked Score: " + save.getScore());
			}
			if (keyboard.isPressed(Key.KEY_D))
			{
				save.changeLevel(1);
				System.out.println("Hacked Level: " + save.getLevel());
				endGame(false);
				frame.startGame(save);
			}
			if (keyboard.isPressed(Key.KEY_B))
			{
				player.setImage("res/lol/barnwell.png", player.getWidth(), player.getHeight());
				System.out.println("Hacked Player: " + "Barnwell");
			}
		}
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
		int level = 1;
		int score = 0;
		AdBlockerFrame f = new AdBlockerFrame();
		f.startGame(new SaveFile(level, score));
	}

}
