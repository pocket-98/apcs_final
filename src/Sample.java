// Sample JFrame stuff

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sample extends JFrame {
	private final JButton b = new JButton();
	private final JButton quit = new JButton();

	public Sample() {
		super();
		this.setTitle("HarambeSopp");
		this.getContentPane().setLayout(null);
		this.setBounds(100, 100, 300, 200);
		this.add(makeButton());
		this.add(makeQuit());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JButton makeButton() {
		b.setText("Click me!");
		b.setBounds(60, 40, 70, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(b, "Hello World!");
			}
		});
		return b;
	}

	private JButton makeQuit() {
		quit.setText("Quit");
		quit.setBounds(120, 40, 70, 30);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return quit;
	}

	public static void main(String[] args) {
		new Sample();
    }
}
