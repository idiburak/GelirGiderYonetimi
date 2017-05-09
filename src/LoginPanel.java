import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.plaf.OptionPaneUI;

import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Frame;

public class LoginPanel extends JPanel {

	private JButton buttonLogin;
	private JTextField textFieldUserName;
	private JPasswordField textFieldPassword;
	private JLabel labelLogo;
	private Connection dbc;

	/**
	 * Create the panel.
	 */
	public LoginPanel(Frame m, Connection dbc) {
		this.dbc = dbc;
		setOpaque(false);
		setPreferredSize(new Dimension(250,300));
		setLayout(null);
		
		
		buttonLogin = new JButton("");
		buttonLogin.setIcon(new ImageIcon(LoginPanel.class.getResource("/Images/Buttons/LoginBurron.png")));
		buttonLogin.setBounds(50, 250, 150, 38);
		buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonLogin.setFocusPainted(false);
		buttonLogin.setBorderPainted(false);
		buttonLogin.setContentAreaFilled(false);
		
		
		textFieldUserName = new JTextField("Kullanýcý Adý");
		textFieldUserName.setForeground(Color.LIGHT_GRAY);
		textFieldUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldUserName.setBounds(10, 130, 230, 38);
		textFieldUserName.setColumns(10);
		
		textFieldPassword = new JPasswordField("Password");
		textFieldPassword.setForeground(Color.LIGHT_GRAY);
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldPassword.setBounds(10, 190, 230, 38);
		textFieldPassword.setColumns(10);
		
		labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon(LoginPanel.class.getResource("/Images/logo.png")));
		labelLogo.setBounds(50, 11, 150, 100);
		
		textFieldUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(textFieldUserName.getText().equals("Kullanýcý Adý")){
					textFieldUserName.setForeground(Color.BLACK);
					textFieldUserName.setText("");
				}	
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(textFieldUserName.getText().equals("")){
					textFieldUserName.setForeground(Color.LIGHT_GRAY);
					textFieldUserName.setText("Kullanýcý Adý");
				}
			}
		});
		
		textFieldPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(textFieldPassword.getText().equals("Password")){
					textFieldPassword.setForeground(Color.BLACK);
					textFieldPassword.setText("");
				}	
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(textFieldPassword.getText().equals("")){
					textFieldPassword.setForeground(Color.LIGHT_GRAY);
					textFieldPassword.setText("Password");
				}
			}
		});
		
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m.dispose();
						try {
							AdminWindow aw = new AdminWindow(dbc);
							aw.getFrame().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
				
				
				/*
				String[] result = checkInput();
				if(result!=null){
					m.dispose();
					switch (result[1]){
						case "admin" : {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										AdminWindow aw = new AdminWindow(dbc);
										aw.getFrame().setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							break;
						}
						case "yonetici":{
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										ManagerWindow mw = new ManagerWindow();
										mw.getFrame().setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							break;
						}
						case "personel":{
							break;
						}
						case "muhasebeci":{
							break;
						}
					}
				}else{
					JOptionPane.showMessageDialog(m, "Girilen Bilgileri Kontrol Edin!","Hata",JOptionPane.ERROR_MESSAGE);
				}
				*/
			}
		});
		
		
		createPanel();
	}
	
	public void createPanel(){
		add(buttonLogin);
		add(textFieldUserName);
		add(textFieldPassword);
		add(labelLogo);
	
	}
	
	public void destroyPanel(){
		removeAll();
	}
	
	public String[] checkInput(){
		String username = textFieldUserName.getText();
		String password = textFieldPassword.getText();

		try {
			String sql = "SELECT password,position FROM personnel WHERE user_name = ? ";
			PreparedStatement ps = dbc.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String passDb = rs.getString("password");
				String pos = rs.getString("position");
				if(password.equals(passDb)){
					String[] result = {username,pos};
					return result;
				}
				return null;
			}else{
				//System.out.println("kullanici yok");
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
