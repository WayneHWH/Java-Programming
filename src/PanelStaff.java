import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class PanelStaff extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confPasswordField;
	private JTextField userSearchField;
	JTable tableStaff;
	DefaultTableModel model;
	private Homepage home;
	private String usernameRecord, passwordRecord, roleRecord, currentUsername;
	private int selectedRow = -1, modelRow;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("rawtypes")
	JComboBox roleComboBox = new JComboBox();

	@SuppressWarnings("unchecked")
	public PanelStaff(Homepage home) {
		this.home = home;

		setBackground(new Color(255, 255, 255));
		setSize(1104, 691);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 1104, 81);
		add(panel);

		JLabel lblNoResultFound = new JLabel("");
		lblNoResultFound.setForeground(Color.RED);
		lblNoResultFound.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNoResultFound.setBounds(706, 240, 162, 22);
		add(lblNoResultFound);

		JLabel lblNewLabel_1 = new JLabel("Manage Staff ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1.setBackground(SystemColor.menu);
		lblNewLabel_1.setBounds(409, 11, 277, 51);
		panel.add(lblNewLabel_1);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblUsername.setBounds(69, 141, 114, 22);
		add(lblUsername);

		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(69, 174, 183, 29);
		add(usernameField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPassword.setBounds(69, 214, 114, 22);
		add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblConfirmPassword.setBounds(69, 307, 174, 22);
		add(lblConfirmPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(69, 247, 190, 29);
		add(passwordField);

		confPasswordField = new JPasswordField();
		confPasswordField.setBounds(69, 340, 190, 29);
		add(confPasswordField);

		userSearchField = new JTextField();
		userSearchField.setColumns(10);
		userSearchField.setBounds(513, 233, 183, 29);
		//Listener field for searching username
		userSearchField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				searchResult();
			}

			public void removeUpdate(DocumentEvent e) {
				searchResult();
			}

			public void insertUpdate(DocumentEvent e) {
				searchResult();
			}

			public void searchResult() {
				boolean resultFound = false;
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				tableStaff.setRowSorter(tr);
				for (int i = 0; i < home.getStaffRecordList().size(); i++) {
					if (home.getStaffRecordList().get(i).staffUsername.contains(userSearchField.getText())) {
						tr.setRowFilter(RowFilter.regexFilter(userSearchField.getText().trim()));
						resultFound = true;
					}
				}
				lblNoResultFound
						.setText(!resultFound && !userSearchField.getText().isEmpty() ? "No Result Found." : " ");
			}
		});
		add(userSearchField);

		JLabel lblSearchUsername = new JLabel("Search Username:");
		lblSearchUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSearchUsername.setBounds(513, 200, 169, 22);
		add(lblSearchUsername);

		JButton btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				if (!Staff.CheckDuplicateStaffRecord(home.getStaffRecordList(), username)) {
					SubmitNewRecord();
					Staff.ConstructStaffTable(home, home.getStaffRecordList());
				}
			}
		});
		btnAddStaff.setForeground(Color.WHITE);
		btnAddStaff.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAddStaff.setBackground(new Color(51, 204, 255));
		btnAddStaff.setBounds(69, 453, 185, 43);
		add(btnAddStaff);

		roleComboBox.setBounds(69, 395, 114, 35);
		roleComboBox.addItem("Staff");
		roleComboBox.addItem("Admin");
		add(roleComboBox);

		JButton btnEditStaff = new JButton("Edit Staff");
		btnEditStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String checkUsername = usernameField.getText().trim();
				String password = new String(passwordField.getPassword()).trim();
				String confPassword = new String(confPasswordField.getPassword()).trim();
				selectedRow = tableStaff.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a user.");
				} else if (usernameField.getText().isEmpty() || passwordField.getPassword().length == 0
						|| confPasswordField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "Please fill in all required fields.");

				} else if (!password.equals(confPassword)) {
					JOptionPane.showMessageDialog(null, "Password do not match. Please try again.");

				} else {

					if (currentUsername.equals(checkUsername)) {
						EditStaff();
					} else if (!currentUsername.equals(checkUsername)) {
						if (!Staff.CheckDuplicateStaffRecord(home.getStaffRecordList(), checkUsername)) {
							EditStaff();
						}
					}
				}
			}
		});
		btnEditStaff.setForeground(Color.WHITE);
		btnEditStaff.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEditStaff.setBackground(new Color(51, 204, 255));
		btnEditStaff.setBounds(69, 521, 185, 43);
		add(btnEditStaff);

		JButton btnDeleteStaff = new JButton("Delete Staff");
		btnDeleteStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRow = tableStaff.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a user.");
				} else {
					if (usernameField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
					} else {
						int selectedRow = tableStaff.getSelectedRow();
						int modelRow = tableStaff.convertRowIndexToModel(selectedRow);
						DeleteRow(modelRow);
					}
				}	
			}
		});
		btnDeleteStaff.setForeground(Color.WHITE);
		btnDeleteStaff.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDeleteStaff.setBackground(new Color(51, 204, 255));
		btnDeleteStaff.setBounds(69, 585, 185, 43);

		add(btnDeleteStaff);

		JButton btnClear = new JButton("Clear ");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameField.setText("");
				passwordField.setText("");
				confPasswordField.setText("");
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnClear.setBackground(new Color(51, 204, 255));
		btnClear.setBounds(269, 340, 106, 29);
		add(btnClear);

		CreateTable();
	}

	void CreateTable() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(513, 273, 422, 386);
		add(scrollPane);

		tableStaff = new JTable();
		tableStaff.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = tableStaff.getSelectedRow();
				int modelRow = tableStaff.convertRowIndexToModel(selectedRow);

				usernameRecord = model.getValueAt(modelRow, 0).toString();
				roleRecord = model.getValueAt(modelRow, 1).toString();
				passwordRecord = home.getStaffRecordList().get(modelRow).staffPassword.toString();
				currentUsername = model.getValueAt(modelRow, 0).toString();

				usernameField.setText(usernameRecord);
				passwordField.setText(passwordRecord);
				roleComboBox.setSelectedItem(roleRecord);
				confPasswordField.setText("");
			}
		});
		tableStaff.setDefaultEditor(Object.class, null);

		model = new DefaultTableModel();
		Object[] column = { "Username", "Role" };
		// Object[] row = new Object[0];
		// Set Column View for model
		model.setColumnIdentifiers(column);
		// Set the model for table
		tableStaff.setModel(model);
		// Set the table view for scrollPane
		scrollPane.setViewportView(tableStaff);
	}

	void SubmitNewRecord() {
		String username = usernameField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();
		String confPassword = new String(confPasswordField.getPassword()).trim();
		String role = roleComboBox.getSelectedItem().toString();

		if (username.equals("") || password.equals("") || confPassword.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
		} else if (password.equals(confPassword) == false) {
			JOptionPane.showMessageDialog(null, "Password do not match. Please try again.");

		} else {
			try {
				// true is to not overwrite data. Append data.
				FileWriter writer = new FileWriter("Staff.txt", true);

				writer.write(username + ";" + password + ";" + role + "\n");

				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			home.getStaffRecordList().add(new StaffRecord(username, password, role));
			JOptionPane.showMessageDialog(null, "New staff record has been added.");
			selectedRow = -1;
		}

	}

	void EditStaff() {
		String username = usernameField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();
		String role = roleComboBox.getSelectedItem().toString();
		modelRow = tableStaff.convertRowIndexToModel(selectedRow);

		home.getStaffRecordList().set(modelRow, new StaffRecord(username, password, role));
		try {
			Staff.SaveStaffRecord(home.getStaffRecordList());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Staff.ConstructStaffTable(home, home.getStaffRecordList());
		JOptionPane.showMessageDialog(null, "Staff Record has been edited.");
		selectedRow = -1;
	}

	void DeleteRow(int rowId) {

		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Delete?",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {

			model.removeRow(rowId);

			home.getStaffRecordList().remove(rowId);

			try {
				Staff.SaveStaffRecord(home.getStaffRecordList());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Record Deleted.");
			usernameField.setText("");
			passwordField.setText("");
			confPasswordField.setText("");
			selectedRow = -1;
		}

	}
}
