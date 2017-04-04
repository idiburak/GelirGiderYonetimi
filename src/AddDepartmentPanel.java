import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddDepartmentPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddDepartmentPanel() {
		setSize(625, 550);
		setLayout(null);
		
		JLabel lblAddPersonel = new JLabel("Add DEPARTMENT");
		lblAddPersonel.setBounds(227, 210, 190, 14);
		add(lblAddPersonel);
	}

}
