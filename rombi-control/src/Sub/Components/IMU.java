package Sub.Components;

import Sub.Rombi;
import Sub.Drivers.Serial.*;
/**
 * This class contains all of the code necessary to interact with
 * the Sparkfun Razor 9DOF IMU that we have been using to help keep
 * the submarine level and to correct the submarine so that it doesn't
 * deviate too far from being level.
 * 
 * Values are stored as yaw, pitch, roll in radians.
 * 
 * @author Vaughn Dorsey
 *
 */
public class IMU {
	
	/**
	 * The serial over USB connection for the IMU
	 */
	private SerialBase connection;
	
	/**
	 * if the sub is put at a new angle, set what the level version of this is. What the code will try to correct to.
	 */
	private double[] level;
	/**
	 * The last reading gathered from the IMU. Will be constantly updated.
	 */
	private double[] lastReading;
	
	/**
	 * This constructs an IMU object.
	 * 
	 * If this code isn't running on the Pi, the constructor
	 * will use the test serial object, which will only return
	 * one value for the IMU. Otherwise, the initialization will
	 * actually need to use data from the serial connection, so
	 * it will establish the serial connection and then get data
	 * from the IMU before finishing. A first IMU entry is written
	 * to the log at the end of it.
	 */
	public IMU(){
		if(System.getProperty("os.arch").equals("amd64")){
			connection = new TestSerial("null", 0, 22);
			level = new double[]{0.0, 0.0, 0.0};
			lastReading = new double[]{0.0, 0.0, 0.0};
		}
		else {
			connection = new PiSerial("/dev/ttyUSB0", 57600, 22);
			System.out.println(connection.open());
			String read = connection.read();
			while(read.length() != 22 && !read.contains("YPR=")){
				System.out.println("Attempting to read IMU.");
				read = connection.read();
			}
			System.out.println(read); 
			level = parseSerial(read);
			lastReading = level.clone();
		}
		Rombi.getInstance().log("FIRST IMU READING: Yaw:"+lastReading[0]+" Pitch:"+lastReading[1]+" Roll:"+lastReading[2]);
	}
	
	/**
	 * This method provides automatic stabilization capabilities for the submarine.
	 * 
	 * It first pulls a new reading from the serial connection associated with the IMU
	 * which is then passed onto the serial parsing method found below this one. The data
	 * is logged afterward. 
	 * 
	 * Next, the logic for correcting the submarine begins. It runs through the yaw, pitch,
	 * and roll of the submarine, comparing them to see if they're within a small tolerance
	 * away from the the last set level value. If they are, then the submarine doesn't need
	 * any adjustment. However, if it is outside of this value, then it'll need fixing. To 
	 * do this, if the difference is only half a degree away from the level value, then the
	 * motor speed is increased or decreased by two percent. Otherwise, the motor speed is
	 * increased or decreased by five percent to try and quickly correct this large variation
	 *  
	 * @param motors The array of motors
	 */
	public void checkAndCorrect(Motor[] motors){
		String read = connection.read();
		lastReading = parseSerial(read);
		Rombi.getInstance().log("IMU READING: Yaw:"+lastReading[0]+" Pitch:"+lastReading[1]+" Roll:"+lastReading[2]);
		//If the sub is yawing more than a little to the right, speedup the right motor to balance it
		if(lastReading[0] > (level[0] + 0.05)) {
			//The values might need adjusted later
			double difference = lastReading[0] - (level[0]);
			int speed = motors[4].getSpeed();
			//If the right motor is already at full speed
			if(speed == 100){
				//Then lets slow down the left motor instead
				speed = motors[1].getSpeed();
				if(difference < 0.50){
					int newSpeed = speed - 2;
					if(newSpeed < 0) speed = 0; //We can only decrease the throttle to 0.
					motors[1].setSpeed(speed);
				}
				else {
					int newSpeed = speed - 5;
					if(newSpeed < 0) speed = 0; //We can only decrease the throttle to 0.
					motors[1].setSpeed(speed);
				}
			}
			//Otherwise there's room to move it
			else {
				//If it's a small difference, slowly speed the motors up.
				if(difference < 0.50){
					int newSpeed = speed + 2;
					if(newSpeed > 100) speed = 100; //We can only increase the throttle to 100.
					motors[4].setSpeed(speed);
				}
				else {
					int newSpeed = speed + 5;
					if(newSpeed > 100) speed = 100; //We can only increase the throttle to 100.
					motors[4].setSpeed(speed);
				}
			}
		}
		//Otherwise, if the sub is yawing more than a little to the left, speedup the left motor to balance it
		else if(lastReading[0] < (level[0] - 0.05)) {
			//The values might need adjusted later
			double difference = lastReading[0] - (level[0]);
			int speed = motors[1].getSpeed();
			//If the left motor is already at full speed
			if(speed == 100){
				//Then lets slow down the right motor instead
				speed = motors[4].getSpeed();
				if(difference < 0.50){
					int newSpeed = speed - 2;
					if(newSpeed < 0) speed = 0; //We can only set the throttle to 100.
					motors[4].setSpeed(speed);
				}
				else {
					int newSpeed = speed - 5;
					if(newSpeed < 0) speed = 0; //We can only set the throttle to 100.
					motors[4].setSpeed(speed);
				}
			}
			//Otherwise there's room to move it
			else {
				//If it's a small difference, slowly speed the motor up.
				if(difference < 0.50){
					int newSpeed = speed + 2;
					if(newSpeed > 100) speed = 100; //We can only set the throttle to 100.
					motors[1].setSpeed(speed);
				}
				else {
					int newSpeed = speed + 5;
					if(newSpeed > 100) speed = 100; //We can only set the throttle to 100.
					motors[1].setSpeed(speed);
				}
			}
		}
		//If the 
		if(lastReading[1] > (level[1] + 0.05)) {
			//The values might need adjusted later
			double difference = lastReading[0] - (level[0]);
			int speed = motors[5].getSpeed();
			//If the right motor is already at full speed
			if(speed == 100){
				//Then lets slow down the left motor instead
				speed = motors[2].getSpeed();
				if(difference < 0.50){
					int newSpeed = speed - 2;
					if(newSpeed < 0) speed = 0; //We can only decrease the throttle to 0.
					motors[2].setSpeed(speed);
				}
				else {
					int newSpeed = speed - 5;
					if(newSpeed < 0) speed = 0; //We can only decrease the throttle to 0.
					motors[2].setSpeed(speed);
				}
			}
			//Otherwise there's room to move it
			else {
				//If it's a small difference, slowly speed the motors up.
				if(difference < 0.50){
					int newSpeed = speed + 2;
					if(newSpeed > 100) speed = 100; //We can only increase the throttle to 100.
					motors[5].setSpeed(speed);
				}
				else {
					int newSpeed = speed + 5;
					if(newSpeed > 100) speed = 100; //We can only increase the throttle to 100.
					motors[5].setSpeed(speed);
				}
			}
		}
		//Otherwise, if the sub is yawing more than a little to the left, speedup the left motor to balance it
		else if(lastReading[1] < (level[1] - 0.05)) {
			//The values might need adjusted later
			double difference = lastReading[0] - (level[0]);
			int speed = motors[2].getSpeed();
			//If the left motor is already at full speed
			if(speed == 100){
				//Then lets slow down the right motor instead
				speed = motors[5].getSpeed();
				if(difference < 0.50){
					int newSpeed = speed - 2;
					if(newSpeed < 0) speed = 0; //We can only set the throttle to 100.
					motors[5].setSpeed(speed);
				}
				else {
					int newSpeed = speed - 5;
					if(newSpeed < 0) speed = 0; //We can only set the throttle to 100.
					motors[5].setSpeed(speed);
				}
			}
			//Otherwise there's room to move it
			else {
				//If it's a small difference, slowly speed the motor up.
				if(difference < 0.50){
					int newSpeed = speed + 2;
					if(newSpeed > 100) speed = 100; //We can only set the throttle to 100.
					motors[2].setSpeed(speed);
				}
				else {
					int newSpeed = speed + 5;
					if(newSpeed > 100) speed = 100; //We can only set the throttle to 100.
					motors[2].setSpeed(speed);
				}
			}
		}
		
		if(lastReading[2] > (level[2] + 0.05)) {
			//The values might need adjusted later
			double difference = lastReading[0] - (level[0]);
			int speed = motors[6].getSpeed();
			//If the right motor is already at full speed
			if(speed == 100){
				//Then lets slow down the left motor instead
				speed = motors[3].getSpeed();
				if(difference < 0.50){
					int newSpeed = speed - 2;
					if(newSpeed < 0) speed = 0; //We can only decrease the throttle to 0.
					motors[3].setSpeed(speed);
				}
				else {
					int newSpeed = speed - 5;
					if(newSpeed < 0) speed = 0; //We can only decrease the throttle to 0.
					motors[3].setSpeed(speed);
				}
			}
			//Otherwise there's room to move it
			else {
				//If it's a small difference, slowly speed the motors up.
				if(difference < 0.50){
					int newSpeed = speed + 2;
					if(newSpeed > 100) speed = 100; //We can only increase the throttle to 100.
					motors[6].setSpeed(speed);
				}
				else {
					int newSpeed = speed + 5;
					if(newSpeed > 100) speed = 100; //We can only increase the throttle to 100.
					motors[6].setSpeed(speed);
				}
			}
		}
		//Otherwise, if the sub is yawing more than a little to the left, speedup the left motor to balance it
		else if(lastReading[2] < (level[2] - 0.05)) {
			//The values might need adjusted later
			double difference = lastReading[0] - (level[0]);
			int speed = motors[3].getSpeed();
			//If the left motor is already at full speed
			if(speed == 100){
				//Then lets slow down the right motor instead
				speed = motors[6].getSpeed();
				if(difference < 0.50){
					int newSpeed = speed - 2;
					if(newSpeed < 0) speed = 0; //We can only set the throttle to 100.
					motors[6].setSpeed(speed);
				}
				else {
					int newSpeed = speed - 5;
					if(newSpeed < 0) speed = 0; //We can only set the throttle to 100.
					motors[6].setSpeed(speed);
				}
			}
			//Otherwise there's room to move it
			else {
				//If it's a small difference, slowly speed the motor up.
				if(difference < 0.50){
					int newSpeed = speed + 2;
					if(newSpeed > 100) speed = 100; //We can only set the throttle to 100.
					motors[3].setSpeed(speed);
				}
				else {
					int newSpeed = speed + 5;
					if(newSpeed > 100) speed = 100; //We can only set the throttle to 100.
					motors[3].setSpeed(speed);
				}
			}
		}
	}
	
	/**
	 * Given a string of data from the IMU, parse it out
	 * and read the captured data. 
	 * 
	 * @param input String sent from the IMU
	 * @return An array containing the yaw, pitch, and roll values captured in this response.
	 */
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
				System.out.println("Not enough entries to parse.");
				result[0]=4.0;
			}
		}
		else{
			System.out.println("Invalid format.");
			result[0]=5.0;
		}
		return result;
	}
	
	/**
	 * This class provides a thread for running the stabilization
	 * method automatically and separately of the main control code.
	 * This allows for corrections to be made while while commands
	 * are being followed and can poll the IMU at a speed that should
	 * allow for near real time reading of the IMU.
	 * 
	 * @author Vaughn Dorsey
	 *
	 */
	public class IMURunner implements Runnable {
		
		/**
		 * The original IMU object
		 */
		private IMU imu;
		/**
		 * Direct access to the motors
		 */
		private Motor[] motors;
		
		/**
		 * Creates the threading object and stores necessary variables.
		 * @param imu The original IMU object it'll need to work with.
		 * @param motors The motor array from Rombi, allowing direct access
		 */
		public IMURunner(IMU imu, Motor[] motors){
			this.imu = imu;
			this.motors = motors;
		}
		
		/**
		 * The code that the thread will be executing. Should cycle
		 * approximately every 200ms on the Pi and close to 100ms on
		 * a PC.
		 */
		@Override
		public void run() {
			while(true){
				imu.checkAndCorrect(motors);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
