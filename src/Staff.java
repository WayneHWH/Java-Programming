import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Staff extends EntityData {
	//Reinitialize ArrayList from Staff file
	static void SetupStaffRecordList(ArrayList<StaffRecord> staffRecordList) {

		for (String[] textLine : LoadRecordList(staffRecordList, "Staff")) {
			staffRecordList.add(new StaffRecord(textLine[0], textLine[1], textLine[2]));
		}
	}
	
	//Constructing staff table with ArrayList
	static void ConstructStaffTable(Homepage home, ArrayList<StaffRecord> staffRecordList) {

		// Clear model
		home.panelStaff.model.setRowCount(0);

		// Populate model with ArrayList
		for (int i = 0; i < staffRecordList.size(); i++)
			home.panelStaff.model
					.addRow(new String[] { staffRecordList.get(i).staffUsername, staffRecordList.get(i).staffRole });

		home.panelStaff.tableStaff.setModel(home.panelStaff.model);
	}

	//Update customer list by deleting and creating a new text file
	static void SaveStaffRecord(ArrayList<StaffRecord> staffRecordList) throws IOException {
		(new File("Staff.txt")).delete();
		new File("Staff.txt");

		FileWriter writer = new FileWriter("Staff.txt", true);

		for (int i = 0; i < staffRecordList.size(); i++) {

			writer.write(staffRecordList.get(i).staffUsername + ";" + staffRecordList.get(i).staffPassword + ";"
					+ staffRecordList.get(i).staffRole + "\n");

		}
		writer.close();
	}
	
	//Check if an existing staff has duplicated entry
	static boolean CheckDuplicateStaffRecord(ArrayList<StaffRecord> staffRecordList, String usernameField) {
		boolean hasDupeStaff = false;
		boolean result = false;
		for (int i = 0; i < staffRecordList.size(); i++) {
			if (usernameField.equals(staffRecordList.get(i).staffUsername)) {
				hasDupeStaff = true;
				result = true;
			}
		}
		if (hasDupeStaff) {
			JOptionPane.showMessageDialog(null, "The staff that you are trying to insert had already existed.\n"
					+ "Please change another username.");
		}
		return result;
	}
}

//StaffRecord constructor
class StaffRecord {
	String staffUsername, staffPassword, staffRole;

	public StaffRecord(String username, String password, String role) {
		staffUsername = username;
		staffPassword = password;
		staffRole = role;
	}

	public boolean checkCredentials() {
		File file = new File("Staff.txt");
		boolean credentialExist = false;

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String accountLine = sc.nextLine();
				String[] textAccountLine = accountLine.split(";");

				if (textAccountLine[0].equals(staffUsername.trim()) && textAccountLine[1].equals(staffPassword.trim())
						&& textAccountLine[2].equals(staffRole)) {
					credentialExist = true;
					break;
				}
			}
			sc.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return credentialExist;
	}
}
