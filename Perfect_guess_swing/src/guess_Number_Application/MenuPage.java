package guess_Number_Application;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import javax.swing.JTable;

public class MenuPage {

	static JFrame MenueFrame;
	static int bound;
	static int randomNumber;
	static Random random = new Random();
	private JTextField BoundField;
	static JTable table;
    private static DefaultTableModel tableModel;
    static int average;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					MenuPage window = new MenuPage();
					window.MenueFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static void getScore() {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
        	
        	String dbPath = "C:\\Sqlite\\Game.db"; // Replace with your path
	        String connectionString = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(connectionString);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Player ORDER BY Score limit 6");
            tableModel.setRowCount(0); // Clear existing rows
            while (rs.next()) {
                String name = rs.getString("PlayerName");
                int score = rs.getInt("Score");
                String date = rs.getString("date");
                String time = rs.getString("time");
                tableModel.addRow(new Object[]{name, score, date, time});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(MenueFrame, "Failed to retrieve high scores.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void getAverage() {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
        	
        	String dbPath = "C:\\Sqlite\\Game.db"; // Replace with your path
	        String connectionString = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(connectionString);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT AVG(Score) as avg from Player");
            if(rs.next()) {
            	average = rs.getInt("avg");
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	private void initialize() {
		MenueFrame = new JFrame();
		JButton HiscoreButton = new JButton("Hiscore");
		JButton AverageButton = new JButton("Average");
		
		JButton ReturnButton = new JButton("Return");
		JButton ExitButton = new JButton("Exit");
		JLayeredPane layeredPane = new JLayeredPane();
		JPanel fristpanel = new JPanel();
		JLabel helloLabel = new JLabel("");
		JLabel BoundLabel = new JLabel("Enter Bound");
		JButton StartButton = new JButton("Start");
		JPanel HiscorePanel = new JPanel();
		String Column [] = new String[]{"Name", "Score", "Date", "Time"};
		tableModel = new DefaultTableModel(Column, 0);
		table = new JTable(tableModel);
		JLabel AverageofScore = new JLabel("");
		JPanel AveragePanel = new JPanel();
		
		MenueFrame.getContentPane().setBackground(new Color(204, 255, 204));
		MenueFrame.getContentPane().setLayout(null);
		
		
		HiscoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(HiscorePanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				MenuPage.getScore();
			}
		});
		HiscoreButton.setForeground(new Color(0, 0, 0));
		HiscoreButton.setBackground(new Color(204, 51, 102));
		HiscoreButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		HiscoreButton.setBounds(98, 260, 109, 48);
		MenueFrame.getContentPane().add(HiscoreButton);
		
		
		AverageButton.setForeground(new Color(0, 0, 0));
		AverageButton.setBackground(new Color(204, 51, 102));
		AverageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(AveragePanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				MenuPage.getAverage();
				AverageofScore.setText("On an average players guess the number in "+average+" times");
			}
		});
		AverageButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		AverageButton.setBounds(324, 260, 109, 48);
		MenueFrame.getContentPane().add(AverageButton);
		
		ReturnButton.setForeground(new Color(0, 0, 0));
		ReturnButton.setBackground(new Color(204, 51, 102));
		ReturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(fristpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		ReturnButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		ReturnButton.setBounds(98, 339, 109, 48);
		MenueFrame.getContentPane().add(ReturnButton);
		
		
		ExitButton.setForeground(new Color(0, 0, 0));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ExitButton.setBackground(new Color(204, 51, 102));
		ExitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		ExitButton.setBounds(324, 339, 109, 48);
		MenueFrame.getContentPane().add(ExitButton);
		
		
		layeredPane.setBounds(31, 30, 455, 210);
		MenueFrame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		fristpanel.setBackground(new Color(204, 255, 204));
		layeredPane.add(fristpanel, "name_2254288427299800");
		fristpanel.setLayout(null);
		
		
		helloLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setBounds(35, 21, 392, 91);
		fristpanel.add(helloLabel);
		helloLabel.setText("Hello "+Login.name+" !");
		
		
		BoundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BoundLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		BoundLabel.setForeground(new Color(0, 0, 0));
		BoundLabel.setBounds(63, 122, 96, 52);
		fristpanel.add(BoundLabel);
		
		
		StartButton.setForeground(new Color(0, 0, 0));
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePage g = new GamePage();
				g.PlayingPage.setVisible(true);
				MenueFrame.dispose();
			}
		});
		StartButton.setEnabled(false);
		StartButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		StartButton.setBackground(new Color(204, 51, 102));
		StartButton.setBounds(316, 133, 85, 37);
		fristpanel.add(StartButton);
		
		BoundField = new JTextField();
		BoundField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = BoundField.getText();
		        try {
		            int boundInt = Integer.parseInt(text);
		            if(boundInt==0) {
		            	JOptionPane.showMessageDialog(MenueFrame, "Bound cannot be 0", "Invalid", JOptionPane.ERROR_MESSAGE);
		            	BoundField.setText("");
		            }
		            else {
		            	bound=boundInt;
		            	StartButton.setEnabled(true);
		            	randomNumber = random.nextInt(boundInt);
		            }  
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(MenueFrame, "Please give proper input", "Invalid", JOptionPane.ERROR_MESSAGE);
		            BoundField.setText("");
		        }
			}
		});
		BoundField.setHorizontalAlignment(SwingConstants.CENTER);
		BoundField.setBounds(181, 133, 96, 37);
		fristpanel.add(BoundField);
		BoundField.setColumns(10);
		
		
		HiscorePanel.setBackground(new Color(204, 255, 204));
		layeredPane.add(HiscorePanel, "name_2255850656102100");
		HiscorePanel.setLayout(null);
		
		table.setBackground(new Color(204, 255, 204));
		table.setFont(new Font("Times New Roman", Font.BOLD, 13));
		table.setForeground(new Color(0, 0, 0));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
        table.setBounds(42, 55, 403, 93);
        HiscorePanel.add(table);
        
        
        AveragePanel.setBackground(new Color(204, 255, 204));
        layeredPane.add(AveragePanel, "name_2370373395413800");
        AveragePanel.setLayout(null);
        
        
        AverageofScore.setFont(new Font("Times New Roman", Font.BOLD, 15));
        AverageofScore.setHorizontalAlignment(SwingConstants.CENTER);
        AverageofScore.setBounds(36, 60, 390, 85);
        AveragePanel.add(AverageofScore);
        
		
		MenueFrame.setBounds(100, 100, 531, 533);
		MenueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
