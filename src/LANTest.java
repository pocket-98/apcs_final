// Test sending data over lan

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import window.ClosableWindow;
import lan.LANUtils;

public class LANTest extends JFrame implements ClosableWindow.Listener
{

	private JLabel hostLabel = new JLabel("Host: ");
	private JLabel portLabel = new JLabel("Port: ");
	private JTextField hostField = new JTextField(15);
	private JTextField portField = new JTextField(6);
	private JButton connectButton = new JButton("Connect");
	private JLabel connectLabel = new JLabel("");

	public LANTest()
	{
		super();
		getContentPane().setLayout(null);
		setTitle("LAN Test");
		setBounds(50, 50, 400, 300);

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		add(makeHostLabel());
		add(makeHostField());
		add(makePortLabel());
		add(makePortField());
		add(makeConnectButton());
		add(makeConnectLabel());

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JLabel makeHostLabel()
	{
		hostLabel.setBounds(60, 50, 50, 25);
		hostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		return hostLabel;
	}

	private JTextField makeHostField()
	{
		hostField.setBounds(120, 50, 200, 25);
		return hostField;
	}

	private JLabel makePortLabel()
	{
		portLabel.setBounds(60, 80, 50, 25);
		portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		return portLabel;
	}

	private JTextField makePortField()
	{
		portField.setBounds(120, 80, 200, 25);
		return portField;
	}

	private JButton makeConnectButton()
	{
		connectButton.setBounds(140, 120, 160, 30);
		connectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String host = hostField.getText();
				String port = portField.getText();
				connectLabel.setText("testing...");
				boolean active = testConnection(host, port);
				connectLabel.setText(active ? "host active" : "host not active");
			}
		});
		return connectButton;
	}

	private JLabel makeConnectLabel()
	{
		connectLabel.setBounds(140, 160, 160, 25);
		connectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return connectLabel;
	}

	public boolean testConnection(String host, String port)
	{
		int portInt;
		try
		{
			portInt = Integer.parseInt(port);
			boolean active = LANUtils.testHost(host, portInt);
			System.out.println(host + ":" + port + (active ? "" : " not" ) + " active");
			return active;
		}
		catch (Exception e)
		{
			System.out.println("port " + port + " invalid");
			return false;
		}
	}

	public void close()
	{
		System.out.println("Closing");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		LANTest l = new LANTest();
	}
}
