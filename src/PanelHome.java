import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelHome extends JPanel {
	private Homepage home;
	/**
	 * Create the panel.
	 */
	
	JLabel lblName = new JLabel("Name");
	
	public PanelHome(Homepage home) {
		setBackground(Color.WHITE);
		
		this.home = home;
		
		setSize(1104, 691);
		setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {

					Login formLogin = new Login();
					formLogin.setVisible(true);
					home.dispose();
				}
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLogout.setBackground(new Color(51, 204, 255));
		btnLogout.setBounds(463, 406, 179, 31);
		add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("Welcome Back,");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(447, 295, 195, 71);
		add(lblNewLabel);
		
		
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(481, 364, 128, 31);
		add(lblName);
		
	}
}
