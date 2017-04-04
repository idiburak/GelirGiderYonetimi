import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddLimitPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddLimitPanel() {
		setSize(625, 550);
		setLayout(null);
		
		JLabel lblAddPersonel = new JLabel("Add LIMIT");
		lblAddPersonel.setBounds(227, 210, 190, 14);
		add(lblAddPersonel);
	}

}
