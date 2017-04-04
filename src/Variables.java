import java.awt.Color;

import javax.swing.ImageIcon;

public class Variables {
	protected static class Images{
		//Login Panel Icons
		protected static final ImageIcon iconAdmin = new ImageIcon(Main.class.getResource("/Images/Buttons/AdminButton.png"));;
		protected static final ImageIcon iconAdminPressed = new ImageIcon(Main.class.getResource("/Images/Buttons/AdminButtonPressed.png"));
		protected static final ImageIcon iconPersonnel = new ImageIcon(Main.class.getResource("/Images/Buttons/PersonnelButton.png"));
		protected static final ImageIcon iconPersonnelPressed = new ImageIcon(Main.class.getResource("/Images/Buttons/PersonnelButtonPressed.png"));
		
		
		protected static final ImageIcon iconAddPersonnel = new ImageIcon(Main.class.getResource("/Images/AddPersonnelButton.png"));
		protected static final ImageIcon iconAddAdmin = new ImageIcon(Main.class.getResource("/Images/AddAdminButton.png"));
		protected static final ImageIcon iconAddDepartment = new ImageIcon(Main.class.getResource("/Images/AddDepartmentButton.png"));
		protected static final ImageIcon iconSpendingLimit = new ImageIcon(Main.class.getResource("/Images/SpendingLimitButton.png"));
		
		
	}
	protected static class Colors{
		protected static final Color backgroundColor = new Color(47, 79, 79);
	}
	
	
}
