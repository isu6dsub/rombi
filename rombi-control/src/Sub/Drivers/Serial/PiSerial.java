package Sub.Drivers.Serial;

import java.io.IOException;
import java.nio.charset.Charset;

import com.pi4j.io.serial.*;


public class PiSerial implements SerialBase{
	protected Serial serial;
	protected int lineLength;
	protected SerialConfig config;
	
	public PiSerial(String dev, int baud, int line) {
		serial = SerialFactory.createInstance();
		config = new SerialConfig();
		config.device("/dev/ttyUSB0").baud(Baud._57600);
	}
	
	@Override
	public String open() {
		try {
			serial.open(config);
			return "Connected.";
		} catch (IOException e) {
			return "Connection Failure.";
		}
	}
	
	//This function isn't working
	//TODO: Try to determine what is being read and why it isn't being understood.
	//Or, if it is reading correctly, then figure out what the problem is.
	//If no solution is available, then replace the whole thing with a Python solution, as it did work.
	@Override
	public String read() {
		try {
			char lastChar = ' ';
			String ypr = "";
			while(lastChar != '\n'){
				lastChar = serial.read(1, Charset.defaultCharset()).toString().charAt(0);
				if(ypr.length() == 0 && lastChar == '\n') lastChar = ' ';
				else ypr += lastChar + "";
			}
			System.out.println(ypr);
			return ypr;
		} catch (IllegalStateException e) {
			System.out.println("Illegal State.");
			return "Illegal State.";
		} catch (IOException e) {
			System.out.println("IO Exception.");
			return "IO Exception.";
		}
	}
	
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
