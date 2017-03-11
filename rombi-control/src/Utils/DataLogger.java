package Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * JavaDoc to come 2/23
 *
 * @author Joseph Hudson
 *
 */
public class DataLogger {

	private String file_path;
	private PrintWriter writer;
	private static DataLogger instance;
	
	/**
	 * 
	 * @return
	 */
	public static DataLogger getInstance() {
		if (instance == null) {
			try {
				instance = new DataLogger(System.currentTimeMillis()+".txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;

	}
	
	/**
	 * 
	 * @param file_path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private DataLogger(String file_path) throws FileNotFoundException, UnsupportedEncodingException {
		this.file_path = file_path;
		writer = new PrintWriter(file_path, "UTF-8");
		writeStuff("Logger started");
	}

	/**
	 * Writes a string to the current log file.
	 * @param text
	 */
	public synchronized void writeStuff(String text) {
		writer.println(System.currentTimeMillis() + " - " + text);
	}
	
	/**
	 * Writes a message about the logger shutting off
	 * and then closes the file.
	 */
	public synchronized void closeLog(){
		writeStuff("Logger closing");
		writer.close();
		instance = null;
	}
}
