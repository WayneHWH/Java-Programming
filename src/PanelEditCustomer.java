import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class PanelEditCustomer extends JPanel {
	JTextField firstNameField;
	JTextField lastNameField;
	JTextField icField;
	JTextField contactField;
	JTextField emailField;

	private String currentIC, currentEmail;

	private Homepage home;

	/**
	 * Create the panel.
	 */
	JComboBox cboStatus = new JComboBox();

	public PanelEditCustomer(Homepage home) {
		this.home = home;
		setBackground(new Color(255, 255, 255));
		setSize(1104, 691);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 1104, 81);
		add(panel);

		JLabel lblNewLabel_1 = new JLabel("Edit Customer ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1.setBackground(SystemColor.menu);
		lblNewLabel_1.setBounds(432, 11, 277, 51);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Customer Details");
		lblNewLabel_1_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(91, 139, 277, 43);
		add(lblNewLabel_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(91, 188, 145, 2);
		add(panel_1);

		JLabel lblCustomerName = new JLabel("First Name:");
		lblCustomerName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCustomerName.setBounds(91, 251, 114, 22);
		add(lblCustomerName);

		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(91, 284, 264, 29);
		add(firstNameField);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLastName.setBounds(413, 251, 114, 22);
		add(lblLastName);

		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(413, 284, 264, 29);
		add(lastNameField);

		JLabel lblIc = new JLabel("IC No.:");
		lblIc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblIc.setBounds(91, 343, 114, 22);
		add(lblIc);

		icField = new JTextField();
		icField.setColumns(10);
		icField.setBounds(91, 376, 264, 29);
		// Listener field to check if the input is only numeric
		icField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					JOptionPane.showMessageDialog(null, "Enter numeric digits only. (0-9)");
					icField.setText("");
				}

			}
		});
		add(icField);

		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblContactNumber.setBounds(91, 437, 155, 22);
		add(lblContactNumber);

		contactField = new JTextField();
		contactField.setColumns(10);
		contactField.setBounds(91, 470, 264, 29);
		contactField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					JOptionPane.showMessageDialog(null, "Enter numeric digits only. (0-9)");
					contactField.setText("");
				}
			}
		});
		add(contactField);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEmail.setBounds(91, 528, 114, 22);
		add(lblEmail);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(91, 561, 264, 29);
		add(emailField);

		JButton btnEdit = new JButton("Save");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText().trim();
				String ic = icField.getText().trim();
				if (firstNameField.getText().equals("") || lastNameField.getText().equals("")
						|| icField.getText().equals("") || contactField.getText().equals("")
						|| emailField.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Please fill in all required information.");

				} else if (Customer.ValidateEmail(email)) {
					if (currentIC.equals(ic) && currentEmail.equals(email)) {
						UpdateCustomerRecord();
					} else if (!currentIC.equals(ic)) {
						if (!Customer.CheckDuplicateICRecord(home.getCustomerRecordList(), ic)) {
							UpdateCustomerRecord();
						}
					} else if (!currentEmail.equals(email)) {
						if (!Customer.CheckDuplicateEmailRecord(home.getCustomerRecordList(), email)) {
							UpdateCustomerRecord();
						}
					}
				}
			}
		});
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEdit.setBackground(new Color(51, 204, 255));
		btnEdit.setBounds(124, 613, 185, 43);
		add(btnEdit);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.menuClicked(home.panelCustomer);
				home.panelCustomer.setSelectedRow(-1);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBack.setBackground(new Color(51, 204, 255));
		btnBack.setBounds(897, 90, 185, 43);
		add(btnBack);

		cboStatus.setBounds(91, 201, 114, 29);
		cboStatus.addItem("ACTIVE");
		cboStatus.addItem("INACTIVE");
		add(cboStatus);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer record?",
						"Delete?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {

					for (int i = 0; i < home.getBookingDetailsList().size(); i++) {
						if (icField.getText().equals(home.getBookingDetailsList().get(i).ic)) {
							JOptionPane.showMessageDialog(null,
									"Cannot delete customer record.\n"
											+ "This customer has an existing booking record.\n"
											+ "Please flag this customer as 'INACTIVE' instead.");
							break;
						}

						if (i == home.getBookingDetailsList().size() - 1) {
							DeleteCustomerRecord();
						}
					}
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDelete.setBackground(new Color(51, 204, 255));
		btnDelete.setBounds(454, 613, 185, 43);
		add(btnDelete);

	}

	// Current IC and Email setter
	// For comparing if the current ic and email is same with the ic and email on
	// edit page.
	// If same proceed to update record.
	// Else run the duplicate check before updating record.
	public void setCurrentEmail(String currentEmail) {
		this.currentEmail = currentEmail;
	}

	public void setCurrentIC(String currentIC) {
		this.currentIC = currentIC;
	}

	// Update customer records method
	void UpdateCustomerRecord() {
		int selectedRow = home.panelCustomer.getModelRow();

		home.getCustomerRecordList().set(selectedRow,
				new CustomerRecord(icField.getText().trim(), firstNameField.getText().trim(),
						lastNameField.getText().trim(), contactField.getText().trim(), emailField.getText().trim(),
						cboStatus.getSelectedItem().toString()));
		try {
			Customer.SaveCustomerRecord(home.getCustomerRecordList());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Customer.ConstructCustomerTable(home, home.getCustomerRecordList());
		JOptionPane.showMessageDialog(null, "Customer record has been updated.");
		home.menuClicked(home.panelCustomer);
		home.panelCustomer.setSelectedRow(-1);

		UpdateIC();
	}

	// Delete customer record method
	void DeleteCustomerRecord() {

		home.panelCustomer.model.removeRow(home.panelCustomer.getModelRow());
		home.getCustomerRecordList().remove(home.panelCustomer.getModelRow());

		try {
			Customer.SaveCustomerRecord(home.getCustomerRecordList());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Customer record has been deleted.");
		home.menuClicked(home.panelCustomer);
		home.panelCustomer.setSelectedRow(-1);
	}

	// Update IC to existing booking details if any.
	void UpdateIC() {
		for (int i = 0; i < home.getBookingDetailsList().size(); i++) {
			if (home.getBookingDetailsList().get(i).ic.equals(currentIC)) {

				home.getBookingDetailsList().get(i).ic = icField.getText();

				try {
					Booking.SaveBookingRecord(home.getBookingDetailsList());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Booking.ConstructBookingTable(home, home.getBookingDetailsList());
			}
		}
	}
}
