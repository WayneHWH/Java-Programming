import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class PanelEditBooking extends JPanel {
	private Homepage home;
	JTextField bookingIDField;
	JTextField roomIDField;
	JTextField daysField;
	JTextField firstNameField;
	JTextField lastNameField;
	JTextField icField;
	JTextField contactField;
	JTextField emailField;
	JTextField serviceTaxField;
	JTextField roomChargesField;
	JTextField totalPriceField;
	JTextField tourismTaxField;
	JTextField checkinField;
	JTextField checkoutField;

	public PanelEditBooking(Homepage home) {
		setBackground(Color.WHITE);
		this.home = home;
		setSize(1104, 691);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 1104, 81);
		add(panel);

		JLabel lblNewLabel_1_1 = new JLabel("Edit Booking Record");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(407, 11, 277, 51);
		panel.add(lblNewLabel_1_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(0, 153, 255));
		panel_1_1.setBounds(38, 141, 163, 2);
		add(panel_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Booking Details");
		lblNewLabel_1_1_1_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1_1_1.setBounds(38, 92, 163, 43);
		add(lblNewLabel_1_1_1_1);

		JLabel lblBookingId = new JLabel("Booking ID:");
		lblBookingId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblBookingId.setBounds(38, 154, 114, 29);
		add(lblBookingId);

		bookingIDField = new JTextField();
		bookingIDField.setEditable(false);
		bookingIDField.setColumns(10);
		bookingIDField.setBounds(38, 187, 140, 29);
		add(bookingIDField);

		JLabel lblRoomId = new JLabel("Room ID:");
		lblRoomId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblRoomId.setBounds(206, 157, 114, 22);
		add(lblRoomId);

		roomIDField = new JTextField();
		roomIDField.setEditable(false);
		roomIDField.setColumns(10);
		roomIDField.setBounds(205, 187, 140, 29);
		add(roomIDField);

		JLabel lblDaysOfStay_2 = new JLabel("Check-in Date:");
		lblDaysOfStay_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDaysOfStay_2.setBounds(38, 227, 121, 22);
		add(lblDaysOfStay_2);

		JLabel lblDaysOfStay_1 = new JLabel("Check-out Date:");
		lblDaysOfStay_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDaysOfStay_1.setBounds(205, 227, 140, 22);
		add(lblDaysOfStay_1);

		JLabel lblDaysOfStay = new JLabel("Days of Stay:");
		lblDaysOfStay.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDaysOfStay.setBounds(38, 292, 114, 22);
		add(lblDaysOfStay);

		daysField = new JTextField();
		daysField.setEditable(false);
		daysField.setColumns(10);
		daysField.setBounds(38, 322, 140, 29);
		add(daysField);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(38, 411, 158, 2);
		add(panel_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Customer Details");
		lblNewLabel_1_1_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1_1.setBounds(38, 362, 158, 43);
		add(lblNewLabel_1_1_1);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFirstName.setBounds(38, 423, 114, 22);
		add(lblFirstName);

		firstNameField = new JTextField();
		firstNameField.setEditable(false);
		firstNameField.setColumns(10);
		firstNameField.setBounds(38, 456, 158, 29);
		add(firstNameField);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLastName.setBounds(236, 423, 114, 22);
		add(lblLastName);

		lastNameField = new JTextField();
		lastNameField.setEditable(false);
		lastNameField.setColumns(10);
		lastNameField.setBounds(236, 456, 158, 29);
		add(lastNameField);

		JLabel lblIcNo = new JLabel("IC No.:");
		lblIcNo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblIcNo.setBounds(38, 496, 114, 22);
		add(lblIcNo);

		icField = new JTextField();
		icField.setEditable(false);
		icField.setColumns(10);
		icField.setBounds(38, 529, 158, 29);
		// Set customer details determined by the IC, Listener field
		icField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCustomerDetails();
			}

			public void removeUpdate(DocumentEvent e) {
				// Do Nothing
			}

			public void insertUpdate(DocumentEvent e) {
				setCustomerDetails();
			}

			public void setCustomerDetails() {
				for (int i = 0; i < home.getCustomerRecordList().size(); i++) {
					if (icField.getText().equals(home.getCustomerRecordList().get(i).ic)) {
						firstNameField.setText(home.getCustomerRecordList().get(i).firstName);
						lastNameField.setText(home.getCustomerRecordList().get(i).lastName);
						contactField.setText(home.getCustomerRecordList().get(i).contactNumber);
						emailField.setText(home.getCustomerRecordList().get(i).email);
					}
				}
			}
		});
		add(icField);

		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblContactNumber.setBounds(235, 496, 140, 22);
		add(lblContactNumber);

		contactField = new JTextField();
		contactField.setEditable(false);
		contactField.setColumns(10);
		contactField.setBounds(236, 529, 158, 29);
		add(contactField);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEmail.setBounds(38, 569, 114, 22);
		add(lblEmail);

		emailField = new JTextField();
		emailField.setEditable(false);
		emailField.setColumns(10);
		emailField.setBounds(38, 602, 204, 29);
		add(emailField);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(0, 153, 255));
		panel_1_2.setBounds(451, 141, 158, 2);
		add(panel_1_2);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Payment Details");
		lblNewLabel_1_1_1_2.setForeground(new Color(0, 153, 255));
		lblNewLabel_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1_1_2.setBackground(SystemColor.menu);
		lblNewLabel_1_1_1_2.setBounds(451, 92, 158, 43);
		add(lblNewLabel_1_1_1_2);

		JLabel lblServiceTax = new JLabel("Service Tax (10%):");
		lblServiceTax.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblServiceTax.setBounds(451, 228, 140, 22);
		add(lblServiceTax);

		serviceTaxField = new JTextField();
		serviceTaxField.setEditable(false);
		serviceTaxField.setColumns(10);
		serviceTaxField.setBounds(451, 261, 140, 29);
		add(serviceTaxField);

		JLabel lblTotalPrice = new JLabel("Room Charges:");
		lblTotalPrice.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTotalPrice.setBounds(451, 141, 140, 42);
		add(lblTotalPrice);

		roomChargesField = new JTextField();
		roomChargesField.setEditable(false);
		roomChargesField.setColumns(10);
		roomChargesField.setBounds(451, 187, 140, 29);
		add(roomChargesField);

		JLabel lblTotalPrice_2 = new JLabel("Total Price:");
		lblTotalPrice_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTotalPrice_2.setBounds(451, 383, 140, 22);
		add(lblTotalPrice_2);

		totalPriceField = new JTextField();
		totalPriceField.setEditable(false);
		totalPriceField.setColumns(10);
		totalPriceField.setBounds(451, 416, 140, 29);
		add(totalPriceField);

		JLabel lblTourismTax = new JLabel("Tourism Tax:");
		lblTourismTax.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTourismTax.setBounds(451, 312, 114, 22);
		add(lblTourismTax);

		tourismTaxField = new JTextField();
		tourismTaxField.setEditable(false);
		tourismTaxField.setColumns(10);
		tourismTaxField.setBounds(451, 345, 140, 29);
		add(tourismTaxField);

		JTextArea receiptAreaEdit = new JTextArea();
		receiptAreaEdit.setBounds(700, 158, 308, 456);
		add(receiptAreaEdit);

		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBackground(new Color(0, 153, 255));
		panel_1_2_1.setBounds(424, 129, 3, 550);
		add(panel_1_2_1);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.menuClicked(home.panelManageBooking);
				home.panelManageBooking.setSelectedRow(-1);
				receiptAreaEdit.setText("");
			}
		});
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnBack.setBounds(970, 92, 121, 37);
		add(btnBack);

		JButton btnPrint = new JButton("Print\r \nReceipt\r\n");
		btnPrint.addActionListener(new ActionListener() {
			// Print the receipt
			public void actionPerformed(ActionEvent e) {
				if (!receiptAreaEdit.getText().equals("")) {
					try {
						receiptAreaEdit.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please generate receipt before printing.");
				}

			}
		});
		btnPrint.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnPrint.setBounds(796, 642, 124, 37);
		add(btnPrint);

		checkinField = new JTextField();
		checkinField.setEditable(false);
		checkinField.setColumns(10);
		checkinField.setBounds(38, 252, 140, 29);
		add(checkinField);

		checkoutField = new JTextField();
		checkoutField.setEditable(false);
		checkoutField.setColumns(10);
		checkoutField.setBounds(205, 252, 140, 29);
		add(checkoutField);

		JButton btnDeleteRecord = new JButton("Delete Record");
		btnDeleteRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteBookingRecord();
			}
		});
		btnDeleteRecord.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDeleteRecord.setBounds(451, 529, 140, 37);
		add(btnDeleteRecord);

		JButton btnGenerateReceipt = new JButton("Generate Receipt");
		btnGenerateReceipt.addActionListener(new ActionListener() {
			// Generate receipt with details
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();

				receiptAreaEdit.setText("                                     De' Luna Hotel\n");
				receiptAreaEdit.setText(
						receiptAreaEdit.getText() + "                      Lot D2 5, 20, Jalan Dutamas 10, " + "\n");
				receiptAreaEdit.setText(
						receiptAreaEdit.getText() + "                      Balakong, 43200 Cheras, Selangor" + "\n");
				receiptAreaEdit
						.setText(receiptAreaEdit.getText() + "===========================================" + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText() + " Billed To: \n\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText() + " Date of Issue: " + formatter.format(date) + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText() + " Customer Name: " + firstNameField.getText() + " "
						+ lastNameField.getText() + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText() + " Email: " + emailField.getText() + "\n" + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText()
						+ "----------------------------------------------------------------------------" + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText() + " Booking Details: \n\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText() + " Booking ID: " + bookingIDField.getText() + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText() + " Room Number: " + roomIDField.getText() + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText() + " Check-in Date: " + checkinField.getText() + "\n");
				receiptAreaEdit
						.setText(receiptAreaEdit.getText() + " Check-out Date: " + checkoutField.getText() + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText()
						+ "----------------------------------------------------------------------------" + "\n \n");
				receiptAreaEdit.setText(
						receiptAreaEdit.getText() + "                                          Room Charges:        "
								+ roomChargesField.getText() + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText()
						+ "                                          Tourism Tax:              "
						+ tourismTaxField.getText() + "\n");
				receiptAreaEdit.setText(
						receiptAreaEdit.getText() + "                                          Service Tax (10%):    "
								+ serviceTaxField.getText() + "\n \n");
				receiptAreaEdit.setText(receiptAreaEdit.getText()
						+ "                                          ==========================" + "\n");
				receiptAreaEdit.setText(receiptAreaEdit.getText()
						+ "                                          Total Price:                 "
						+ totalPriceField.getText());
			}
		});
		btnGenerateReceipt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnGenerateReceipt.setBounds(451, 467, 140, 37);
		add(btnGenerateReceipt);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(667, 141, 376, 490);
		add(panel_2);
	}

	// Method to delete the booking details
	void DeleteBookingRecord() {

		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?\n", "Delete?",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			if (JOptionPane.showConfirmDialog(null, "WARNING: You will be deleting the billing records too.",
					"Delete Confirmation?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				home.panelManageBooking.model.removeRow(home.panelManageBooking.getModelRow());
				home.getBookingDetailsList().remove(home.panelManageBooking.getModelRow());

				try {
					Booking.SaveBookingRecord(home.getBookingDetailsList());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Booking record has been deleted.");
				home.menuClicked(home.panelManageBooking);
				home.panelManageBooking.setSelectedRow(-1);
			}
		}
	}
}
