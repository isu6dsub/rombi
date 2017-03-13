package Sub.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Joseph Hudson
 *
 *         File configuration takes a file and stores it in a hashmap to be accessed.
 */

public class FileConfiguration {

	/**
	 * File path being read from
	 */
	private String file_path;
	/**
	 * HashMap containing all of the config values from file.
	 */
	private HashMap<String, Integer> map;
	/**
	 * Singleton instance of a fileConfiguration object
	 */
	private static FileConfiguration instance;
	
	/**
	 * Returns a single instance of the fileConfiguration object.
	 * If the object hasn't been created, it will load it.
	 * 
	 * @param filePath File path to the config file.
	 * @return Instance of fileConfiguration.
	 */
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
	 * Retrieves a value from the configuration storage hashmap.
	 * 
	 * @param searchKey The desired configuration item.
	 * @return The value of the config item, if present.
	 */
	public int getConfigValue(String searchKey) {
		return map.get(searchKey);
	}
	
	/**
	 * Checks to see if a value exists in the configuration storage hashmap.
	 * 
	 * @param searchKey The desired configuration item.
	 * @return If the map has a matching item.
	 */
	public boolean containsConfigValue(String searchKey) {
		return map.containsKey(searchKey);
	}
}
