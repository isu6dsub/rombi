package Sub.Drivers.Serial;

import com.pi4j.wiringpi.Serial;

public abstract class SerialBase {
	private String devName;
	private int fileDescriptor;
	private int baud;
	
	public SerialBase(String dev, int baud, int line){
		this.devName = dev;
		this.baud = baud;
		this.fileDescriptor = -1;
		this.lineLength = line;
	}
	
	public void initializeConnection(){
		fileDescriptor = Serial.serialOpen(devName, baud);
	}
	
	public byte[] read(int bytes){
		return Serial.serialGetBytes(arg0, arg1);
	}
	
	
}
