import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Cursor;


public class Main {

	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}
	protected static boolean isManager = false;
	private JButton buttonAdmin;
	private JButton buttonPersonnel;
	private Point buttonAdminLocation,buttonPersonnelLocation;
	private LoginPanel log;
	private Connection dbc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					break;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		dbc = makeConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(950, 650));
		frame.getContentPane().setBackground(new Color(47, 79, 79));
		frame.getContentPane().setLayout(null);
		
		buttonAdmin = new JButton("");
		buttonAdmin.setBounds(342, 235, 240, 60);
		buttonAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonAdmin.setIcon(Variables.Images.iconAdmin);
		buttonAdmin.setRolloverIcon(Variables.Images.iconAdminPressed);
		buttonAdmin.setPressedIcon(Variables.Images.iconAdminPressed);
		buttonAdmin.setFocusPainted(false);
		buttonAdmin.setBorderPainted(false);
		buttonAdmin.setContentAreaFilled(false);
		
		frame.getContentPane().add(buttonAdmin);
		
		buttonPersonnel = new JButton("");
		buttonPersonnel.setBounds(342, 315, 240, 60);
		buttonPersonnel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPersonnel.setIcon(Variables.Images.iconPersonnel);
		buttonPersonnel.setRolloverIcon(Variables.Images.iconPersonnelPressed);
		buttonPersonnel.setPressedIcon(Variables.Images.iconPersonnelPressed);
		buttonPersonnel.setFocusPainted(false);
		buttonPersonnel.setBorderPainted(false);
		buttonPersonnel.setContentAreaFilled(false);
		frame.getContentPane().add(buttonPersonnel);
		
		
		
		buttonAdminLocation = buttonAdmin.getLocation();
		buttonPersonnelLocation = buttonPersonnel.getLocation();
		
		panel = new JPanel();
		panel.setBounds(305, 120, 283, 315);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(47, 79, 79));
		panel.setIgnoreRepaint(true);
		
		log = new LoginPanel(frame,dbc);
		log.setEnabled(false);
		log.destroyPanel();
		log.setLocation(330, 127);
		log.setSize(250, 300);
		frame.getContentPane().add(log);
		
		
		
		createActionListeners();
		
		
		
		
		frame.setLocationRelativeTo(null);
	}
	
	
	//********************************* Listeners ***************************************************//
	public void createActionListeners(){
		
		buttonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				buttonAdmin.setIcon(Variables.Images.iconAdminPressed);
				buttonPersonnel.setIcon(Variables.Images.iconPersonnel);
				isManager = true;
				if(buttonAdminLocation.x == buttonAdmin.getLocation().x){
					moveButtonsToLeft();
					showLoginPanel();
				}
			}
		});
		
		buttonPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				buttonPersonnel.setIcon(Variables.Images.iconPersonnelPressed);
				buttonAdmin.setIcon(Variables.Images.iconAdmin);
				isManager = false;
				if(buttonPersonnelLocation.x == buttonPersonnel.getLocation().x){
					moveButtonsToLeft();
					showLoginPanel();
				}
			}
		});
		
		frame.getContentPane().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(buttonAdminLocation.x == buttonAdmin.getLocation().x + 150){
					buttonPersonnel.setIcon(Variables.Images.iconPersonnel);
					buttonAdmin.setIcon(Variables.Images.iconAdmin);
					moveButtonsToRight();
					closeLoginPanel();
				}
					
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		
	
	}
	
	private Thread moveLeft, moveRight, showLogin, closeLogin;
	private JPanel panel;
	public void moveButtonsToLeft(){
		moveLeft = new Thread(){
			public void run(){
				try {
					for(int i=0 ; i<150 ; i++){
						buttonAdmin.setLocation(buttonAdmin.getBounds().x-1, buttonAdmin.getBounds().y);
						buttonPersonnel.setLocation(buttonPersonnel.getBounds().x-1, buttonPersonnel.getBounds().y);
						Thread.sleep(5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
			
		};
		moveLeft.start();
	}
	
	public void moveButtonsToRight(){
		moveRight = new Thread(){
			public void run(){
				try {
					for(int i=0 ; i<150 ; i++){
						buttonAdmin.setLocation(buttonAdmin.getBounds().x+1, buttonAdmin.getBounds().y);
						buttonPersonnel.setLocation(buttonPersonnel.getBounds().x+1, buttonPersonnel.getBounds().y);
						Thread.sleep(5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		};
		moveRight.start();
	}
	
	public void showLoginPanel(){
		showLogin = new Thread(){
			public void run(){
				try {
					log.createPanel();
					log.setEnabled(true);
					for(int i=0 ; i<150 ; i++){
						log.setLocation(log.getBounds().x+1, log.getBounds().y);
						panel.setLocation(panel.getBounds().x-1, panel.getBounds().y);
						Thread.sleep(5);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
			
		};
		showLogin.start();
	}
	
	public void closeLoginPanel(){
		closeLogin = new Thread(){
			public void run(){
				try {
					
					for(int i=0 ; i<150 ; i++){
						log.setLocation(log.getBounds().x-1, log.getBounds().y);
						panel.setLocation(panel.getBounds().x+1, panel.getBounds().y);
						Thread.sleep(5);
					}
					log.destroyPanel();
					log.setEnabled(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
			
		};
		closeLogin.start();
	}
	
	public static Connection makeConnection() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/gelirGider", "postgres", "12345");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return c;
	}
}
