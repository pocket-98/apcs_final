// Main Game Panel

package ui;

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.MouseEvent;

import input.Mouse;
import utils.FileUtils;
import utils.ImageUtils;
import utils.GameUtils;
import ui.AdBlockerFrame;

public class GamePanel extends JPanel
{

	// Panel Constants
	private int width;
	private int height;
	//private AdBlockerFrame frame;
	private JFrame frame;

	// GUI Items
	private Graphics g;

	//public GamePanel(int w, int h, AdBlockerFrame f)
	public GamePanel(int w, int h, JFrame f)
	{
		super(null);
		width = w;
		height = h;
		frame = f;
		setBounds(0, 0, width, height);
		setVisible(true);
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.BLACK);
		g.fillRect(30, 30, 50, 50);
	}

	public void setSize(int w, int h)
	{
		super.setSize(w, h);
		width = w;
		height = h;
	}

	public static void main(String[] args)
	{
		int w = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		JFrame f = new JFrame();
		f.getContentPane().setLayout(null);
		f.add(new GamePanel(w, h, f));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);
		f.setVisible(true);
	}

}