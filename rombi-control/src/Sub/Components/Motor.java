package Sub.Components;

import java.io.IOException;

import Sub.Drivers.PWM.*;

/**
 * 
 * The motor class stores all variables that are needed to operate the motors
 * and any methods to control the motor, such as setting the speed and changing
 * the motor direction.
 * 
 * As a note, if the control system is run on a system that isn't the Pi, null values
 * for the PWM channels will be set so that it doesn't try to set PWM values that don't
 * exist during testing. Failure to do this will cause the program to crash.
 * 
 * @author Vaughn Dorsey
 *
 */
public class Motor {
	/**
	 * Minimum PWM Value
	 */
	private int MIN_PWM;
	/**
	 * PWM Midpoint Value
	 */
	private int MID_PWM;
	/**
	 * Maximum PWM Value
	 */
	private int MAX_PWM;

	/**
	 * Enum defining the direction of the propulsion.
	 * 
	 * @author vwdorsey
	 *
	 */
	private enum Direction { FORWARD, BACKWARD };
	
	/**
	 * Variable holding current motor speed.
	 */
	private int speed;
	
	/**
	 * Variable holding current motor direction.
	 */
	private Direction motorDirection;
	
	/**
	 * Variable holding what pwm_channel corresponds to the motor.
	 */
	private PWMChannelBase pwm_channel;
	
	/**
	 * Constructs a motor object for use with Rombi. Also sets the PWM
	 * channel that corresponds to the motor and the maximum, midpoint,
	 * and minimum throttle values. Finally, the initial motor direction
	 * and speed are set to moving forward and full stop.
	 * 
	 * @param pwmChannelBase PWM Channel that the ESC for the motor exists on
	 * @param max The maximum throttle value, equivalent to full forward.
	 * @param mid The midpoint of the throttle. Should be where motor stops between forward and reverse.
	 * @param min The minimum throttle value, equivalent to full reverse.
	 */
	public Motor(PWMChannelBase pwmChannelBase, int min, int mid, int max){
		this.speed = 0;
		this.motorDirection = Direction.FORWARD;
		if(System.getProperty("os.arch").equals("amd64")){
			this.pwm_channel = null;
		}
		else this.pwm_channel = pwmChannelBase;
		this.MIN_PWM = min;
		this.MID_PWM = mid;
		this.MAX_PWM = max;
	}
	
	/**
	 * Commands the motor to immediately stop by setting the PWM value to 0,
	 * which the ESC won't run at.
	 */
	public synchronized void stop(){
		speed = 0;
		try {
			if(pwm_channel == null) return;
			pwm_channel.setPWM(0, 0);
		} catch (IOException e) {
			// TODO Make a scary error message?
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the speed of the motor to the given value.
	 * Also ensures that the speed won't be out of bounds.
	 * 
	 * @param speed New speed of the sub as a percentage
	 * @throws IOException 
	 */
	public synchronized int setSpeed(int speed){
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
			if(pwm_channel == null) return 0;
			pwm_channel.setPWM(0, speed);
		} catch (IOException e) {
			return -2;
		}
		return 0;
	}
	
	/**
	 * Allows access to the speed of the motor.
	 * 
	 * @return Current speed of motor.
	 */
	public synchronized int getSpeed(){
		return speed;
	}
	
	/**
	 * Switches the direction of the motor and then attempts
	 * to reset the ESC to make the motor go in the desired direction.
	 */
	public synchronized void switchDirection(){
		this.stop();
		if(motorDirection == Direction.FORWARD){
			motorDirection = Direction.BACKWARD;
		}
		else {
			motorDirection = Direction.FORWARD;
		}
		
		if(pwm_channel == null) return;
		else {
			try {
				pwm_channel.setPWM(0, 0);
				pwm_channel.setPWM(0, MID_PWM);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * Allows access to the current direction of the motor.
	 * 
	 * @return True if motor is operating in forward mode, false if in reverse.
	 */
	public synchronized boolean direction(){
		if(motorDirection == Direction.FORWARD) return true;
		else return false;
	}
}
