import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Color;

public class AddAdminPanel extends JPanel {

	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldTcNo;
	private JTextField textFieldBirthDate;
	private JTextField textFieldPhone;
	private JTextField textFieldMail;
	private JComboBox comboBoxSex;
	private JComboBox comboBoxDepartment;
	private JTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnFix;
	private Connection dbc;
	
	/**
	 * Create the panel.
	 */
	public AddAdminPanel() {
		setOpaque(false);
		setSize(625, 550);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Y\u00F6netici Ekle");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 605, 44);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad\u0131");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 66, 110, 24);
		add(lblNewLabel_1);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(130, 66, 175, 24);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblSoyad = new JLabel("Soyad\u0131");
		lblSoyad.setForeground(Color.WHITE);
		lblSoyad.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblSoyad.setBounds(320, 66, 110, 24);
		add(lblSoyad);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(440, 66, 175, 24);
		add(textFieldSurname);
		
		JLabel lblTelefon = new JLabel("TC Kimlik");
		lblTelefon.setForeground(Color.WHITE);
		lblTelefon.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblTelefon.setBounds(10, 101, 110, 24);
		add(lblTelefon);
		
		textFieldTcNo = new JTextField();
		textFieldTcNo.setColumns(10);
		textFieldTcNo.setBounds(130, 101, 175, 24);
		add(textFieldTcNo);
		
		JLabel lblEmail = new JLabel("Do\u011Fum Tarihi");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblEmail.setBounds(320, 101, 110, 24);
		add(lblEmail);
		
		textFieldBirthDate = new JTextField();
		textFieldBirthDate.setColumns(10);
		textFieldBirthDate.setBounds(440, 101, 175, 24);
		add(textFieldBirthDate);
		
		JLabel label = new JLabel("Telefon");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		label.setBounds(10, 136, 110, 24);
		add(label);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(130, 136, 175, 24);
		add(textFieldPhone);
		
		JLabel label_1 = new JLabel("E-Mail");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		label_1.setBounds(320, 136, 110, 24);
		add(label_1);
		
		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(440, 136, 175, 24);
		add(textFieldMail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 289, 460, 250);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCinsiyet = new JLabel("Cinsiyet");
		lblCinsiyet.setForeground(Color.WHITE);
		lblCinsiyet.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblCinsiyet.setBounds(10, 173, 110, 24);
		add(lblCinsiyet);
		
		comboBoxSex = new JComboBox();
		comboBoxSex.setBounds(130, 173, 175, 24);
		add(comboBoxSex);
		
		JLabel lblDepartman = new JLabel("Departman");
		lblDepartman.setForeground(Color.WHITE);
		lblDepartman.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblDepartman.setBounds(320, 173, 110, 24);
		add(lblDepartman);
		
		comboBoxDepartment = new JComboBox();
		comboBoxDepartment.setBounds(440, 173, 175, 24);
		add(comboBoxDepartment);
		
		btnAdd = new JButton("Ekle");
		btnAdd.setBounds(526, 208, 89, 23);
		add(btnAdd);
		
		btnDelete = new JButton("Sil");
		btnDelete.setBounds(526, 286, 89, 23);
		add(btnDelete);
		
		btnFix = new JButton("D\u00FCzenle");
		btnFix.setBounds(526, 322, 89, 23);
		add(btnFix);
	}
}
