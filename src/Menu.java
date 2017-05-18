import window.ClosableWindow;
import window.ResizableComponent;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Menu extends JFrame implements ClosableWindow.Listener, ResizableComponent.Listener
{
	// GUI Items
	private JLabel titleLabel = new JLabel("Ad Blocker The Game ");
	private int width;
	private int height;

	// Images
	private BufferedImage BackgroundImage;
	private String youTubePath = "E:\\apcs_final\\res\\menu\\youtube.png";

	// Messagebox
	private String warningMessage = "An unexpected error has occured. Not all resources were loaded.";

	public Menu()
	{
		super();
		getContentPane().setLayout(null);
		setTitle("Ad Blocker The Game | Main Menu");
		resized();
		setSize(width, height);

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		ResizableComponent rs = new ResizableComponent(this);
		addComponentListener(rs);
		
		add(makeTitleLabel());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
	}

	private JLabel makeTitleLabel() 
	{
		int w = 682, h = 480;

		titleLabel.setBounds((width - w) / 2, 50, w, h);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setIcon(new ImageIcon(getImage(youTubePath, w, h)));
		return titleLabel;
	}

	public static void warningMsgBox(String message, String title)
	{
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

	public Image getImage(String path, int width, int height)
	{
		Image scaled;
		BufferedImage image;
		Graphics drawim;
		File saved;

		BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		try
		{
			image = ImageIO.read(new File(path)); 
			//System.out.println(this.getClass().getCanonicalName());
			scaled = image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			drawim = finalImage.getGraphics();
			drawim.drawImage(scaled, 0, 0, this);
		}
		catch(IOException e)
		{
			warningMsgBox(warningMessage, "Error");
		}

		return finalImage;
	}

	public void resized()
	{
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
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