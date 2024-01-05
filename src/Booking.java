import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//Booking Details constructor
class BookingDetails {
	String bookingID, ic, roomID, checkinDate, checkoutDate, daysOfStay, roomCharges, tourismTax, serviceTax,
			totalPrice;

	public BookingDetails(String bookingID, String ic, String roomID, String checkinDate, String checkoutDate,
			String daysOfStay, String roomCharges, String tourismTax, String serviceTax, String totalPrice) {
		this.bookingID = bookingID;
		this.ic = ic;
		this.roomID = roomID;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.daysOfStay = daysOfStay;
		this.roomCharges = roomCharges;
		this.tourismTax = tourismTax;
		this.serviceTax = serviceTax;
		this.totalPrice = totalPrice;
	}
}

//Class that extends EntityData to inherit its method
public class Booking extends EntityData {
	//Reinitialize ArrayList from Booking Details file
	static void SetupBookingDetailsList(ArrayList<BookingDetails> bookingDetailsList) {

		for (String[] textLine : LoadRecordList(bookingDetailsList, "Booking")) {
			bookingDetailsList.add(new BookingDetails(textLine[0], textLine[1], textLine[2], textLine[3], textLine[4],
					textLine[5], textLine[6], textLine[7], textLine[8], textLine[9]));
		}
	}
	
	//Construct booking details table with ArrayList
	static void ConstructBookingTable(Homepage home, ArrayList<BookingDetails> bookingDetailsList) {

		// Clear model
		home.panelManageBooking.model.setRowCount(0);

		// Populate model with ArrayList
		for (int i = 0; i < bookingDetailsList.size(); i++)
			home.panelManageBooking.model
					.addRow(new String[] { bookingDetailsList.get(i).bookingID, bookingDetailsList.get(i).ic,
							bookingDetailsList.get(i).roomID, bookingDetailsList.get(i).checkinDate,
							bookingDetailsList.get(i).checkoutDate, bookingDetailsList.get(i).daysOfStay,
							bookingDetailsList.get(i).roomCharges, bookingDetailsList.get(i).tourismTax,
							bookingDetailsList.get(i).serviceTax, bookingDetailsList.get(i).totalPrice });

		home.panelManageBooking.tableBookingDetails.setModel(home.panelManageBooking.model);
	}
	
	//Update text file by deleting and creating a new text file and write the updated data from ArrayList
	static void SaveBookingRecord(ArrayList<BookingDetails> bookingDetailsList) throws IOException {
		(new File("Booking.txt")).delete();
		new File("Booking.txt");

		FileWriter writer = new FileWriter("Booking.txt", true);

		for (int i = 0; i < bookingDetailsList.size(); i++) {

			writer.write(bookingDetailsList.get(i).bookingID + ";" + bookingDetailsList.get(i).ic + ";"
					+ bookingDetailsList.get(i).roomID + ";" + bookingDetailsList.get(i).checkinDate + ";"
					+ bookingDetailsList.get(i).checkoutDate + ";" + bookingDetailsList.get(i).daysOfStay + ";"
					+ bookingDetailsList.get(i).roomCharges + ";" + bookingDetailsList.get(i).tourismTax + ";"
					+ bookingDetailsList.get(i).serviceTax + ";" + bookingDetailsList.get(i).totalPrice +"\n");
		}
		writer.close();
	}
	
	//Method to check if there is an existing booking record.
	static boolean CheckDuplicateIDRecord(ArrayList<BookingDetails> bookingDetailsList, String bookingIDField) {
		boolean hasDupeID = false;
		boolean result = false;
		for (int i = 0; i < bookingDetailsList.size(); i++) {
			if (bookingIDField.equals(bookingDetailsList.get(i).bookingID)) {
				hasDupeID = true;
				result = true;
			}
		}
		if (hasDupeID) {
			JOptionPane.showMessageDialog(null, "There is an existing booking record already.");
		}
		return result;
	}
}
