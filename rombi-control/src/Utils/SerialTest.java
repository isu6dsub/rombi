package Utils;

import Sub.Drivers.Serial.PiSerial;

/**
 * Testing to see what the serial port ends up outputting and if the serial driver actually works.
 * @author vwdorsey
 *
 */
public class SerialTest {
	
	public static void main(String[] args){
		PiSerial serial = new PiSerial("/dev/ttyUSB0", 57600, 80);
		serial.open();
		while(true) {
			if(serial.hasData()){
				if(serial.amountDataAvailable() == 80){
					String output = serial.read();
					System.out.println(output);
				}
			}
		}
		//serial.close();
	}
}
