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
	private static FileConfiguration instance;
	
	public static FileConfiguration getInstance(String filePath){
		if(instance == null) {
			instance = new FileConfiguration(filePath);
		}
		return instance;
	}

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
	private FileConfiguration(String file_path) {
		this.file_path = file_path;
		readFile();
	}

	/**
	 * Reads the file and creates a hashmap of the contents
	 * 
	 * @throws FileNotFoundException If the file isn't found, an empty map will be added.
	 */
	private void readFile() {
		try {
			map = new HashMap<String, Integer>();
			String k;
			Integer v;
			File file = new File(file_path);
			Scanner scanner;

			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				k = scanner.next();
				v = scanner.nextInt();
				if (v != null && k != null)
					map.put(k, v);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			map = new HashMap<String, Integer>();
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
	 * Retrieves a value from the configuration storage hashmap.
	 * 
	 * @param searchKey The key being looked for.
	 * @return Integer value for whatever key is passed in, if available.
	 */
	public int getConfigValue(String searchKey) {
		return map.get(searchKey);
	}
	
	/**
	 * Checks to see if a value exists in the configuration storage hashmap.
	 * 
	 * @param searchKey
	 * @return
	 */
	public boolean containsConfigValue(String searchKey) {
		return map.containsKey(searchKey);
	}
}
