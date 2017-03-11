package Sub;
import java.io.IOException;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import Sub.Drivers.PWM.*;
import Sub.Logic.Movement;
import Utils.DataLogger;
import Utils.FileConfiguration;
import Sub.Components.*;

/**
 * The object that represents the submarine. It contains variables
 * to store references to the motors, the pwmBoard, and the IMU, 
 * among other later additions. This also sets up and starts all 
 * of the systems that comprise the submarine and provides access to
 * the underlying systems with API methods.
 * 
 * @author Vaughn Dorsey
 *
 */
public class Rombi {
	private Motor motors[];
	private PWMBase pwmBoard;
	private IMU imu;
	
	/**
	 * 
	 */
	public Rombi() {
		FileConfiguration config = FileConfiguration.getInstance("SubConfig.txt");
		DataLogger.getInstance();
		if(System.getProperty("os.arch").equals("amd64")){
			pwmBoard = new TestPWMDevice();
			imu = new IMU();
		}
		else {
			try {
				pwmBoard = new PWMDevice();
			} catch (IOException e) {
				System.out.println("There was an I/O problem. System can't continue.");
				e.printStackTrace();
				System.exit(-1);
			} catch (UnsupportedBusNumberException e) {
				System.out.println("Invalid Bus number. System can't continue.");
				e.printStackTrace();
				System.exit(-1);
			}
			try {
				pwmBoard.setPWMFreqency(240.0);
			} catch (IOException e) {
				System.out.println("Unable to set PWM frequency. System can't continue");
				e.printStackTrace();
				System.exit(-1);
			}
			
			imu = new IMU();
		}
		
		motors = new Motor[6];
		
		for(int i = 1; i < 7; i++) {
			motors[i-1] = new Motor(pwmBoard.getChannel(i),
					(config.containsConfigValue("motor"+i+"min") ? config.getConfigValue("motor"+i+"min") :800),
					(config.containsConfigValue("motor"+i+"mid") ? config.getConfigValue("motor"+i+"mid") :1600),
					(config.containsConfigValue("motor"+i+"max") ? config.getConfigValue("motor"+i+"max") :2400));
		}
		
	}
	
	/**
	 * Method to shut down the submarine safely.
	 * Stops all of the motors and shuts the logger down
	 * so that all data is saved.
	 */
	public void stop(){
		for(Motor m:motors){
			m.stop();
		}
		
		DataLogger.getInstance().closeLog();
	}
	
	/**
	 * Asks the IMU to get a new value and to check the orientation
	 * of the submarine.
	 */
	public void systemCheck(){
		imu.checkAndCorrect(motors);
	}

	/**
	 * 
	 * @param direction
	 * @param speed
	 */
	public void move(String direction, int speed){
		switch(direction.toLowerCase()){
			case "forward":
				Movement.Forward(speed, motors);
				break;
			case "backward":
				Movement.Backward(speed, motors);
				break;
			case "left translate":
				Movement.TransLeft(speed, motors);
				break;
			case "right translate":
				Movement.TransRight(speed, motors);
				break;
			case "left yaw":
				Movement.YawLeft(speed, motors);
				break;
			case "right yaw":
				Movement.YawRight(speed, motors);
				break;
			case "roll left":
				Movement.RollLeft(speed, motors);
				break;
			case "roll right":
				Movement.RollRight(speed, motors);
				break;
			case "pitch up":
				Movement.PitchUp(speed, motors);
				break;
			case "pitch down":
				Movement.PitchDown(speed, motors);
				break;
			case "surface":
				Movement.Surface(speed, motors);
				break;
			case "dive":
				Movement.Dive(speed, motors);
				break;
		}
		
		DataLogger.getInstance().writeStuff("Command issued: "+direction+" "+speed);
	}
}
