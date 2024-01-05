import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;

public class PanelAddCustomer extends JPanel {
	private JTextField firstNameField;
	private JTextField icField;
	private JTextField contactField;
	private JTextField emailField;
	private JTextField lastNameField;

	private Homepage home;

	/**
	 * Create the panel.
	 */
	JComboBox cboStatus = new JComboBox();

	public PanelAddCustomer(Homepage home) {
		this.home = home;

		setBackground(new Color(255, 255, 255));
		setSize(1104, 691);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 1104, 81);
		add(panel);

		JLabel lblNewLabel_1 = new JLabel("Add Customer ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1.setBackground(SystemColor.menu);
		lblNewLabel_1.setBounds(417, 11, 277, 51);
		panel.add(lblNewLabel_1);

		JLabel lblCustomerName = new JLabel("First Name:");
		lblCustomerName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCustomerName.setBounds(98, 231, 114, 22);
		add(lblCustomerName);

		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(98, 264, 264, 29);
		add(firstNameField);

		JLabel lblIc = new JLabel("IC No.:");
		lblIc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblIc.setBounds(98, 323, 114, 22);
		add(lblIc);

		icField = new JTextField();
		icField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					JOptionPane.showMessageDialog(null, "Enter numeric digits only. (0-9)");
					icField.setText("");
				}

			}
		});
		icField.setColumns(10);
		icField.setBounds(98, 356, 264, 29);
		add(icField);

		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblContactNumber.setBounds(98, 417, 155, 22);
		add(lblContactNumber);

		contactField = new JTextField();
		contactField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					JOptionPane.showMessageDialog(null, "Enter numeric digits only. (0-9)");
					contactField.setText("");
				}
			}
		});
		contactField.setColumns(10);
		contactField.setBounds(98, 450, 264, 29);
		add(contactField);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEmail.setBounds(98, 508, 114, 22);
		add(lblEmail);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(98, 541, 264, 29);
		add(emailField);

		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(416, 264, 264, 29);
		add(lastNameField);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLastName.setBounds(416, 231, 114, 22);
		add(lblLastName);

		JLabel lblNewLabel_1_1 = new JLabel("Customer Details");
		lblNewLabel_1_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(98, 117, 277, 43);
		add(lblNewLabel_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(98, 166, 145, 2);
		add(panel_1);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Declaring email and ic variables to validate duplicate entries and email
				// format
				String email = emailField.getText().trim();
				String ic = icField.getText();
				if (firstNameField.getText().equals("") || lastNameField.getText().equals("")
						|| icField.getText().equals("") || contactField.getText().equals("")
						|| emailField.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Please fill in all required information.");
				} else if (Customer.ValidateEmail(email)) {
					if (!Customer.CheckDuplicateICRecord(home.getCustomerRecordList(), ic)
							&& !Customer.CheckDuplicateEmailRecord(home.getCustomerRecordList(), email)) {
						SubmitNewRecord();
						Customer.ConstructCustomerTable(home, home.getCustomerRecordList());
					}
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 204, 255));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnNewButton.setBounds(133, 601, 185, 43);
		add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				home.menuClicked(home.panelCustomer);

			}
		});
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setBackground(new Color(51, 204, 255));
		btnBack.setBounds(909, 92, 185, 43);
		add(btnBack);

		cboStatus.setBounds(98, 179, 114, 29);
		cboStatus.addItem("ACTIVE");
		cboStatus.addItem("INACTIVE");
		add(cboStatus);
	}

	// Insert new customer record
	void SubmitNewRecord() {
		try {
			// true is to not overwrite data. Append data.
			FileWriter writer = new FileWriter("Customer.txt", true);

			writer.write(icField.getText().trim() + ";" + firstNameField.getText().trim() + ";"
					+ lastNameField.getText().trim() + ";" + contactField.getText().trim() + ";"
					+ emailField.getText().trim() + ";" + cboStatus.getSelectedItem().toString() + "\n");

			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		home.getCustomerRecordList()
				.add(new CustomerRecord(icField.getText().trim(), firstNameField.getText().trim(),
						lastNameField.getText().trim(), contactField.getText().trim(), emailField.getText().trim(),
						cboStatus.getSelectedItem().toString()));
		JOptionPane.showMessageDialog(null, "New customer record has been added.");
		firstNameField.setText("");
		lastNameField.setText("");
		icField.setText("");
		contactField.setText("");
		emailField.setText("");

	}
}
