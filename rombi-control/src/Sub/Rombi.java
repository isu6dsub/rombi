package Sub;
import java.io.IOException;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import Sub.Drivers.PWM.*;
import Sub.Drivers.Serial.*;
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
}
