import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JComboBox;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JComboBox roleComboBox = new JComboBox();

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 567);
		contentPane = new JPanel() {
			@Override
			//Set gradient background
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				int width = getWidth();
				int height = getHeight();

				Color color1 = new Color(65, 200, 162);
				Color color2 = new Color(24, 91, 157);

				GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		contentPane.setForeground(new Color(173, 255, 47));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("De' Luna Hotel");
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setForeground(new Color(255, 215, 0));
		lblTitle.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 24));
		lblTitle.setBounds(103, 304, 201, 37);
		contentPane.add(lblTitle);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(387, 40, 340, 432);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(30, 144, 255));
		//Change button color on mouse hover
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(65, 200, 162));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(30, 144, 255));
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnLogin.setBounds(39, 362, 274, 42);
		panel.add(btnLogin);

		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(39, 174, 274, 34);
		panel.add(usernameField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPassword.setBounds(39, 219, 70, 14);
		panel.add(lblPassword);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUsername.setBounds(39, 146, 88, 14);
		panel.add(lblUsername);

		JLabel lblHeader = new JLabel("Welcome Back,");
		lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblHeader.setBounds(39, 62, 157, 22);
		panel.add(lblHeader);

		JLabel lblSubHeader = new JLabel("Sign in to continue");
		lblSubHeader.setBackground(new Color(255, 255, 255));
		lblSubHeader.setForeground(new Color(169, 169, 169));
		lblSubHeader.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSubHeader.setBounds(39, 95, 157, 22);
		panel.add(lblSubHeader);

		passwordField = new JPasswordField();
		passwordField.setBounds(39, 244, 274, 34);
		panel.add(passwordField);

		// JComboBox roleComboBox = new JComboBox();
		roleComboBox.setBounds(39, 316, 114, 35);
		roleComboBox.addItem("Staff");
		roleComboBox.addItem("Admin");
		panel.add(roleComboBox);

		JLabel lblAccountRole = new JLabel("Account Role");
		lblAccountRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAccountRole.setBounds(39, 289, 88, 14);
		panel.add(lblAccountRole);

		JLabel lblLogo = new JLabel("");
		Image img_logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(120, 120,
				Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(img_logo));
		lblLogo.setBounds(124, 153, 131, 175);
		contentPane.add(lblLogo);

		JLabel lblSubtitle = new JLabel("Enchanted with Elegance");
		lblSubtitle.setForeground(new Color(230, 230, 250));
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitle.setFont(new Font("Perpetua", Font.PLAIN, 18));
		lblSubtitle.setBounds(91, 352, 190, 20);
		contentPane.add(lblSubtitle);

		JLabel lblDash = new JLabel("---");
		lblDash.setHorizontalAlignment(SwingConstants.CENTER);
		lblDash.setForeground(new Color(230, 230, 250));
		lblDash.setFont(new Font("Perpetua", Font.PLAIN, 16));
		lblDash.setBounds(81, 339, 190, 14);
		contentPane.add(lblDash);

		JLabel lblHotelManagement = new JLabel("| Hotel Management System");
		lblHotelManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelManagement.setForeground(new Color(255, 255, 255));
		lblHotelManagement.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
		lblHotelManagement.setBounds(10, 40, 267, 20);
		contentPane.add(lblHotelManagement);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidateCredentials();
			}
		});
	}
	
	//Method to check if an account exists by matching login credentials
	void ValidateCredentials() {
		boolean result;

		String getUsername = usernameField.getText().trim();
		String getPassword = new String(passwordField.getPassword()).trim();
		String getRole = roleComboBox.getSelectedItem().toString();

		StaffRecord staffRecord = new StaffRecord(getUsername, getPassword, getRole);

		result = staffRecord.checkCredentials();

		if (usernameField.getText().isEmpty() || passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
		} else if (result == false) {
			JOptionPane.showMessageDialog(null, "Invalid Account.");
		} else {
			if (getRole == "Staff") {
				Homepage formHome = new Homepage();
				formHome.setVisible(true);
				formHome.menuStaff.setVisible(false);
				formHome.panelHome.lblName.setText(usernameField.getText());
				dispose();
			} else {
				Homepage formHome = new Homepage();
				formHome.setVisible(true);
				formHome.menuStaff.setVisible(true);
				formHome.panelHome.lblName.setText(usernameField.getText());
				dispose();
			}
		}
	}
}
