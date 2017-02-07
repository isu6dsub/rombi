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
	
	//This now works. Was some weird issue, I guess. Now I just need to make it 
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
