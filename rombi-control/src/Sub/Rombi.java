package Sub;
import java.io.IOException;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import Sub.Components.Motor;
import se.hirt.pi.adafruit.pwm.PWMDevice;

public class Rombi {
	public Motor motors[];
	public PWMDevice pwmBoard;
	
	public Rombi() {
		//TODO: Load some sort of config
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
		
		for(int i = 1; i < 7; i++) {
			motors[i-1] = new Motor(pwmBoard.getChannel(i));
		}
	}
}
