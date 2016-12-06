package Sub.Drivers.Serial;

import com.pi4j.wiringpi.Serial;

public class PiSerial extends SerialBase{

	public PiSerial(String dev, int baud, int line) {
		super(dev, baud, line);
	}
	
	@Override
	public void open() {
		fileDescriptor = Serial.serialOpen(devName, baud);
	}
	
	@Override
	public String read() {
		byte[] result = Serial.serialGetBytes(fileDescriptor, lineLength);
		String readableResult = "";
		for(byte b: result){
			readableResult += Byte.toString(b);
		}
		return readableResult;
	}
	
	@Override
	public void close() {
		Serial.serialClose(fileDescriptor);
	}
	
	@Override
	public boolean hasData() {
		return Serial.serialDataAvail(fileDescriptor)>0;
	}
	
	@Override
	public int amountDataAvailable() {
		return Serial.serialDataAvail(fileDescriptor);
	}
}
