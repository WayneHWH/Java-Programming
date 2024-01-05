import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class FrameSelectCustomer extends JFrame {

	private JPanel contentPane;
	JTable tableCustomer;
	private JTextField icSearchField;
	private JScrollPane scrollPane;
	private String ic, firstName, lastName, contactNumber, email;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameSelectCustomer frame = new FrameSelectCustomer();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrameSelectCustomer(Homepage home) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 473);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 783, 295);
		contentPane.add(scrollPane);

		tableCustomer = new JTable();
		tableCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCustomer.addMouseListener(new MouseAdapter() {
			@Override
			//When a record is selected, fetch the data into their respective text fields.
			public void mousePressed(MouseEvent e) {
				int selectedRow = tableCustomer.getSelectedRow();
				int modelRow = tableCustomer.convertRowIndexToModel(selectedRow);

				ic = model.getValueAt(modelRow, 0).toString();
				firstName = model.getValueAt(modelRow, 1).toString();
				lastName = model.getValueAt(modelRow, 2).toString();
				contactNumber = model.getValueAt(modelRow, 3).toString();
				email = model.getValueAt(modelRow, 4).toString();

				home.panelPayment.icField.setText(ic);
				home.panelPayment.firstNameField.setText(firstName);
				home.panelPayment.lastNameField.setText(lastName);
				home.panelPayment.contactField.setText(contactNumber);
				home.panelPayment.emailField.setText(email);
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

		JLabel lblNewLabel = new JLabel("Customer IC:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 26, 114, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNoResultFound = new JLabel("");
		lblNoResultFound.setForeground(Color.RED);
		lblNoResultFound.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNoResultFound.setBounds(60, 99, 162, 22);
		contentPane.add(lblNoResultFound);

		icSearchField = new JTextField();
		icSearchField.setColumns(10);
		icSearchField.setBounds(10, 59, 264, 29);
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
		contentPane.add(icSearchField);
		
		//On load, construct the customer table
		Customer.ConstructFetchCustomerTable(this, home.getCustomerRecordList());
	}
}
