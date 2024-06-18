package guess_Number_Application;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Login {

	JFrame LoginFrame;
	JTextField textField;
	static String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.LoginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//MainFrame
		LoginFrame = new JFrame();
		LoginFrame.getContentPane().setBackground(new Color(204, 255, 204));
		LoginFrame.setBounds(100, 100, 531, 533);
		LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginFrame.getContentPane().setLayout(null);
		
		//Title
		JLabel titleLabel = new JLabel("<html>Welcome to Perfect guess game</html>");
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setBounds(336, 107, 172, 133);
		LoginFrame.getContentPane().add(titleLabel);
		
		//Continue Button
		JButton ContinueButton = new JButton("Continue");
		ContinueButton.setToolTipText("Press enter after writing name in the field");
		ContinueButton.setEnabled(false);
		ContinueButton.setBackground(new Color(204, 51, 102));
		ContinueButton.setForeground(new Color(0, 0, 0));
		ContinueButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				MenuPage next = new MenuPage();
				next.MenueFrame.setVisible(true);
				LoginFrame.dispose();
			}
		});
		ContinueButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		ContinueButton.setBounds(367, 390, 105, 36);
		LoginFrame.getContentPane().add(ContinueButton);
		
		JLabel imageLabel_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/WelcomePageImage.png")).getImage().getScaledInstance(361,496,Image.SCALE_DEFAULT);
		imageLabel_1.setIcon(new ImageIcon(img));
		imageLabel_1.setBounds(-50, 10, 361, 496);
		LoginFrame.getContentPane().add(imageLabel_1);
		
		//Name TextField
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = textField.getText();
				if(name.length()!=0) {
					ContinueButton.setEnabled(true);
				}
				else if(name.length()==0) {
					ContinueButton.setEnabled(false);
				}
			}
		});
		textField.setBounds(338, 345, 157, 19);
		LoginFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel NameLabel = new JLabel("Enter Your Name");
		NameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		NameLabel.setBounds(356, 310, 128, 19);
		LoginFrame.getContentPane().add(NameLabel);
		
		
	}
}
