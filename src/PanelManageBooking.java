import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

public class PanelManageBooking extends JPanel {
	JTable tableBookingDetails;
	private JTextField searchField;
	DefaultTableModel model;
	private Homepage home;
	private String bookingID, roomID, days, ic, checkin, checkout, roomCharges, tourismTax, serviceTax, totalPrice;
	private int selectedRow = -1;
	private int modelRow;

	//Getter and setter methods
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public int getModelRow() {
		return modelRow;
	}


	/**
	 * Create the panel.
	 */
	JComboBox cboSearch = new JComboBox();
	JLabel lblNoResultFound = new JLabel("");

	public PanelManageBooking(Homepage home) {
		setBackground(Color.WHITE);
		this.home = home;
		setSize(1104, 691);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 272, 1084, 360);
		add(scrollPane);

		tableBookingDetails = new JTable();
		tableBookingDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableBookingDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectedRow = tableBookingDetails.getSelectedRow();
				modelRow = tableBookingDetails.convertRowIndexToModel(selectedRow);

				bookingID = model.getValueAt(modelRow, 0).toString();
				ic = model.getValueAt(modelRow, 1).toString();
				roomID = model.getValueAt(modelRow, 2).toString();
				checkin = model.getValueAt(modelRow, 3).toString();
				checkout = model.getValueAt(modelRow, 4).toString();
				days = model.getValueAt(modelRow, 5).toString();
				roomCharges = model.getValueAt(modelRow, 6).toString();
				tourismTax = model.getValueAt(modelRow, 7).toString();
				serviceTax = model.getValueAt(modelRow, 8).toString();
				totalPrice = model.getValueAt(modelRow, 9).toString();
			}
		});
		tableBookingDetails.setDefaultEditor(Object.class, null);

		model = new DefaultTableModel();
		Object[] column = { "Booking ID", "Customer IC", "Room ID", "Check-in Date", "Check-out Date", "Days of Stay",
				"Room Charges", "Tourism Tax", "Service Tax (10%)", "Total Price" };
		// Object[] row = new Object[0];
		// Set Column View for model
		model.setColumnIdentifiers(column);
		// Set the model for table
		tableBookingDetails.setModel(model);
		// Set the table view for scrollPane
		scrollPane.setViewportView(tableBookingDetails);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 1104, 81);
		add(panel);

		JLabel lblNewLabel_1_1 = new JLabel("Manage Booking Records");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(447, 11, 277, 51);
		panel.add(lblNewLabel_1_1);

		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(38, 196, 243, 29);
		add(searchField);
		//Listener field for search
		searchField.getDocument().addDocumentListener(new DocumentListener() {
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
				String getSearchType = cboSearch.getSelectedItem().toString();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				tableBookingDetails.setRowSorter(tr);
				//Check the types of searching methods
				for (int i = 0; i < home.getBookingDetailsList().size(); i++) {
					if (getSearchType == "Room ID") {
						if (home.getBookingDetailsList().get(i).roomID.contains(searchField.getText())) {
							tr.setRowFilter(RowFilter.regexFilter(searchField.getText().trim()));
							resultFound = true;
						}
					} else if (getSearchType == "Customer IC") {
						if (home.getBookingDetailsList().get(i).ic.contains(searchField.getText())) {
							tr.setRowFilter(RowFilter.regexFilter(searchField.getText().trim()));
							resultFound = true;
						}
					} else if (getSearchType == "Month"){
						String dateStr = home.getBookingDetailsList().get(i).checkinDate;
						String[] month = dateStr.split(" ");
						if (month[1].contains(searchField.getText())) {
							tr.setRowFilter(RowFilter.regexFilter(searchField.getText().trim()));
							resultFound = true;
						}
					}
				}
				lblNoResultFound.setText(!resultFound && !searchField.getText().isEmpty() ? "No Result Found." : " ");
			}
		});

		JButton btnViewRecord = new JButton("View Record");
		btnViewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a record.");
				} else {
					home.menuClicked(home.panelEditBooking);

					home.panelEditBooking.bookingIDField.setText(bookingID);
					home.panelEditBooking.roomIDField.setText(roomID);
					home.panelEditBooking.checkinField.setText(checkin);
					home.panelEditBooking.checkoutField.setText(checkout);
					home.panelEditBooking.daysField.setText(days);
					home.panelEditBooking.icField.setText(ic);
					home.panelEditBooking.roomChargesField.setText(roomCharges);
					home.panelEditBooking.tourismTaxField.setText(tourismTax);
					home.panelEditBooking.serviceTaxField.setText(serviceTax);
					home.panelEditBooking.totalPriceField.setText(totalPrice);
				}

			}
		});
		btnViewRecord.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnViewRecord.setBounds(954, 643, 140, 37);
		add(btnViewRecord);

		cboSearch.setBounds(38, 155, 147, 29);
		cboSearch.addItem("Room ID");
		cboSearch.addItem("Customer IC");
		cboSearch.addItem("Month");
		add(cboSearch);

		lblNoResultFound.setForeground(Color.RED);
		lblNoResultFound.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNoResultFound.setBounds(78, 239, 162, 22);
		add(lblNoResultFound);
	}
}
