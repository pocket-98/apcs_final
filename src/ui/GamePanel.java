// Main Game Panel

package ui;

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javafx.scene.media.AudioClip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;

import input.Mouse;
import input.Keyboard;
import input.Key;
import utils.FileUtils;
import utils.ImageUtils;
import utils.SoundUtils;
import utils.GameUtils;
import game.LevelResources;
import ui.AdBlockerFrame;

public class GamePanel extends JPanel implements Mouse.Listener, Keyboard.Listener
{

	// Panel Constants
	private LevelResources res;
	private int level;
	private int width;
	private int height;
	//private AdBlockerFrame frame;
	private JFrame frame;

	// Input Devices
	private Mouse mouse;
	private Keyboard keyboard;

	// GUI Items
	private BufferedImage buffer;
	private BufferedImage player;
	private BufferedImage background;

	// Sound Items
	private AudioClip music;

	//public GamePanel(int w, int h, AdBlockerFrame f)
	public GamePanel(int l, int w, int h, JFrame f)
	{
		super(null);
		level = l;
		width = w;
		height = h;
		frame = f;

		res = new LevelResources(level);

		mouse = new Mouse(this);
		keyboard = new Keyboard(this);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);
		addKeyListener(keyboard);

		playBackgroundMusic();

		setBounds(0, 0, width, height);
		setVisible(true);
	}

	/**************************************************
	 *                  Paint Methods                 *
	 **************************************************/

	public void paint(Graphics g)
	{
		super.paint(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.BLACK);
		g.fillRect(30, 30, 50, 50);
	}

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
		int w = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int l = 1;
		JFrame f = new JFrame();
		f.getContentPane().setLayout(null);
		f.add(new GamePanel(l, w, h, f));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);
		f.setVisible(true);
	}

}