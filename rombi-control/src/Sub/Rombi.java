package Sub;
import java.io.IOException;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import Sub.Drivers.PWM.*;
import Sub.Logic.Movement;
import Sub.Components.*;

public class Rombi {
	private Motor motors[];
	private PWMBase pwmBoard;
	private IMU imu;
	
	public Rombi() {
		//TODO: Load some sort of config
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
			motors[i-1] = new Motor(pwmBoard.getChannel(i));
		}
	}
	
	public void stop(){
		for(Motor m:motors){
			m.stop();
		}
	}
	
	public void systemCheck(){
		imu.checkAndCorrect(motors);
	}
	
	public void availableCommands(){
		
	}

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
	}
}
