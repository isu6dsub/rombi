package Sub.Components;

import Sub.Drivers.PWM.TestPWMDevice;
import Sub.Drivers.Serial.*;
import Utils.DataLogger;
/**
 * 
 * @author Vaughn Dorsey
 *
 */
public class IMU {
	
	/**
	 * 
	 */
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
	
	/**
	 * 
	 */
	public IMU(){
		if(System.getProperty("os.arch").equals("amd64")){
			connection = new TestSerial("null", 0, 22);
			flat = new double[]{0.0, 0.0, 0.0};
			level = new double[]{0.0, 0.0, 0.0};
			lastReading = new double[]{0.0, 0.0, 0.0};
		}
		else {
			connection = new PiSerial("/dev/ttyUSB0", 57600, 22);
			System.out.println(connection.open());
			flat = new double[]{0.00, 0.00, 0.00};
			String read = connection.read();
			while(read.length() != 22 && !read.contains("YPR=")){
				System.out.println("Attempting to read IMU.");
				read = connection.read();
			}
			System.out.println(read); 
			level = parseSerial(read);
			lastReading = level.clone();
			System.out.println("LAST IMU READING: Yaw:"+lastReading[0]+" Pitch:"+lastReading[1]+" Roll:"+lastReading[2]);

		}
	}
	
	/**
	 * 
	 * @param motors
	 */
	public void checkAndCorrect(Motor[] motors){
		String read = connection.read();
		lastReading = parseSerial(read);
		DataLogger.getInstance().writeStuff("IMU READING: Yaw:"+lastReading[0]+" Pitch:"+lastReading[1]+" Roll:"+lastReading[2]);
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
	 * 
	 * @param input
	 * @return
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
	
	
	public class IMURunner implements Runnable {
		
		private IMU imu;
		private Motor[] motors;
		
		public IMURunner(IMU imu, Motor[] motors){
			this.imu = imu;
			this.motors = motors;
		}
		
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
