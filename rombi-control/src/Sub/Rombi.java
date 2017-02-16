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

	public void interpretCommand(String command) {
		String[] commands = command.toLowerCase().split("\\s");
		if(commands.length == 3 && commands[0].equals("move")){
			int givenSpeed = Integer.parseInt(commands[2]);
			if(givenSpeed >= 0 && givenSpeed <= 100) {
				if(commands[1].equals("forward")) Movement.Forward(givenSpeed, motors);
				else if(commands[1].equals("backward")) Movement.Backward(givenSpeed, motors);
				else System.out.println("Either the movement direction is invalid or not supported yet. Check and try again.");
			}
			else System.out.println("Invalid Speed Given.");
		}
		else System.out.println("I don't understand the command you've given me. Given Command: "+command);
	}
}
