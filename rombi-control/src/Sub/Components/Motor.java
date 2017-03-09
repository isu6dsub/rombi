package Sub.Components;

import java.io.IOException;

import Sub.Drivers.PWM.*;

/**
 * 
 * @author Vaughn Dorsey
 *
 */
public class Motor {
	//Static values for the minimum, midpoint, and maximum value
	private int MIN_PWM;
	private int MID_PWM;
	private int MAX_PWM;

	//Enum defining direction
	private enum Direction { FORWARD, BACKWARD };
	
	//Variables that hold data for each motor.
	private int speed;
	private Direction motorDirection;
	private PWMChannelBase pwm_channel;
	
	/**
	 * 
	 * @param pwmChannelBase
	 * @param max 
	 * @param mid 
	 * @param min 
	 */
	public Motor(PWMChannelBase pwmChannelBase, int min, int mid, int max){
		this.speed = 0;
		this.motorDirection = Direction.FORWARD;
		this.pwm_channel = pwmChannelBase;
		this.MIN_PWM = min;
		this.MID_PWM = mid;
		this.MAX_PWM = max;
	}
	
	/**
	 * 
	 */
	public void stop(){
		speed = 0;
		try {
			pwm_channel.setPWM(0, 0);
		} catch (IOException e) {
			// TODO Make a scary error message?
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
	 * @return
	 */
	public int getSpeed(){
		return speed;
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
	
	/**
	 * 
	 * @return
	 */
	public boolean direction(){
		if(motorDirection == Direction.FORWARD) return true;
		else return false;
	}
}
