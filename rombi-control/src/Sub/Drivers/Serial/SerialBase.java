package Sub.Drivers.Serial;

import com.pi4j.wiringpi.Serial;

public abstract class SerialBase {
	protected String devName;
	protected int fileDescriptor;
	protected int baud;
	protected int lineLength;
	
	public SerialBase(String dev, int baud, int line){
		this.devName = dev;
		this.baud = baud;
		this.fileDescriptor = -1;
		this.lineLength = line;
	}
	
	public void open(){
		//For the base, don't actually make a connection unless we have serial emulation software available.
	}
	
	public String read(){
		//For the base, don't actually read anyting unless we have serial emulation software available.
		return "";
	}
	
	public void close(){
		
	}
}
