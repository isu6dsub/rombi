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
	
	@Override
	public String read() {
		try {
			String read = serial.read(22, Charset.defaultCharset()).toString();
			System.out.println(read);
			return read;
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
