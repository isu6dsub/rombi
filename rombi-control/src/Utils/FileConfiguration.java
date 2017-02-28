package Utils;

import java.io.File;
import java.io.FileNotFoundException;
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
	 * String Int String Int etc.
	 * 
	 * @param file_path
	 * @throws FileNotFoundException
	 */
	public FileConfiguration(String file_path) {
		this.file_path = file_path;
		readFile();
	}

	/**
	 * Reads the file and creates a hashmap of the contents
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	private void readFile() {
		try {
			HashMap<String, Integer> newMap = new HashMap<String, Integer>();
			String k;
			Integer v;
			File file = new File(file_path);
			Scanner scanner;

			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				k = scanner.nextLine();
				v = scanner.nextInt();
				if (v != null && k != null)
					newMap.put(k, v);
			}

			scanner.close();
			map = newMap;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edits the file_path the the file reader. After that, it creates a hashmap
	 * based upon the new file_path and stores the new hashmap instead of the
	 * old.
	 * 
	 * @param file_path
	 */
	public void changeFile(String file_path) {
		this.file_path = file_path;
		readFile();
	}

	/**
	 * @return a hashMap<String, Int> made by the most recent file_path.
	 */
	public HashMap<String, Integer> getMap() {
		return map;
	}
}
