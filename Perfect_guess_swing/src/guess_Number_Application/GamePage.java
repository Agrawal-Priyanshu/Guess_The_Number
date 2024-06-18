package guess_Number_Application;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;

public class GamePage {

	JFrame PlayingPage;
	JTextField NumberInput;
	static int count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePage window = new GamePage();
					window.PlayingPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GamePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "resource", "null" })
	public static void insertPlayerData() {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    String sql = "INSERT or REPLACE INTO Player (PlayerName, Score, date, time) VALUES (?, ?, ?, ?)";
        
	    try {
	        // Replace with your actual database path and connection string
	    	String dbPath = "C:\\Sqlite\\Game.db"; // Replace with your path
	        String connectionString = "jdbc:sqlite:" + dbPath;
	        
	        // Connect to the database
	        conn = DriverManager.getConnection(connectionString);
	        stmt = conn.prepareStatement(sql);
	        
	        //To get current date and time in string;
	        LocalDateTime dt = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
            DateTimeFormatter df2 = DateTimeFormatter.ISO_LOCAL_TIME;
            String Date = dt.format(df);
            String Time = dt.format(df2);
	       
	        // Get player name from MenuPage (assuming it's stored there)
            String playerName = Login.name;
     
	        // Set values for placeholders in the statement
	        stmt.setString(1, playerName);
	        stmt.setInt(2, count);
	        stmt.setString(3, Date);
	        stmt.setString(4, Time);

	        // Execute the insert statement
	        stmt.executeUpdate();
	    } catch (SQLException ex) {
	    	
	    } finally {
	        // Close resources (connection and statement)
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                // handle potential exception
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                // handle potential exception
	            }
	        }
	    }
	}
	
	private void initialize() {
		PlayingPage = new JFrame();
		PlayingPage.getContentPane().setForeground(UIManager.getColor("Button.light"));
		PlayingPage.getContentPane().setBackground(new Color(204, 255, 204));
		PlayingPage.getContentPane().setLayout(null);
		
		JPanel PlayingPanel = new JPanel();
		PlayingPanel.setBorder(null);
		PlayingPanel.setBounds(96, 104, 322, 278);
		PlayingPage.getContentPane().add(PlayingPanel);
		PlayingPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel Northpanel = new JPanel();
		Northpanel.setBackground(new Color(255, 255, 255));
		Northpanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		PlayingPanel.add(Northpanel, BorderLayout.NORTH);
		Northpanel.setLayout(new BorderLayout(0, 0));
		
		JLabel TitleLabel = new JLabel("Number Guessing Game");
		TitleLabel.setVerticalAlignment(SwingConstants.TOP);
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		Northpanel.add(TitleLabel, BorderLayout.NORTH);
		
		JLabel Disc = new JLabel("");
		Disc.setText("Guess the number between 0 to "+MenuPage.bound);
		Disc.setHorizontalAlignment(SwingConstants.CENTER);
		Disc.setFont(new Font("Times New Roman",Font.PLAIN,12));
		Northpanel.add(Disc , BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		PlayingPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		NumberInput = new JTextField();
		NumberInput.setHorizontalAlignment(SwingConstants.CENTER);
		NumberInput.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		NumberInput.setBounds(107, 24, 96, 19);
		panel.add(NumberInput);
		NumberInput.setColumns(10);
		
		JLabel ResultDisplay = new JLabel("");
		ResultDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		ResultDisplay.setFont(new Font("Verdana", Font.BOLD, 10));
		ResultDisplay.setForeground(new Color(255, 0, 0));
		ResultDisplay.setBounds(43, 60, 248, 13);
		panel.add(ResultDisplay);
		
		JButton CheckButton = new JButton("Check");
		CheckButton.setEnabled(false);
		CheckButton.setBackground(new Color(204, 51, 102));
		CheckButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		CheckButton.setBounds(118, 83, 75, 41);
		panel.add(CheckButton);
		
		CheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int temp  = Integer.parseInt(NumberInput.getText());
				int input = temp;
				if(input>MenuPage.bound) {
					JOptionPane.showMessageDialog(PlayingPage, "Bound cannot be greater than bound", "Invalid", JOptionPane.ERROR_MESSAGE);
		            NumberInput.setText("");
				}
				else if(input<MenuPage.randomNumber) {
					count++;
					ResultDisplay.setText("Try again , Guess Higher");
				}
				else if(input>MenuPage.randomNumber) {
					count++;
					ResultDisplay.setText("Try again , Guess Lower");
				}
				else {
					count++;
					ResultDisplay.setText("Well Done you guesed it right in "+count +" times");
					ResultDisplay.setForeground(new Color(0,204, 0));
					CheckButton.setEnabled(false);
					GamePage.insertPlayerData();	
				}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(204, 51, 102));
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				count=0;
				MenuPage menu = new MenuPage();
				menu.MenueFrame.setVisible(true);
				PlayingPage.dispose();
			}
		});
		
		NumberInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = NumberInput.getText();
				if(temp.length()==0) {
					CheckButton.setEnabled(false);
				}
				else {
					CheckButton.setEnabled(true);
				}
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBack.setBounds(118, 141, 75, 41);
		panel.add(btnBack);
		
		PlayingPage.setBounds(500, 100, 531, 533);
		PlayingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
