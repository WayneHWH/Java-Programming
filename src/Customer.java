import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

class CustomerRecord {
	String ic, firstName, lastName, contactNumber, email, status;

	public CustomerRecord(String ic, String firstName, String lastName, String contactNumber, String email,
			String status) {
		this.ic = ic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.email = email;
		this.status = status;
	}	
}

public class Customer extends EntityData {
	// Declaring Regex syntax for email format validation
	private static final String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	// Reinitialize ArrayList from Customer file
	static void SetupCustomerRecordList(ArrayList<CustomerRecord> customerRecordList) {

		// OP Code
		for (String[] textLine : LoadRecordList(customerRecordList, "Customer"))
			customerRecordList.add(
					new CustomerRecord(textLine[0], textLine[1], textLine[2], textLine[3], textLine[4], textLine[5]));
	}
	
	//Constructing customer table with ArrayList
	static void ConstructCustomerTable(Homepage home, ArrayList<CustomerRecord> customerRecordList) {

		// Clear model
		home.panelCustomer.model.setRowCount(0);

		// Populate model with ArrayList
		for (int i = 0; i < customerRecordList.size(); i++)
			home.panelCustomer.model
					.addRow(new String[] { customerRecordList.get(i).ic, customerRecordList.get(i).firstName,
							customerRecordList.get(i).lastName, customerRecordList.get(i).contactNumber,
							customerRecordList.get(i).email, customerRecordList.get(i).status });

		home.panelCustomer.tableCustomer.setModel(home.panelCustomer.model);
	}
	
	//Populate model for selecting customer with active status
	static void ConstructFetchCustomerTable(FrameSelectCustomer selectCust,
			ArrayList<CustomerRecord> customerRecordList) {

		// Clear model
		selectCust.model.setRowCount(0);
		
		//Declare an active string
		String activeStatus = "ACTIVE";

		// Populate model with list
		for (int i = 0; i < customerRecordList.size(); i++)
			if (customerRecordList.get(i).status.equals(activeStatus)) {
				selectCust.model
						.addRow(new String[] { customerRecordList.get(i).ic, customerRecordList.get(i).firstName,
								customerRecordList.get(i).lastName, customerRecordList.get(i).contactNumber,
								customerRecordList.get(i).email, customerRecordList.get(i).status });
			}

		selectCust.tableCustomer.setModel(selectCust.model);
	}

	//Update customer list
	static void SaveCustomerRecord(ArrayList<CustomerRecord> customerRecordList) throws IOException {
		(new File("Customer.txt")).delete();
		new File("Customer.txt");

		FileWriter writer = new FileWriter("Customer.txt", true);

		for (int i = 0; i < customerRecordList.size(); i++) {

			writer.write(customerRecordList.get(i).ic + ";" + customerRecordList.get(i).firstName + ";"
					+ customerRecordList.get(i).lastName + ";" + customerRecordList.get(i).contactNumber + ";"
					+ customerRecordList.get(i).email + ";" + customerRecordList.get(i).status + "\n");
		}
		writer.close();
	}
	
	//Validate email format 
	static boolean ValidateEmail(String emailField) {
		boolean emailResult = false;
		String email = emailField;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches() == false) {
			JOptionPane.showMessageDialog(null,
					"Email is either incomplete or having an incorrect format. Please try again.");
			emailResult = false;
		} else {
			emailResult = true;
		}
		return emailResult;
	}

	//Check if there is an existing IC record
	static boolean CheckDuplicateICRecord(ArrayList<CustomerRecord> customerRecordList, String icField) {
		boolean hasDupeIC = false;
		boolean result = false;
		for (int i = 0; i < customerRecordList.size(); i++) {
			if (icField.equals(customerRecordList.get(i).ic)) {
				hasDupeIC = true;
				result = true;
			}
		}
		if (hasDupeIC) {
			JOptionPane.showMessageDialog(null, "The IC that you are trying to insert had already existed.");
		}
		return result;
	}

	//Check if there is an existing email record
	static boolean CheckDuplicateEmailRecord(ArrayList<CustomerRecord> customerRecordList, String emailField) {
		boolean hasDupeEmail = false;
		boolean result = false;
		for (int i = 0; i < customerRecordList.size(); i++) {
			if (emailField.equals(customerRecordList.get(i).email)) {
				hasDupeEmail = true;
				result = true;
			}
		}
		if (hasDupeEmail) {
			JOptionPane.showMessageDialog(null, "The email that you are trying to insert had already existed.");
		}
		return result;
	}

}


