import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class AddPersonPanel extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldTcNo;
	private JTextField textFieldPhone;
	private JTextField textFieldMail;
	private JComboBox<String> comboBoxSex;
	private JComboBox<String> comboBoxDepartment;
	private JTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnFix;
	private Connection dbc;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JLabel lblifre;
	private JDateChooser dateChooser;

	/**
	 * Create the panel.
	 */
	public AddPersonPanel(Connection dbc) {
		setOpaque(false);
		this.dbc = dbc;
		setSize(625, 550);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Personel Ekle");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 605, 44);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad\u0131");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 102, 110, 24);
		add(lblNewLabel_1);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(130, 102, 175, 24);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblSoyad = new JLabel("Soyad\u0131");
		lblSoyad.setForeground(Color.WHITE);
		lblSoyad.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblSoyad.setBounds(320, 102, 110, 24);
		add(lblSoyad);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(440, 102, 175, 24);
		add(textFieldSurname);
		
		JLabel lblTelefon = new JLabel("TC Kimlik");
		lblTelefon.setForeground(Color.WHITE);
		lblTelefon.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblTelefon.setBounds(10, 137, 110, 24);
		add(lblTelefon);
		
		textFieldTcNo = new JTextField();
		textFieldTcNo.setColumns(10);
		textFieldTcNo.setBounds(130, 137, 175, 24);
		add(textFieldTcNo);
		
		JLabel lblEmail = new JLabel("Do\u011Fum Tarihi");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblEmail.setBounds(320, 137, 110, 24);
		add(lblEmail);
		
		JLabel label = new JLabel("Telefon");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		label.setBounds(10, 172, 110, 24);
		add(label);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(130, 172, 175, 24);
		add(textFieldPhone);
		
		JLabel label_1 = new JLabel("E-Mail");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		label_1.setBounds(320, 172, 110, 24);
		add(label_1);
		
		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(440, 172, 175, 24);
		add(textFieldMail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 289, 460, 250);
		add(scrollPane);
		
		table = new JTable();
		setTableDefault();
		updateTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCinsiyet = new JLabel("Cinsiyet");
		lblCinsiyet.setForeground(Color.WHITE);
		lblCinsiyet.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblCinsiyet.setBounds(10, 209, 110, 24);
		add(lblCinsiyet);
		
		comboBoxSex = new JComboBox<String>();
		comboBoxSex.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		comboBoxSex.setBounds(130, 209, 175, 24);
		comboBoxSex.setModel(new DefaultComboBoxModel<String>(new String[] {"Erkek", "Kad\u0131n"}));
		add(comboBoxSex);	
		
		JLabel lblDepartman = new JLabel("Departman");
		lblDepartman.setForeground(Color.WHITE);
		lblDepartman.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblDepartman.setBounds(320, 209, 110, 24);
		add(lblDepartman);
		
		
		
		btnAdd = new JButton("EKLE");
		btnAdd.setBounds(505, 244, 110, 23);
		add(btnAdd);
		
		btnDelete = new JButton("Sil");
		btnDelete.setBounds(505, 286, 110, 23);
		add(btnDelete);
		
		btnFix = new JButton("D\u00FCzenle");
		btnFix.setBounds(505, 322, 110, 23);
		add(btnFix);
		
		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblKullancAd.setForeground(Color.WHITE);
		lblKullancAd.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblKullancAd.setBounds(10, 66, 110, 24);
		add(lblKullancAd);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(130, 66, 175, 24);
		add(textFieldUsername);
		
		lblifre = new JLabel("\u015Eifre");
		lblifre.setForeground(Color.WHITE);
		lblifre.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblifre.setBounds(320, 66, 110, 24);
		add(lblifre);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(440, 66, 175, 24);
		add(textFieldPassword);
		
		comboBoxDepartment = new JComboBox<String>();
		comboBoxDepartment.setBounds(440, 207, 175, 23);
		add(comboBoxDepartment);
		comboBoxDepartment.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		dateChooser.setDateFormatString("dd.MMMM.yyyy");
		dateChooser.setBounds(439, 137, 176, 24);
		add(dateChooser);
		updateDepartmentTypes();
	}
	
	public void updateDepartmentTypes(){
		try {
			String spendingTypes[];
			Statement st = dbc.createStatement();
			String sql = "SELECT count(*) FROM department";
			ResultSet rs = st.executeQuery(sql);
			int num = 0;
			while (rs.next()) {
				num = rs.getInt(1);
			}
			spendingTypes = new String[num];
			sql = "SELECT dep_name FROM department";
			rs = st.executeQuery(sql);
			int i=0;
			while(rs.next()){
				spendingTypes[i++] = rs.getString("dep_name");
			}
			comboBoxDepartment.setModel(new DefaultComboBoxModel<String>(spendingTypes));
			
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
					"Kullan\u0131c\u0131 Ad\u0131", "Ad\u0131 Soyad\u0131", "Telefon", "Email"
				}
			) {
				public boolean isCellEditable(int row, int column){  
					return false;  
				} 
			});	
	}
	
	private void updateTable() {
		try {
			setTableDefault();
			Statement st = dbc.createStatement();
			String sql = "SELECT user_name, name, surname, phone, email  FROM personnel ";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String uName = rs.getString("user_name");
				String name = rs.getString("name") + " " + rs.getString("surname");
				String phone = rs.getString("phone");
				String email = rs.getString("email");

				Object[] row = {uName, name, phone, email};
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
