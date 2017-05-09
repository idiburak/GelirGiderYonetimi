import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;

public class AddDepartmentPanel extends JPanel {

	private JTextField textFieldName;
	private JSpinner spinnerLimit;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AddDepartmentPanel() {
		setSize(625, 550);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Departman Ekle");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 605, 44);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Departman Ad\u0131");
		lblNewLabel_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 66, 110, 24);
		add(lblNewLabel_1);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(130, 66, 175, 24);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblHarcama = new JLabel("Harcama Limiti");
		lblHarcama.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		lblHarcama.setBounds(320, 66, 110, 24);
		add(lblHarcama);
		
		spinnerLimit = new JSpinner();
		spinnerLimit.setModel(new SpinnerNumberModel(0, 0, 25000, 100));
		spinnerLimit.setBounds(440, 66, 175, 24);
		add(spinnerLimit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 289, 460, 250);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
