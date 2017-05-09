import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import com.toedter.calendar.JCalendar;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerWindow {

	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}
	private Connection dbc;
	private JButton buttonLogout;
	private JPanel panelInfo;
	private JLabel lblDepartmanBilgileri;
	private JTextPane textPane_1;
	private JLabel lblYneticiBilgileri;
	private JTextPane textPane_2;
	private JLabel lblSpendingLogo;
	private JTable table;
	private JButton buttonApply;
	private JButton buttonDeny;
	private String username;
	private JTextPane textPaneDetail;
	
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
					ManagerWindow window = new ManagerWindow();
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
	public ManagerWindow() {
		username = "bidi";
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
		frame.getContentPane().setBackground(Variables.Colors.backgroundColor);
		frame.getContentPane().setLayout(null);
		
		lblSpendingLogo = new JLabel("");
		lblSpendingLogo.setBounds(10, 11, 83, 80);
		lblSpendingLogo.setIcon(Variables.Images.iconSpendingLogo);
		frame.getContentPane().add(lblSpendingLogo);
		
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
		
		String userInfo[] = getUserInfo();
		String departmentInfo[] = getDepInfo(username);
		String manInfo = "Adý\t: " + userInfo[0] + "\nSoyadý\t: " + userInfo[1] + 
						"\nEmail\t: " + userInfo[2] + "\nTelefon\t: " + userInfo[3];
		String depInfo = "Departman\t: " + departmentInfo[0] + "\nYönetici\t: " + departmentInfo[2] + 
							"\nHarcama Limiti : " + departmentInfo[1] + "\nKalan Limit\t: k limit" + "\n\n";
		
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
		lblYneticiBilgileri.setBounds(10, 11, 280, 45);
		panelInfo.add(lblYneticiBilgileri);
		
		textPane_2 = new JTextPane();
		textPane_2.setText(manInfo);
		textPane_2.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		textPane_2.setEditable(false);
		textPane_2.setBackground(SystemColor.menu);
		textPane_2.setAlignmentY(0.0f);
		textPane_2.setAlignmentX(0.0f);
		textPane_2.setBounds(10, 67, 280, 71);
		panelInfo.add(textPane_2);
		
		JLabel lblAddSpending = new JLabel("Harcama Onayla");
		lblAddSpending.setForeground(Color.WHITE);
		lblAddSpending.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSpending.setFont(new Font("Ubuntu", Font.PLAIN, 28));
		lblAddSpending.setBounds(10, 11, 604, 80);
		frame.getContentPane().add(lblAddSpending);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 102, 604, 300);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		setTableDefault();
		updateTable();
		scrollPane.setViewportView(table);
		
		buttonApply = new JButton("");
		buttonApply.setFocusPainted(false);
		buttonApply.setContentAreaFilled(false);
		buttonApply.setBorderPainted(false);
		buttonApply.setBounds(518, 413, 88, 88);
		buttonApply.setIcon(Variables.Images.iconApply);
		frame.getContentPane().add(buttonApply);
		
		buttonDeny = new JButton("");
		buttonDeny.setFocusPainted(false);
		buttonDeny.setContentAreaFilled(false);
		buttonDeny.setBorderPainted(false);
		buttonDeny.setBounds(518, 512, 88, 88);
		buttonDeny.setIcon(Variables.Images.iconDeny);
		frame.getContentPane().add(buttonDeny);
		
		textPaneDetail = new JTextPane();
		textPaneDetail.setText("Seçilen harcama bilgileri");
		textPaneDetail.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		textPaneDetail.setEditable(false);
		textPaneDetail.setBackground(SystemColor.menu);
		textPaneDetail.setAlignmentY(0.0f);
		textPaneDetail.setAlignmentX(0.0f);
		textPaneDetail.setBounds(10, 413, 498, 187);
		frame.getContentPane().add(textPaneDetail);
		
		NumberFormat format = DecimalFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(10000);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
		
		
	    table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String id = (String) table.getValueAt(table.getSelectedRow(), 0);
				try {
					Statement st = dbc.createStatement();
					String sql = "SELECT name, surname, s_date, s_type_name, s_company, s_explanation, s_amount  FROM spending s, spendingType t, personnel p "
							+ "WHERE s.spending_id = " + id + " AND t.s_type_id = s.s_type_id AND s.user_name = p.user_name ORDER BY spending_id";
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {
						String personnel = rs.getString("name") + " " + rs.getString("surname");
						String sDate = rs.getString("s_date");
						String type = rs.getString("s_type_name");
						String company = rs.getString("s_company");
						String explanation = rs.getString("s_explanation");
						int amount = rs.getInt("s_amount");
						
						String detail = "Personel\t: " + personnel + "\nTarih\t: " + sDate + "\nFirma\t: " + company ;
						detail += "\nHarcama Türü      : " + type + "\nHarcama Miktarý : " + amount + "\nAçýklama\t: " + explanation;
						textPaneDetail.setText(detail);

					}
				} catch (SQLException e) {
					e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private String[] getDepInfo(String dep){
		try {
			String depInfo[] = new String[4];
			String sql = "SELECT dep_name, dep_limit, name, surname FROM personnel p, department d WHERE user_name = ? AND d.dep_id = p.dep_id";
			PreparedStatement ps = dbc.prepareStatement(sql);
			ps.setString(1, username);
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
	
	private void updateTable() {
		try {
			setTableDefault();
			Statement st = dbc.createStatement();
			String sql = "SELECT s.spending_id, t.s_type_name, s.s_date, s.s_amount, s.user_name  FROM spending s, spendingType t, waitingSpending w "
					+ "WHERE t.s_type_id = s.s_type_id AND s.spending_id = w.spending_id ORDER BY spending_id";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("spending_id"));
				String type = rs.getString("s_type_name");
				String sDate = rs.getString("s_date");
				int amount = rs.getInt("s_amount");
				String user_name = rs.getString("user_name");

				Object[] row = {id, type, sDate, amount, user_name };
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("serial")
	void setTableDefault(){
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Harcama T\u00FCr\u00FC", "Tarih", "Tutar", "Personel"
				}
			) {
				public boolean isCellEditable(int row, int column){  
					return false;  
				} 
			});
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(80);
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
