package Sub.Components;

import Sub.Drivers.PWM.TestPWMDevice;
import Sub.Drivers.Serial.*;

public class IMU {
	private SerialBase connection;
	/*
	 * The next three arrays hold XYZ values of the IMU's orientation
	 * Flat = what the first, flat surface measurement is (sub should be level when configuring). Mainly a reference.
	 * Level = if the sub is put at a new angle, set what the level version of this is. What the code will try to correct to.
	 * LastReading = the last reading gathered from the IMU. will need constantly updated.
	 */
	private double[] flat;
	private double[] level;
	private double[] lastReading;
	
	public IMU(){
		if(System.getProperty("os.arch").equals("amd64")){
			connection = new TestSerial("null", 0, 80);
			flat = new double[]{0.0, -9.0, 0.0};
			level = new double[]{0.0, -9.0, 0.0};
		}
		else {
			connection = new PiSerial("/dev/ttyUSB0", 57600, 80);
			String firstRead = connection.read();
			
		}
	}
	
	
}
