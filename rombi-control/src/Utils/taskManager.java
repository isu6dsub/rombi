package Utils;

import java.util.Scanner;

import Sub.Rombi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This provides a task list importer and manager for the
 * autonomous functionality of the submarine.
 * 
 * @Author Joseph Hudson
 * @Author Vaughn Dorsey
 * 
 */
public class taskManager {

	/**
	 * File path of the task list to be read.
	 */
	private String file_path;
	/**
	 * The full command list to be executed.
	 */
	private ArrayList<String> list;
	/**
	 * The current position in the command list.
	 */
	private int listPosition;
	
	/**
	 * Constructor for the task manager.
	 * 
	 * Saves the current filepath and then calls for
	 * the data to be read into an arrayList to be accessed later.
	 * 
	 * @param file_path
	 */
	public taskManager(String file_path) {
		this.file_path = file_path;
		list = readFile();
		listPosition = 0;
	}

	/**
	 * Reads the file and creates an ArrayList of the contents
	 * 
	 * TODO: Check incoming commands against an available command list and discard bad commands
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
			DataLogger.getInstance().writeStuff("Unable to open task file for autonomous functioning. System will exit.");
			Rombi.getInstance().stop();
			System.exit(0);
		}
		return list;
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
	 * Grabs the next command from the task list.
	 * If there isn't one available, the next command is to exit.
	 * 
	 * @return The next available command, or exit if no other commands are available.
	 */
	public String nextCommand(){
		if(listPosition == list.size()) return "exit";
		else {
			String command = list.get(listPosition);
			listPosition++;
			return command;
		}
	}
}
