package Utils;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Joseph Hudson
 *
 *         File configuration makes and returns a hashMap stored in a text file
 */

public class FileConfiguration {

	private String file_path;
	private HashMap<String, Integer> map;

	/**
	 * Creates a new FileConfiguration with a file path name and upon creation
	 * reads the file and creates a hashMap and stores that map to have the map
	 * accessible through the getMap() method.
	 * 
	 * the file's order of contents should be so that every line it alters
	 * between String one line and int the next 
	 * 
	 * String
	 * Int
	 * String
	 * Int
	 * etc.
	 * 
	 * @param file_path
	 */
	public FileConfiguration(String file_path) {
		this.file_path = file_path;
		map = readFile();
	}

	/**
	 * Reads the file and creates a hashmap of the contents
	 * 
	 * @return
	 */
	private HashMap<String, Integer> readFile() {
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		String k;
		Integer v;
		Scanner scanner = new Scanner(file_path);

		while (scanner.hasNext()) {
			k = null;
			v = null;
			k = scanner.nextLine();
			v = scanner.nextInt();
			if(v != null && k != null)
			returnMap.put(k, v);
		}

		scanner.close();
		return returnMap;
	}

	/**
	 * Edits the file_path the the file reader.
	 * 
	 * @param file_path
	 */
	public void changeFile(String file_path) {
		this.file_path = file_path;
	}

	/**
	 * @return
	 * 		a hashMap<String, Int>
	 */
	public HashMap<String, Integer> getMap() {
		return map;
	}
}
