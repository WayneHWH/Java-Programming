import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class EntityData {
	
	static protected <T> ArrayList<String []> LoadRecordList(ArrayList<T> recordList, String fileName) {
		//Idea by RH Wong
		//Generic method for retrieving data from text files by passing the correct ArrayList type.
		//It is a reusable code.
		File file = new File(fileName + ".txt");
		ArrayList<String []> returnList = new ArrayList<String[]>();

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] textLine = line.split(";");
				returnList.add(textLine);
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return returnList;
	}
}
