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
	
	public static DataLogger getInstance() {
		if (instance == null) {
			try {
				instance = new DataLogger("stuff");
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
	
	private DataLogger(String file_path) throws FileNotFoundException, UnsupportedEncodingException {
		this.file_path = file_path;
		writer = new PrintWriter(file_path, "UTF-8");
		writeStuff("Logger started");
	}

	public void writeStuff(String text) {
		writer.println(System.currentTimeMillis() + " - " + text);
	}
	
	public void closeLog(){
		writeStuff("Logger closing");
		writer.close();
		instance = null;
	}
}
