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
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.print.*;

public class PanelPayment extends JPanel {
	JTextField bookingIDField;
	JTextField roomIDField;
	JTextField daysField;
	JTextField roomChargesField;
	JTextField firstNameField;
	JTextField lastNameField;
	JTextField icField;
	JTextField contactField;
	JTextField emailField;
	private JTextField serviceTaxField;
	private JTextField tourismTaxField;
	private JTextField totalPriceField;
	private double roomPrice = 350.00;
	private double tourismTax = 10.00;

	private Homepage home;
	JTextField checkoutField;
	JTextField checkinField;

	/**
	 * Create the panel.
	 */
	JTextArea receiptArea = new JTextArea();

	public PanelPayment(Homepage home) {
		setBackground(Color.WHITE);

		this.home = home;
		setSize(1104, 691);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 1104, 81);
		add(panel);

		JLabel lblNewLabel_1_1 = new JLabel("Make Payment");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(407, 11, 277, 51);
		panel.add(lblNewLabel_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(32, 412, 158, 2);
		add(panel_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Customer Details");
		lblNewLabel_1_1_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1_1.setBounds(32, 363, 158, 43);
		add(lblNewLabel_1_1_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(0, 153, 255));
		panel_1_1.setBounds(32, 142, 163, 2);
		add(panel_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Booking Details");
		lblNewLabel_1_1_1_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1_1_1.setBounds(32, 93, 163, 43);
		add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Payment Details");
		lblNewLabel_1_1_1_2.setForeground(new Color(0, 153, 255));
		lblNewLabel_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1_1_2.setBackground(SystemColor.menu);
		lblNewLabel_1_1_1_2.setBounds(435, 92, 158, 43);
		add(lblNewLabel_1_1_1_2);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(0, 153, 255));
		panel_1_2.setBounds(435, 141, 158, 2);
		add(panel_1_2);

		bookingIDField = new JTextField();
		bookingIDField.setEditable(false);
		bookingIDField.setColumns(10);
		bookingIDField.setBounds(32, 188, 140, 29);
		add(bookingIDField);

		JLabel lblBookingId = new JLabel("Booking ID:");
		lblBookingId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblBookingId.setBounds(32, 155, 114, 29);
		add(lblBookingId);

		roomIDField = new JTextField();
		roomIDField.setEditable(false);
		roomIDField.setColumns(10);
		roomIDField.setBounds(199, 188, 140, 29);
		add(roomIDField);

		JLabel lblRoomId = new JLabel("Room ID:");
		lblRoomId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblRoomId.setBounds(200, 158, 114, 22);
		add(lblRoomId);

		daysField = new JTextField();
		daysField.setEditable(false);
		daysField.setColumns(10);
		daysField.setBounds(32, 323, 140, 29);
		daysField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setPrice();
			}

			public void removeUpdate(DocumentEvent e) {
				// Do Nothing
			}

			public void insertUpdate(DocumentEvent e) {
				setPrice();
			}

			public void setPrice() {
				if (daysField.getText() != "") {
					int numDays = Integer.valueOf(daysField.getText());
					double roomCharges = roomPrice * numDays;
					double tourismCharges = tourismTax * numDays;
					double serviceCharges = roomCharges * 0.1;
					double totalCharges = roomCharges + tourismCharges + serviceCharges;

					roomChargesField.setText("RM " + String.format("%.2f", roomCharges));
					tourismTaxField.setText("RM " + String.format("%.2f", tourismCharges));
					serviceTaxField.setText("RM " + String.format("%.2f", serviceCharges));
					totalPriceField.setText("RM " + String.format("%.2f", totalCharges));
				}
			}
		});
		add(daysField);

		JLabel lblDaysOfStay = new JLabel("Days of Stay:");
		lblDaysOfStay.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDaysOfStay.setBounds(32, 293, 114, 22);
		add(lblDaysOfStay);

		JLabel lblTotalPrice = new JLabel("Room Charges:");
		lblTotalPrice.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTotalPrice.setBounds(435, 142, 140, 42);
		add(lblTotalPrice);

		roomChargesField = new JTextField();
		roomChargesField.setEditable(false);
		roomChargesField.setColumns(10);
		roomChargesField.setBounds(435, 188, 140, 29);
		add(roomChargesField);

		firstNameField = new JTextField();
		firstNameField.setEditable(false);
		firstNameField.setColumns(10);
		firstNameField.setBounds(32, 457, 158, 29);
		add(firstNameField);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFirstName.setBounds(32, 424, 114, 22);
		add(lblFirstName);

		lastNameField = new JTextField();
		lastNameField.setEditable(false);
		lastNameField.setColumns(10);
		lastNameField.setBounds(230, 457, 158, 29);
		add(lastNameField);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLastName.setBounds(230, 424, 114, 22);
		add(lblLastName);

		icField = new JTextField();
		icField.setEditable(false);
		icField.setColumns(10);
		icField.setBounds(32, 530, 158, 29);
		add(icField);

		JLabel lblIcNo = new JLabel("IC No.:");
		lblIcNo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblIcNo.setBounds(32, 497, 114, 22);
		add(lblIcNo);

		contactField = new JTextField();
		contactField.setEditable(false);
		contactField.setColumns(10);
		contactField.setBounds(230, 530, 158, 29);
		add(contactField);

		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblContactNumber.setBounds(229, 497, 140, 22);
		add(lblContactNumber);

		emailField = new JTextField();
		emailField.setEditable(false);
		emailField.setColumns(10);
		emailField.setBounds(32, 603, 204, 29);
		add(emailField);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEmail.setBounds(32, 570, 114, 22);
		add(lblEmail);

		serviceTaxField = new JTextField();
		serviceTaxField.setEditable(false);
		serviceTaxField.setColumns(10);
		serviceTaxField.setBounds(435, 261, 140, 29);
		add(serviceTaxField);

		JLabel lblServiceTax = new JLabel("Service Tax (10%):");
		lblServiceTax.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblServiceTax.setBounds(435, 228, 140, 22);
		add(lblServiceTax);

		tourismTaxField = new JTextField();
		tourismTaxField.setEditable(false);
		tourismTaxField.setColumns(10);
		tourismTaxField.setBounds(435, 342, 140, 29);
		add(tourismTaxField);

		JLabel lblTourismTax = new JLabel("Tourism Tax:");
		lblTourismTax.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTourismTax.setBounds(437, 310, 114, 22);
		add(lblTourismTax);

		JButton btnSelectExistingCustomer = new JButton("Select Existing Customer");
		btnSelectExistingCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameSelectCustomer selectCust = new FrameSelectCustomer(home);
				selectCust.setVisible(true);
			}
		});
		btnSelectExistingCustomer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSelectExistingCustomer.setBounds(32, 643, 204, 37);
		add(btnSelectExistingCustomer);

		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBackground(new Color(0, 153, 255));
		panel_1_2_1.setBounds(409, 115, 3, 550);
		add(panel_1_2_1);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.menuClicked(home.panelBooking);
				home.panelBooking.model.setRowCount(0);
				home.panelBooking.selectedRow = -1;
				receiptArea.setText("");
			}
		});
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnBack.setBounds(973, 92, 121, 37);
		add(btnBack);

		JLabel lblTotalPrice_2 = new JLabel("Total Price:");
		lblTotalPrice_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTotalPrice_2.setBounds(435, 384, 140, 22);
		add(lblTotalPrice_2);

		totalPriceField = new JTextField();
		totalPriceField.setEditable(false);
		totalPriceField.setColumns(10);
		totalPriceField.setBounds(435, 417, 140, 29);
		add(totalPriceField);

		JButton btnGenerateReceipt = new JButton("Add Record");
		btnGenerateReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!icField.getText().equals("")) {
					String bookingID = bookingIDField.getText();
					if (!Booking.CheckDuplicateIDRecord(home.getBookingDetailsList(), bookingID)) {
						SubmitNewRecord();
						Booking.ConstructBookingTable(home, home.getBookingDetailsList());
					}	
				} else {
					JOptionPane.showMessageDialog(null, "Please select an existing customer.");
				}
			
			}
		});
		btnGenerateReceipt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnGenerateReceipt.setBounds(433, 479, 142, 37);
		add(btnGenerateReceipt);

		JLabel lblDaysOfStay_1 = new JLabel("Check-out Date:");
		lblDaysOfStay_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDaysOfStay_1.setBounds(199, 228, 140, 22);
		add(lblDaysOfStay_1);

		checkoutField = new JTextField();
		checkoutField.setEditable(false);
		checkoutField.setColumns(10);
		checkoutField.setBounds(199, 258, 140, 29);
		add(checkoutField);

		JLabel lblDaysOfStay_2 = new JLabel("Check-in Date:");
		lblDaysOfStay_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDaysOfStay_2.setBounds(32, 228, 121, 22);
		add(lblDaysOfStay_2);

		checkinField = new JTextField();
		checkinField.setEditable(false);
		checkinField.setColumns(10);
		checkinField.setBounds(32, 258, 140, 29);
		add(checkinField);

		receiptArea.setBounds(700, 158, 308, 456);
		add(receiptArea);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(618, 154, 463, 478);
		add(panel_2);

		JButton btnPrint = new JButton("Print\r \nReceipt\r\n");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!receiptArea.getText().equals("")) {
					try {
						receiptArea.print();
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
		btnPrint.setBounds(802, 643, 121, 37);
		add(btnPrint);

	}

	void SubmitNewRecord() {
		String bookingID = bookingIDField.getText();
		String roomID = roomIDField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String email = emailField.getText();
		String days = daysField.getText();
		String ic = icField.getText();
		String checkin = checkinField.getText();
		String checkout = checkoutField.getText();
		String roomCharges = roomChargesField.getText();
		String tourismTax = tourismTaxField.getText();
		String serviceTax = serviceTaxField.getText();
		String totalPrice = totalPriceField.getText();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

			try {
				// true is to not overwrite data. Append data.
				FileWriter writer = new FileWriter("Booking.txt", true);
				writer.write(bookingID + ";" + ic + ";" + roomID + ";" + checkin + ";" + checkout + ";" + days + ";"
						+ roomCharges + ";" + tourismTax + ";" + serviceTax + ";" + totalPrice + "\n");

				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			home.getBookingDetailsList().add(new BookingDetails(bookingID, ic, roomID, checkin, checkout, days, roomCharges,
					tourismTax, serviceTax, totalPrice));
			JOptionPane.showMessageDialog(null, "New booking record has been added.");
		

		receiptArea.setText("                                     De' Luna Hotel\n");
		receiptArea.setText(receiptArea.getText() + "                      Lot D2 5, 20, Jalan Dutamas 10, " + "\n");
		receiptArea.setText(receiptArea.getText() + "                      Balakong, 43200 Cheras, Selangor" + "\n");
		receiptArea.setText(receiptArea.getText() + "===========================================" + "\n");
		receiptArea.setText(receiptArea.getText() + " Billed To: \n\n");
		receiptArea.setText(receiptArea.getText() + " Date of Issue: " + formatter.format(date) + "\n");
		receiptArea.setText(receiptArea.getText() + " Customer Name: " + firstName + " " + lastName + "\n");
		receiptArea.setText(receiptArea.getText() + " Email: " + email + "\n" + "\n");
		receiptArea.setText(receiptArea.getText()
				+ "----------------------------------------------------------------------------" + "\n");
		receiptArea.setText(receiptArea.getText() + " Booking Details: \n\n");
		receiptArea.setText(receiptArea.getText() + " Booking ID: " + bookingID + "\n");
		receiptArea.setText(receiptArea.getText() + " Room Number: " + roomID + "\n");
		receiptArea.setText(receiptArea.getText() + " Check-in Date: " + checkin + "\n");
		receiptArea.setText(receiptArea.getText() + " Check-out Date: " + checkout + "\n\n");
		receiptArea.setText(receiptArea.getText()
				+ "----------------------------------------------------------------------------" + "\n");
		receiptArea.setText(receiptArea.getText() + "                                          Room Charges:        "
				+ roomChargesField.getText() + "\n");
		receiptArea.setText(
				receiptArea.getText() + "                                          Tourism Tax:              "
						+ tourismTaxField.getText() + "\n");
		receiptArea.setText(
				receiptArea.getText() + "                                          Service Tax (10%):    "
						+ serviceTaxField.getText() + "\n \n");
		receiptArea.setText(receiptArea.getText()
				+ "                                          ==========================" + "\n");
		receiptArea.setText(
				receiptArea.getText() + "                                          Total Price:                 "
						+ totalPriceField.getText());
	}
}

