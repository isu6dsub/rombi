package Sub.Tools;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Provides a way to log data to file from the submarine
 *
 * @author Joseph Hudson
 * @author Vaughn Dorsey
 */
public class DataLogger {

	/**
	 * Used to write data to a file.
	 */
	private PrintWriter writer;
	
	/**
	 * Creates an instance of a datalogger.
	 * 
	 * @param file_path Path for the logfile to be saved at.
	 * @throws FileNotFoundException Throws is file isn't found. Shouldn't be thrown.
	 * @throws UnsupportedEncodingException Throws if encoding type isn't supported on system. Shouldn't be thrown for our usecase.
	 */
	public DataLogger(String file_path) throws FileNotFoundException, UnsupportedEncodingException {
		writer = new PrintWriter(file_path, "UTF-8");
		writeStuff("Logger started");
	}

	/**
	 * Writes a string to the current log file with a to the millisecond timestamp.
	 * @param text String to write to file.
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
	}
}
