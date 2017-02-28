package Utils;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class taskManager {

	private String file_path;
	private ArrayList<String> list;
	
	/**
	 * reads an ArrayList and does stuff because of it.
	 * 
	 * @param file_path
	 */
	public taskManager(String file_path) {
		this.file_path = file_path;
		list = readFile();
	}

	/**
	 * Reads the file and creates an ArrayList of the contents
	 * 
	 * @return
	 */
	private ArrayList<String> readFile() {
		try {
			ArrayList<String> returnList = new ArrayList<String>();
			String k;
			File file = new File(file_path);
			Scanner scanner;
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				k = null;
				k = scanner.nextLine();
				if (k != null)
					returnList.add(k);
			}
			scanner.close();
			return returnList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Edits the file_path the the task manager.
	 * 
	 * @param file_path
	 */
	public void changeFile(String file_path) {
		this.file_path = file_path;
	}

	/**
	 * @return an ArrayList<String>
	 */
	public ArrayList<String> getList() {
		return list;
	}
}
