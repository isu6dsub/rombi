package Sub.Drivers.Serial;

import java.io.IOException;
import java.nio.charset.Charset;

import com.pi4j.io.serial.*;

/**
 * This is the generic serial driver for use with the Pi
 * It is able to work with really any serial device with a
 * few modifications.
 * 
 * @author Vaughn Dorsey
 *
 */
public class PiSerial implements SerialBase{
	protected Serial serial;
	protected int lineLength;
	protected SerialConfig config;
	
	/**
	 * Constructs a serial device based on the Pi specific
	 * serial driver using the given information.
	 * 
	 * @param dev Unused, supposed to be the linux device path
	 * @param baud Unused, supposed to be the baud rate to use
	 * @param line Unused, supposed to be the length of input from the device.
	 */
	public PiSerial(String dev, int baud, int line) {
		serial = SerialFactory.createInstance();
		config = new SerialConfig();
		config.device("/dev/ttyUSB0").baud(Baud._57600);
	}
	
	/**
	 * Opens a serial connection based on the configuration.
	 */
	@Override
	public String open() {
		try {
			serial.open(config);
			return "Connected.";
		} catch (IOException e) {
			return "Connection Failure.";
		}
	}
	
	/**
	 * Reads from the serial connection character by character
	 * until a newline character is encountered. If the device messes
	 * up and doesn't finish transmitting a line, the line will be 
	 * dumped and a new line loaded.
	 */
	@Override
	public String read() {
		try {
			char lastChar = ' ';
			String ypr = "";
			serial.discardAll();
			while(lastChar != '\n'){
				lastChar = serial.read(1, Charset.defaultCharset()).toString().charAt(0);
				if(ypr.length() == 0 && lastChar != '#') lastChar = ' ';
				else if(lastChar == '\n') break;
				else if(lastChar == '#') ypr = lastChar + "";
				else ypr += lastChar + "";
			}
			return ypr;
		} catch (IllegalStateException e) {
			System.out.println("Illegal State.");
			return "Illegal State.";
		} catch (IOException e) {
			System.out.println("IO Exception.");
			return "IO Exception.";
		}
	}
	
	/**
	 * Closes the serial connection.
	 */
	@Override
	public void close() {
		try {
			serial.close();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
