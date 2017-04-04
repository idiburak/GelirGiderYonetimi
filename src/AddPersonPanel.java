import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddPersonPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public AddPersonPanel() {
		setSize(625, 550);
		setLayout(null);
		
		JLabel lblAddPersonel = new JLabel("Add PERSONEL");
		lblAddPersonel.setBounds(227, 210, 190, 14);
		add(lblAddPersonel);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setBounds(222, 122, 86, 20);
		add(textField);
		textField.setColumns(10);
	}
}
