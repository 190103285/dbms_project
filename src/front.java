import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JTextField;

import oracle.jdbc.OracleTypes;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JList;

public class front {

	private JFrame frmGames;
	static Connection connection;
	private JTextField tf_genre;
	private JTextField tf_name;
	private JTextField tf_year;
	private JTextField tf_platform;
	byte[] game_image=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					front window = new front();
					window.frmGames.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String username = "system";
		String password = "Saya3011";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO testing_connection(id) "
					+ "VALUES (2)";
			Statement statement = connection.createStatement();
			int num = statement.executeUpdate(sql);
			
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public front() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGames = new JFrame();
		frmGames.getContentPane().setBackground(SystemColor.activeCaption);
		frmGames.setTitle("games");
		frmGames.setBackground(SystemColor.controlShadow);
		frmGames.setBounds(100, 100, 1014, 650);
		frmGames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGames.getContentPane().setLayout(null);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setForeground(SystemColor.activeCaption);
		horizontalBox.setBackground(SystemColor.activeCaption);
		horizontalBox.setBounds(862, 0, -857, 79);
		frmGames.getContentPane().add(horizontalBox);
		
		JLabel lblNewLabel = new JLabel("Genre");
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		lblNewLabel.setBounds(67, 321, 120, 29);
		frmGames.getContentPane().add(lblNewLabel);
		
		tf_genre = new JTextField();
		tf_genre.setBounds(261, 318, 186, 41);
		frmGames.getContentPane().add(tf_genre);
		tf_genre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		lblNewLabel_1.setBounds(67, 108, 94, 25);
		frmGames.getContentPane().add(lblNewLabel_1);
		
		tf_name = new JTextField();
		tf_name.setBounds(261, 108, 186, 41);
		frmGames.getContentPane().add(tf_name);
		tf_name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Year of release");
		lblNewLabel_2.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		lblNewLabel_2.setBounds(67, 248, 149, 29);
		frmGames.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Platform");
		lblNewLabel_3.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		lblNewLabel_3.setBounds(67, 181, 94, 21);
		frmGames.getContentPane().add(lblNewLabel_3);
		
		tf_year = new JTextField();
		tf_year.setBounds(261, 249, 186, 41);
		frmGames.getContentPane().add(tf_year);
		tf_year.setColumns(10);
		
		tf_platform = new JTextField();
		tf_platform.setBounds(261, 178, 186, 41);
		frmGames.getContentPane().add(tf_platform);
		tf_platform.setColumns(10);
		
		JButton btn_insert = new JButton("Insert");
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallableStatement src = null;
				String query = "{CALL INSERT_GAME(?,?,?,?)}";
				
				try {
					src = connection.prepareCall(query);
					
					src.setString(1, tf_name.getText());//name
					src.setString(2, tf_platform.getText());//platform
					src.setInt(3, Integer.parseInt(tf_year.getText()));//year
					src.setString(4, tf_genre.getText());//genre
					
					src.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "inserted");
					
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		
		btn_insert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_insert.setBounds(67, 441, 94, 29);
		frmGames.getContentPane().add(btn_insert);
		
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallableStatement src = null;
				String query = "{CALL UPDATE_GAME(?,?,?,?)}";
				
				try {
					src = connection.prepareCall(query);
					
					src.setString(1, tf_name.getText());//name
					src.setString(2, tf_platform.getText());//platform
					src.setInt(3, Integer.parseInt(tf_year.getText()));//year
					src.setString(4, tf_genre.getText());//genre
					
					src.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "updated");
					
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_update.setBounds(213, 441, 94, 29);
		frmGames.getContentPane().add(btn_update);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallableStatement src = null;
				String query = "{CALL DELETE_GAME(?,?,?,?)}";
				
				try {
					src = connection.prepareCall(query);
					
					src.setString(1, tf_name.getText());//name
					src.setString(2, tf_platform.getText());//platform
					src.setInt(3, Integer.parseInt(tf_year.getText()));//year
					src.setString(4, tf_genre.getText());//genre
					
					src.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "deleted");
					
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
			
		});
		
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_delete.setBounds(347, 441, 100, 29);
		frmGames.getContentPane().add(btn_delete);
		
		JLabel img1 = new JLabel("New label");
		img1.setBounds(590, 311, 111, 136);
		frmGames.getContentPane().add(img1);
		
		 try {
			 connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Saya3011");
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("select poster from catalogue where name = 'Wii Sports' ");
	            rs.next();
	            BufferedImage im = ImageIO.read(rs.getBinaryStream("poster"));
	            img1.setIcon(new ImageIcon(im));
	            img1.setPreferredSize(new Dimension(250, 100));
	            
	            
	            
	        } catch (Exception err) {
	            JOptionPane.showMessageDialog(null, err.getMessage());
	        }
		 JLabel img2 = new JLabel("New label");
	            img2.setBounds(813, 300, 111, 159);
	            frmGames.getContentPane().add(img2);
	            
	            try {
	   			 connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Saya3011");
	   	            Statement stmt = connection.createStatement();
	   	            ResultSet rs = stmt.executeQuery("select poster from catalogue where name = 'Super Mario Bros.' ");
	   	            rs.next();
	   	            BufferedImage im = ImageIO.read(rs.getBinaryStream("poster"));
	   	            img2.setIcon(new ImageIcon(im));
	   	            img2.setPreferredSize(new Dimension(250, 100));
	   	            
	   	            
	   	            
	   	        } catch (Exception err) {
	   	            JOptionPane.showMessageDialog(null, err.getMessage());
	   	        }
	            
	            JLabel img3 = new JLabel("New label");
	            img2.setBounds(794, 300, 130, 159);
	            frmGames.getContentPane().add(img2);
	            
	            JLabel img4 = new JLabel("New label");
	            img4.setBounds(590, 99, 108, 136);
	            frmGames.getContentPane().add(img4);
	            
	            try {
	   			 connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Saya3011");
	   	            Statement stmt = connection.createStatement();
	   	            ResultSet rs = stmt.executeQuery("select poster from catalogue where name = 'Mario Kart Wii' ");
	   	            rs.next();
	   	            BufferedImage im = ImageIO.read(rs.getBinaryStream("poster"));
	   	            img4.setIcon(new ImageIcon(im));
	   	            img4.setPreferredSize(new Dimension(250, 100));
	   	            
	   	            
	   	            
	   	            
	   	            
	   	        } catch (Exception err) {
	   	            JOptionPane.showMessageDialog(null, err.getMessage());
	   	        }
	            
	            JLabel img5 = new JLabel("New label");
	   	            img5.setBounds(782, 99, 111, 136);
	   	            frmGames.getContentPane().add(img5);
	   	            
	   	         try {
		   			 connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Saya3011");
		   	            Statement stmt = connection.createStatement();
		   	            ResultSet rs = stmt.executeQuery("select poster from catalogue where name = 'Wii Sports Resort' ");
		   	            rs.next();
		   	            BufferedImage im = ImageIO.read(rs.getBinaryStream("poster"));
		   	            img5.setIcon(new ImageIcon(im));
		   	            img5.setPreferredSize(new Dimension(250, 100));
		   	       
		   	            
		   	        } catch (Exception err) {
		   	            JOptionPane.showMessageDialog(null, err.getMessage());
		   	        }
	}
}
