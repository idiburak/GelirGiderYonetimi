import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

public class LoginPanel extends JPanel {

	private JButton buttonLogin;
	private JTextField textFieldUserName;
	private JPasswordField textFieldPassword;
	private JLabel labelLogo;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
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
		
		
		textFieldUserName = new JTextField("Kullan�c� Ad�");
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
				if(textFieldUserName.getText().equals("Kullan�c� Ad�")){
					textFieldUserName.setForeground(Color.BLACK);
					textFieldUserName.setText("");
				}	
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(textFieldUserName.getText().equals("")){
					textFieldUserName.setForeground(Color.LIGHT_GRAY);
					textFieldUserName.setText("Kullan�c� Ad�");
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
}
