package Utils;

import Sub.Drivers.Serial.PiSerial;

import java.io.IOException;
import java.nio.charset.Charset;

import com.pi4j.io.serial.*;

/**
 * Testing to see what the serial port ends up outputting and if the serial driver actually works.
 * @author vwdorsey
 *
 */
public class SerialTest {
	
	public static void main(String[] args){
		final Serial serial = SerialFactory.createInstance();
		serial.addListener(new SerialDataEventListener()
				{
					@Override
					public void dataReceived(SerialDataEvent arg0) {
						System.out.println("Recieved");
						
					}
				});
		try {
			SerialConfig config = new SerialConfig();
			
			config.device("/dev/ttyUSB0").baud(Baud._57600);
			
			System.out.println("Connecting....");
			
			serial.open(config);
			
			System.out.println("Connected.");
			
			while(true){
				System.out.println(serial.read(22, Charset.defaultCharset()).toString());
			}
		}
		catch(IOException e){
			System.out.println("Error.");
			return;
		}
	}
}