import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

public class PanelBooking extends JPanel {
	JTable tableRoom;
	DefaultTableModel model;
	private Homepage home;
	int selectedRow = -1;

	JDateChooser checkinDateChooser = new JDateChooser();
	JDateChooser checkoutDateChooser = new JDateChooser();

	public PanelBooking(Homepage home) {
		this.home = home;
		setBackground(new Color(255, 255, 255));
		setSize(1104, 691);
		setLayout(null);

		JLabel lblCheckinDate = new JLabel("Check-in Date:");
		lblCheckinDate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCheckinDate.setBounds(282, 101, 114, 22);
		add(lblCheckinDate);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(0, 0, 1104, 81);
		add(panel);

		JLabel lblNewLabel_1_1 = new JLabel("Add Booking Records");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(438, 11, 277, 51);
		panel.add(lblNewLabel_1_1);

		JLabel lblCheckoutDate = new JLabel("Check-out Date:");
		lblCheckoutDate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCheckoutDate.setBounds(282, 174, 155, 22);
		add(lblCheckoutDate);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 247, 491, 363);
		add(scrollPane);

		tableRoom = new JTable();
		tableRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectedRow = tableRoom.getSelectedRow();

				String room = model.getValueAt(selectedRow, 0).toString();

				home.panelPayment.roomIDField.setText(room);
			}
		});
		tableRoom.setRowSelectionAllowed(false);
		tableRoom.setCellSelectionEnabled(false);
		tableRoom.setDefaultEditor(Object.class, null);
		model = new DefaultTableModel();
		Object[] column = { "Room ID" };
		// Object[] row = new Object[0];
		// Set Column View for model
		model.setColumnIdentifiers(column);
		// Set the model for table
		tableRoom.setModel(model);
		// Set the table view for scrollPane
		scrollPane.setViewportView(tableRoom);

		JButton btnSearchRoom = new JButton("Search Room");
		btnSearchRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkinDateChooser.getDate() == null || checkoutDateChooser.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Please select the dates.");

				} else {
					if (compareDate() == true) {
						try {
							try {
								searchAvailableRoom();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnSearchRoom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSearchRoom.setBounds(633, 199, 140, 37);
		add(btnSearchRoom);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a room first.");
				} else {
					autoIncrement();
					daysOfStay();
					home.panelPayment.receiptArea.setText("");
					home.panelPayment.firstNameField.setText("");
					home.panelPayment.lastNameField.setText("");
					home.panelPayment.icField.setText("");
					home.panelPayment.contactField.setText("");
					home.panelPayment.emailField.setText("");
				}
			}
		});
		btnNext.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNext.setBounds(652, 620, 121, 37);
		add(btnNext);

		checkinDateChooser.setBounds(282, 134, 140, 29);
		checkinDateChooser.getJCalendar().setMinSelectableDate(new Date());
		add(checkinDateChooser);
		
		checkoutDateChooser.setBounds(282, 207, 140, 29);
		checkoutDateChooser.getJCalendar().setMinSelectableDate(new Date());
		add(checkoutDateChooser);
	}

	//Check date selection logic
	boolean compareDate() {
		boolean compareResult = false;
		Date getCheckin = checkinDateChooser.getDate();
		Date getCheckout = checkoutDateChooser.getDate();

		Date getCurrentDate = new Date();

		String checkinRecord = new SimpleDateFormat("dd MMM yyyy").format(getCheckin);
		String checkoutRecord = new SimpleDateFormat("dd MMM yyyy").format(getCheckout);
		if (!getCheckin.before(getCheckout)) {
			JOptionPane.showMessageDialog(null, "Check-out Date cannot be earlier than Check-in Date.");
			compareResult = false;
		} else if (checkinRecord.equals(checkoutRecord)) {
			JOptionPane.showMessageDialog(null, "Check-in Date cannot be same as Check-out Date.");
			compareResult = false;
		} else {
			compareResult = true;
		}
		return compareResult;
	}
	
	//Method to increment the booking ID automatically whenever a new record is detected
	void autoIncrement() {	
		String bookingID = "";
		String subStr, newBookingID;
		int autoIncrementBooking, subID;
		
		if (home.getBookingDetailsList().size() > 0) {
			for (int i = 0; i < home.getBookingDetailsList().size(); i++) {
				bookingID = home.getBookingDetailsList().get(i).bookingID;
			}

			subStr = bookingID.substring(1);
			subID = Integer.valueOf(subStr);
			autoIncrementBooking = subID + 1;
			newBookingID = String.format("%04d", autoIncrementBooking);

			home.panelPayment.bookingIDField.setText("B" + newBookingID);
		} else {
			home.panelPayment.bookingIDField.setText("B0001");
		}

		
		home.menuClicked(home.panelPayment);
	}
	
	//Method to search available rooms based on date selection
	void searchAvailableRoom() throws ParseException, IOException {
		model.setRowCount(0);

		String[] dates = new String[2];
		String getRoomID;

		Date getCheckinDate = checkinDateChooser.getDate();
		Date getCheckoutDate = checkoutDateChooser.getDate();

		// Idea By RH Wong
		//Creating static room list
		String[] roomIDs = new String[20];
		int roomNumber = 101;
		
		for (int i = 0; i < roomIDs.length; i++) {
			//Prints R101 to R110 initially
			roomIDs[i] = "R" + roomNumber;
			roomNumber++;
			
			//When roomNumber hits 111, the remainder would be 11 and it satisfy
			// 11 > 10 criteria
			//Updates roomNumber become 201
			if (roomNumber % 100 > 10) {
				roomNumber = (roomNumber / 100 + 1) * 100 + 1;
			}
			model.addRow(new String[] { roomIDs[i] });
		}
		
		//If no booking details in the system, just print all roomID
		//and ignore the date checking method. 
		if (home.getBookingDetailsList().size() <= 0)
			return;
		for (int i = 0; i < home.getBookingDetailsList().size(); i++) {
			dates[0] = home.getBookingDetailsList().get(i).checkinDate;
			dates[1] = home.getBookingDetailsList().get(i).checkoutDate;
			getRoomID = home.getBookingDetailsList().get(i).roomID;

			Date checkinRecord = new SimpleDateFormat("dd MMM yyyy").parse(dates[0]);
			Date checkoutRecord = new SimpleDateFormat("dd MMM yyyy").parse(dates[1]);
			
			//Remove Room ID if the date from booking records and date selection clash
			//Proceed to display to Room ID if the dates did not clash
			if (!(getCheckoutDate.before(checkinRecord) || getCheckinDate.after(checkoutRecord))) {
				for (int y = 0; y < model.getRowCount(); y++) {
					if ((model.getValueAt(y, 0)).equals(getRoomID)) {
						model.removeRow(y);
					}
				}
			}	
		}
	}
	
	//Method to calculate nights of stay between check-out date and check-in date
	//Display results in text field
	void daysOfStay() {
		Date getCheckin = checkinDateChooser.getDate();
		Date getCheckout = checkoutDateChooser.getDate();

		String checkinDate = new SimpleDateFormat("dd MMM yyyy").format(getCheckin);
		String checkoutDate = new SimpleDateFormat("dd MMM yyyy").format(getCheckout);

		long diff = Math.abs(getCheckout.getTime() - getCheckin.getTime());
		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		String daysOfStay = Long.toString(days);
		home.panelPayment.daysField.setText(daysOfStay);
		home.panelPayment.checkinField.setText(checkinDate);
		home.panelPayment.checkoutField.setText(checkoutDate);
	}
}
