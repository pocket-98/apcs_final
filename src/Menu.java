import window.ClosableWindow;
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
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Menu extends JFrame implements ClosableWindow.Listener
{
	// GUI Items
	private JLabel titleLabel = new JLabel("Ad Blocker The Game ");

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

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		add(makeTitleLabel());

		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	}

	private JLabel makeTitleLabel()
	{
		int width = 1280, height = 320;

		titleLabel.setBounds(100, 100, width, height);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setIcon(new ImageIcon(getImage(youTubePath, width, height)));
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

		BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		try
		{
			image = ImageIO.read(new File(path)); 
			System.out.println(this.getClass().getCanonicalName());
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

	public void close()
	{
		System.out.println("Closing");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		Menu menu = new Menu();
	}

}