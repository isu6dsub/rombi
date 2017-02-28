package Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * JavaDoc to come 2/23
 *
 * @author Joseph Hudson
 *
 */
public class DataLogger {

	private String file_path;
	private String[] text;

	public DataLogger(String file_path, String[] text) {
		this.file_path = file_path;
		this.text = text;
		writeStuff();
	}

	private void writeStuff() {
		try {
			PrintWriter writer = new PrintWriter(file_path, "UTF-8");
			for (int i = 0; i < text.length; i++) {
				writer.println(text[i]);
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error occured, but I have no idea what might've gone wrong.");
		}
	}
}
