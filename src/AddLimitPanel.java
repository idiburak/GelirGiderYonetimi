import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AddLimitPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AddLimitPanel() {
		setSize(625, 550);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Harcama Limiti Düzenle");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 605, 44);
		add(lblNewLabel);
	}

}
