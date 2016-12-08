package Sub.Drivers.Serial;

import java.io.IOException;
import java.nio.charset.Charset;

import com.pi4j.io.serial.*;


public class PiSerial implements SerialBase{
	protected String devName;
	protected final Serial serial;
	protected int baud;
	protected int lineLength;
	protected SerialConfig config;
	
	public PiSerial(String dev, int baud, int line) {
		config = new SerialConfig();
		serial = SerialFactory.createInstance();
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
	
	@Override
	public String read() {
		try {
			return serial.read(22, Charset.defaultCharset()).toString();
		} catch (IllegalStateException e) {
			return "Illegal State.";
		} catch (IOException e) {
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
