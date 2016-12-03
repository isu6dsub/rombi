package Sub.Components;

import java.io.IOException;

import se.hirt.pi.adafruit.pwm.PWMDevice;
import se.hirt.pi.adafruit.pwm.PWMDevice.PWMChannel;

public class Motor {
	//Static values for the minimum, midpoint, and maximum value
	private static int MIN_PWM = 800;
	private static int MID_PWM = 1600;
	private static int MAX_PWM = 2400;

	//Enum defining direction
	private enum Direction { FORWARD, BACKWARD };
	
	//Variables that hold data for each motor.
	private int speed;
	private Direction motorDirection;
	private PWMChannel pwm_channel;
	
	/**
	 * 
	 * @param channel
	 */
	public Motor(PWMChannel channel){
		this.speed = 0;
		this.motorDirection = Direction.FORWARD;
		this.pwm_channel = channel;
	}
	
	/**
	 * 
	 */
	public void stop(){
		//TODO: Do stopping stuff
		speed = 0;
		try {
			pwm_channel.setPWM(0, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param speed New speed of the sub as a percentage
	 * @throws IOException 
	 */
	public int setSpeed(int speed){
		if(speed > 100 || speed < 0){
			return -1;
		}
		
		if(motorDirection == Direction.FORWARD){
			speed = (int) (MID_PWM + (MAX_PWM - MID_PWM) * (speed/100.0));
		}
		else {
			speed = (int) (MID_PWM - (MAX_PWM - MID_PWM) * 1-(speed/100.0));
		}
		try {
			pwm_channel.setPWM(0, speed);
		} catch (IOException e) {
			return -2;
		}
		return 0;
	}
	
	/**
	 * 
	 */
	public void switchDirection(){
		this.stop();
		if(motorDirection == Direction.FORWARD){
			motorDirection = Direction.BACKWARD;
		}
		else {
			motorDirection = Direction.FORWARD;
		}
		
	}
}
