import java.awt.Font;

import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ListSelectionModel;

public class PanelCustomer extends JPanel {
	private JTextField icSearchField;
	DefaultTableModel model;
	JTable tableCustomer;
	private String ic, firstName, lastName, contactNumber, email, status;
	private int selectedRow = -1, modelRow;
	private Homepage home;
	
	/**
	 * Create the panel.
	 */

	public PanelCustomer(Homepage home) {
		this.home = home;

		JLabel lblNoResultFound = new JLabel("");
		lblNoResultFound.setForeground(Color.RED);
		lblNoResultFound.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNoResultFound.setBounds(88, 225, 162, 22);
		add(lblNoResultFound);

		setBackground(new Color(255, 255, 255));
		setSize(1104, 691);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Customer IC:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(42, 152, 114, 22);
		add(lblNewLabel);

		icSearchField = new JTextField();
		icSearchField.setBounds(42, 185, 264, 29);
		add(icSearchField);
		icSearchField.setColumns(10);
		//Search Listener
		icSearchField.getDocument().addDocumentListener(new DocumentListener() {
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
				tableCustomer.setRowSorter(tr);
				for (int i = 0; i < home.getCustomerRecordList().size(); i++) {
					if (home.getCustomerRecordList().get(i).ic.contains(icSearchField.getText())) {
						tr.setRowFilter(RowFilter.regexFilter(icSearchField.getText().trim()));
						resultFound = true;
					}
				}
				lblNoResultFound.setText(!resultFound && !icSearchField.getText().isEmpty() ? "No Result Found." : " ");
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 1104, 81);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Manage Customer Details");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(417, 11, 277, 51);
		panel.add(lblNewLabel_1);

		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.setForeground(new Color(255, 255, 255));
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.menuClicked(home.panelAddCustomer);
			}
		});
		btnAddCustomer.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAddCustomer.setBackground(new Color(51, 204, 255));
		btnAddCustomer.setBounds(725, 209, 162, 31);
		add(btnAddCustomer);

		JButton btnEditCustomer = new JButton("Edit Customer");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a record.");
				} else {
					home.menuClicked(home.panelEditCustomer);

					home.panelEditCustomer.firstNameField.setText(firstName);
					home.panelEditCustomer.lastNameField.setText(lastName);
					home.panelEditCustomer.icField.setText(ic);
					home.panelEditCustomer.contactField.setText(contactNumber);
					home.panelEditCustomer.emailField.setText(email);
					home.panelEditCustomer.cboStatus.setSelectedItem(status);
					home.panelEditCustomer.setCurrentIC(ic);
					home.panelEditCustomer.setCurrentEmail(email);
				}

			}
		});
		btnEditCustomer.setForeground(new Color(255, 255, 255));
		btnEditCustomer.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEditCustomer.setBackground(new Color(51, 204, 255));
		btnEditCustomer.setBounds(932, 209, 162, 31);
		add(btnEditCustomer);

		//Populate JTable
		CreateTable();

	}


	//Setter and Getter Method
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public int getModelRow() {
		return modelRow;
	}

	//Populate JTable
	void CreateTable() {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 262, 1084, 418);
		add(scrollPane);

		tableCustomer = new JTable();
		tableCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCustomer.setDefaultEditor(Object.class, null);

		tableCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e2) {
				selectedRow = tableCustomer.getSelectedRow();
				modelRow = tableCustomer.convertRowIndexToModel(selectedRow);

				ic = model.getValueAt(modelRow, 0).toString();
				firstName = model.getValueAt(modelRow, 1).toString();
				lastName = model.getValueAt(modelRow, 2).toString();
				contactNumber = model.getValueAt(modelRow, 3).toString();
				email = model.getValueAt(modelRow, 4).toString();
				status = model.getValueAt(modelRow, 5).toString();
				
			}
		});
		model = new DefaultTableModel();
		Object[] column = { "IC", "First Name", "Last Name", "Contact Number", "Email", "Status" };
		// Object[] row = new Object[0];
		// Set Column View for model
		model.setColumnIdentifiers(column);
		// Set the model for table
		tableCustomer.setModel(model);
		// Set the table view for scrollPane
		scrollPane.setViewportView(tableCustomer);

	}
}
