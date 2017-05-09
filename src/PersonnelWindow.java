import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import com.toedter.calendar.JCalendar;
import javax.swing.Box;
import javax.swing.DropMode;
import javax.swing.DefaultComboBoxModel;

public class PersonnelWindow {

	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}
	private Connection dbc;
	private JButton buttonLogout;
	private JPanel panelInfo;
	private JTextPane textPane;
	private JLabel lblDepartmanBilgileri;
	private JTextPane textPane_1;
	private JLabel lblYneticiBilgileri;
	private JTextPane textPane_2;
	private JLabel lblSpendingLogo;
	private JTextField textFieldCompany;
	private String username;
	private JComboBox comboBoxSpendingTypes;
	private JButton btnAdd;
	private JDateChooser dateChooser;
	private JFormattedTextField textFieldAmount;
	private JTextArea textAreaExplanation;
	private JButton btnClear;

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
					PersonnelWindow window = new PersonnelWindow();
					window.frame.setVisible(true);
					window.frame.setTitle("Personel Harcama Ekraný");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PersonnelWindow() {
		dbc = makeConnection();
		username = "bahar";
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
		frame.getContentPane().setBackground(Variables.Colors.backgroundColor);
		frame.getContentPane().setLayout(null);
		
		lblSpendingLogo = new JLabel("");
		lblSpendingLogo.setBounds(10, 11, 83, 80);
		lblSpendingLogo.setIcon(Variables.Images.iconSpendingLogo);
		frame.getContentPane().add(lblSpendingLogo);
		
		JLabel lblSpendingLogo2 = new JLabel("");
		lblSpendingLogo2.setBounds(330, 123, 284, 120);
		lblSpendingLogo2.setIcon(Variables.Images.iconSpendingLogo2);
		frame.getContentPane().add(lblSpendingLogo2);
		
		panelInfo = new JPanel();
		panelInfo.setBounds(624, 11, 300, 589);
		frame.getContentPane().add(panelInfo);
		panelInfo.setLayout(null);
		
		buttonLogout = new JButton("");
		buttonLogout.setBounds(102, 554, 96, 24);
		buttonLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonLogout.setIcon(Variables.Images.iconLogout);
		buttonLogout.setFocusPainted(false);
		buttonLogout.setBorderPainted(false);
		buttonLogout.setContentAreaFilled(false);
		panelInfo.add(buttonLogout);
		
		JLabel lblNewLabel = new JLabel("Personel Bilgileri");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 280, 45);
		panelInfo.add(lblNewLabel);
		
		String userInfo[] = getUserInfo();
		String departmentInfo[] = getDepInfo(username);
		String managerInfo[] = getManInfo();
		String info = "Adý\t: " + userInfo[0] + "\nSoyadý\t: " + userInfo[1] + 
						"\nEmail\t: " + userInfo[2] + "\nTelefon\t: " + userInfo[3];
		String depInfo = "Departman\t: " + departmentInfo[0] + "\nYönetici\t: " + departmentInfo[2] + 
				"\nHarcama Limiti : " + departmentInfo[1] + "\nKalan Limit\t: " + calculateLimitLeft();
		String manInfo = "Adý\t: " + managerInfo[0] + "\nSoyadý\t: " + managerInfo[1] + 
				"\nEmail\t: " + managerInfo[2] + "\nTelefon\t: " + managerInfo[3];
		
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.control);
		textPane.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		textPane.setAlignmentY(Component.TOP_ALIGNMENT);
		textPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		textPane.setEditable(false);
		textPane.setBounds(10, 67, 280, 71);
		textPane.setText(info);
		panelInfo.add(textPane);
		
		lblDepartmanBilgileri = new JLabel("Departman Bilgileri");
		lblDepartmanBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartmanBilgileri.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		lblDepartmanBilgileri.setBounds(10, 149, 280, 45);
		panelInfo.add(lblDepartmanBilgileri);
		
		textPane_1 = new JTextPane();
		textPane_1.setText(depInfo);
		textPane_1.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		textPane_1.setEditable(false);
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setAlignmentY(0.0f);
		textPane_1.setAlignmentX(0.0f);
		textPane_1.setBounds(10, 210, 280, 71);
		panelInfo.add(textPane_1);
		
		lblYneticiBilgileri = new JLabel("Y\u00F6netici Bilgileri");
		lblYneticiBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
		lblYneticiBilgileri.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		lblYneticiBilgileri.setBounds(10, 292, 280, 45);
		panelInfo.add(lblYneticiBilgileri);
		
		textPane_2 = new JTextPane();
		textPane_2.setText(manInfo);
		textPane_2.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		textPane_2.setEditable(false);
		textPane_2.setBackground(SystemColor.menu);
		textPane_2.setAlignmentY(0.0f);
		textPane_2.setAlignmentX(0.0f);
		textPane_2.setBounds(10, 353, 280, 71);
		panelInfo.add(textPane_2);
		
		JLabel lblAddSpending = new JLabel("Harcama Ekle");
		lblAddSpending.setForeground(Color.WHITE);
		lblAddSpending.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSpending.setFont(new Font("Ubuntu", Font.PLAIN, 28));
		lblAddSpending.setBounds(10, 11, 604, 80);
		frame.getContentPane().add(lblAddSpending);
		
		JLabel lblSpendingType = new JLabel("Harcama T\u00FCr\u00FC");
		lblSpendingType.setForeground(Color.WHITE);
		lblSpendingType.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblSpendingType.setBounds(10, 123, 150, 24);
		frame.getContentPane().add(lblSpendingType);
		
		JLabel lblSpendingDate = new JLabel("Harcama Tarihi");
		lblSpendingDate.setForeground(Color.WHITE);
		lblSpendingDate.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblSpendingDate.setBounds(10, 171, 150, 24);
		frame.getContentPane().add(lblSpendingDate);
		
		JLabel lblSpendingAmount = new JLabel("Harcama Miktar\u0131");
		lblSpendingAmount.setForeground(Color.WHITE);
		lblSpendingAmount.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblSpendingAmount.setBounds(10, 219, 150, 24);
		frame.getContentPane().add(lblSpendingAmount);
		
		JLabel lblCompany = new JLabel("Firma");
		lblCompany.setForeground(Color.WHITE);
		lblCompany.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblCompany.setBounds(10, 267, 150, 24);
		frame.getContentPane().add(lblCompany);
		
		JLabel lblExplanation = new JLabel("A\u00E7\u0131klama");
		lblExplanation.setForeground(Color.WHITE);
		lblExplanation.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		lblExplanation.setBounds(10, 315, 150, 24);
		frame.getContentPane().add(lblExplanation);
		
		comboBoxSpendingTypes = new JComboBox();
		comboBoxSpendingTypes.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		comboBoxSpendingTypes.setBounds(170, 123, 150, 24);
		frame.getContentPane().add(comboBoxSpendingTypes);
		
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		dateChooser.setDateFormatString("dd / MMMM / yyyy");
		Date date = new Date();
		dateChooser.setDate(date);
		dateChooser.setBounds(170, 171, 150, 24);
		frame.getContentPane().add(dateChooser);
		
		NumberFormat format = DecimalFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(10000);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    
		textFieldAmount = new JFormattedTextField(formatter);
		textFieldAmount.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		textFieldAmount.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textFieldAmount.setBounds(170, 219, 150, 24);
		frame.getContentPane().add(textFieldAmount);
		
		textFieldCompany = new JTextField();
		textFieldCompany.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		textFieldCompany.setBounds(170, 267, 444, 24);
		frame.getContentPane().add(textFieldCompany);
		textFieldCompany.setColumns(10);
		
		textAreaExplanation = new JTextArea();
		textAreaExplanation.setLineWrap(true);
		textAreaExplanation.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		textAreaExplanation.setBounds(169, 315, 445, 125);
		frame.getContentPane().add(textAreaExplanation);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(10, 158, 310, 2);
		frame.getContentPane().add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(10, 206, 310, 2);
		frame.getContentPane().add(verticalStrut_1);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setBounds(10, 254, 310, 2);
		frame.getContentPane().add(verticalStrut_2);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setBounds(10, 302, 310, 2);
		frame.getContentPane().add(verticalStrut_3);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalStrut_4.setBounds(10, 451, 604, 2);
		frame.getContentPane().add(verticalStrut_4);
		
		btnAdd = new JButton("EKLE");
		btnAdd.setBounds(464, 464, 150, 23);
		frame.getContentPane().add(btnAdd);
		
		btnClear = new JButton("TEM\u0130ZLE");
		btnClear.setBounds(170, 464, 150, 23);
		frame.getContentPane().add(btnClear);
		
		updateSpendingTypes();
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearPage();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "INSERT INTO spending (s_amount, s_type_id, s_date, s_explanation, s_company, user_name, dep_id)" + 
									"VALUES (?,?,?,?,?,?,?)";
					PreparedStatement preparedStatement = dbc.prepareStatement(sql);
					preparedStatement.setInt(1,Integer.parseInt(textFieldAmount.getText()));
					preparedStatement.setInt(2, comboBoxSpendingTypes.getSelectedIndex()+1);
					preparedStatement.setDate(3, new java.sql.Date(dateChooser.getDate().getTime()));
					preparedStatement.setString(4, textAreaExplanation.getText());
					preparedStatement.setString(5, textFieldCompany.getText());
					preparedStatement.setString(6, username);
					preparedStatement.setInt(7, getUserDep());
					preparedStatement .executeUpdate();
					if(preparedStatement.getWarnings() != null){
						throw new Exception(preparedStatement.getWarnings().getMessage());
					}
	
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "HATA", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
	
		buttonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Main m = new Main();
							m.getFrame().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		frame.setLocationRelativeTo(null);
	}
	
	private void clearPage(){
		comboBoxSpendingTypes.setSelectedIndex(0);
		dateChooser.setDate(new Date());
		textFieldAmount.setText(0+"");
		textFieldCompany.setText("");
		textAreaExplanation.setText("");
	}
	
	private int getUserDep(){
		try {
			int userDep = 0;
			String sql = "SELECT dep_id FROM personnel WHERE user_name = ? ";
			PreparedStatement ps = dbc.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userDep = rs.getInt("dep_id");
			}
			return userDep;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private String[] getUserInfo(){
		try {
			String userInfo[] = new String[4];
			String sql = "SELECT name,surname,email,phone FROM personnel WHERE user_name = ? ";
			PreparedStatement ps = dbc.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userInfo[0] = rs.getString("name");
				userInfo[1] = rs.getString("surname");
				userInfo[2] = rs.getString("email");
				userInfo[3] = rs.getString("phone");
				return userInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String[] getDepInfo(String dep){
		try {
			String depInfo[] = new String[4];
			String sql = "SELECT dep_name, dep_limit, name, surname FROM personnel p, department d"
					+ " WHERE p.position = 'yonetici' AND d.dep_id = ? AND d.dep_id = p.dep_id ";
			PreparedStatement ps = dbc.prepareStatement(sql);
			ps.setInt(1, getUserDep());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				depInfo[0] = rs.getString("dep_name");
				depInfo[1] = Integer.toString(rs.getInt("dep_limit"));
				depInfo[2] = rs.getString("name") + " " + rs.getString("surname");
				return depInfo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String[] getManInfo(){
		try {
			String userInfo[] = new String[4];
			String sql = "SELECT name,surname,email,phone FROM personnel WHERE dep_id = ? AND position = 'yonetici'";
			PreparedStatement ps = dbc.prepareStatement(sql);
			ps.setInt(1, getUserDep());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userInfo[0] = rs.getString("name");
				userInfo[1] = rs.getString("surname");
				userInfo[2] = rs.getString("email");
				userInfo[3] = rs.getString("phone");
				return userInfo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void updateSpendingTypes(){
		try {
			String spendingTypes[];
			Statement st = dbc.createStatement();
			String sql = "SELECT count(*) FROM spendingType";
			ResultSet rs = st.executeQuery(sql);
			int num = 0;
			while (rs.next()) {
				num = rs.getInt(1);
			}
			spendingTypes = new String[num];
			sql = "SELECT s_type_name FROM spendingType ORDER BY s_type_id";
			rs = st.executeQuery(sql);
			int i=0;
			while(rs.next()){
				spendingTypes[i++] = rs.getString("s_type_name");
			}
			comboBoxSpendingTypes.setModel(new DefaultComboBoxModel(spendingTypes));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int calculateLimitLeft(){
		try {
			Date d = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int month = cal.get(Calendar.MONTH) + 1;
			Statement st = dbc.createStatement();
			String sql = "SELECT sum(s_amount),dep_limit FROM spending s, department d  "
					+ "WHERE d.dep_id = "+ getUserDep() +" AND d.dep_id = s.dep_id AND EXTRACT(month FROM \"s_date\") = " + month + " GROUP BY d.dep_id";
			ResultSet rs = st.executeQuery(sql);
			int num = 0,lim = 0;
			while (rs.next()) {
				num = rs.getInt(1);
				lim = rs.getInt(2);
			}
			return lim - num;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
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
