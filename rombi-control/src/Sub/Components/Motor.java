package Sub.Components;

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
	private int pwm_channel;
	
	/**
	 * 
	 * @param channel
	 */
	public Motor(int channel){
		this.speed = MID_PWM;
		this.motorDirection = Direction.FORWARD;
		this.pwm_channel = channel;
	}
	
	/**
	 * 
	 */
	public void stop(){
		//TODO: Do stopping stuff
	}
	
	/**
	 * 
	 * @param speed New speed of the sub as a percentage
	 */
	public int setSpeed(int speed){
		if(speed > 100 || speed < 0){
			return -1;
		}
		
		//TODO: Set speed stuff based on direction
		
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
