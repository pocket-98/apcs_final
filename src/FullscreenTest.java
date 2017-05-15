// Test Fullscreen JFrame

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import window.ClosableWindow;

public class FullscreenTest extends JFrame implements window.ClosableWindow.Listener
{
	private final JButton b = new JButton();
	private final JButton quit = new JButton();

	public FullscreenTest()
	{
		super();
		setTitle("Fullscreen Test");
		getContentPane().setLayout(null);
		setBounds(100, 100, 300, 200);

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		add(makeButton());
		add(makeQuit());

		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JButton makeButton()
	{
		b.setText("Click me!");
		b.setBounds(60, 40, 100, 30);
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(b, "Hello World!");
			}
		});
		return b;
	}

	private JButton makeQuit()
	{
		quit.setText("Quit");
		quit.setBounds(60, 80, 100, 30);
		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				close();
			}
		});
		return quit;
	}

	public void close()
	{
		System.out.println("Closing");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		new FullscreenTest();
    }
}
