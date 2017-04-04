import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AddAdminPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddAdminPanel() {
		setSize(625, 550);
		setLayout(null);
		
		JLabel lblAddPersonel = new JLabel("Add ADMIN");
		lblAddPersonel.setBounds(227, 210, 190, 14);
		add(lblAddPersonel);
	}
}
