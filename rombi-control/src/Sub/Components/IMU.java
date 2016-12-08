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
	 * 
	 * Values are stored as yaw, pitch, roll in radians.
	 */
	private double[] flat;
	private double[] level;
	private double[] lastReading;
	
	public IMU(){
		if(System.getProperty("os.arch").equals("amd64")){
			connection = new TestSerial("null", 0, 22);
			flat = new double[]{0.0, -9.0, 0.0};
			level = new double[]{0.0, -9.0, 0.0};
		}
		else {
			connection = new PiSerial("/dev/ttyUSB0", 57600, 22);
			flat = new double[]{0.00, 0.00, 0.00};
			level = parseSerial(connection.read());
			lastReading = level.clone();
		}
	}
	
	public void checkAndCorrect(Motor[] motors){
		lastReading = parseSerial(connection.read());
		if(lastReading[0] > (level[0] + 0.05)) {
			
		}
		else if(lastReading[0] < (level[0] - 0.05)) {
			
		}
	}
	
	private double[] parseSerial(String input){
		double[] result = new double[3];
		String[] firstPass = input.split("=");
		if(firstPass.length == 2){
			String[] secondPass = firstPass[1].split(",");
			if(secondPass.length == 3){
				for(int i = 0; i < 3; i++){
					result[i] = Double.parseDouble(secondPass[i]);
				}
			}
			else{
				result[0]=4.0;
			}
		}
		else{
			result[0]=4.0;
		}
		return result;
	}
	
}
