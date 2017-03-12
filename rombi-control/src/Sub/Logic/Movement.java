package Sub.Logic;

import Sub.Components.Motor;

/**
 * This class contains functions that define what motors should be used
 * to accomplish various movements that the submarine will need to make.
 * These methond may change over time as the submarine evolves, but currently
 * this code relies on the submarine being in a camera down orientation.
 * It only goes in basic directions, not accounting for the orientation of the sub
 * or really any other factors.
 * 
 * Motor assignments are as follows, using x,y,z axis: (All respective to values)
 * Motors 1 & 4: On top and bottom of sub, facing along X-axis
 * Motors 2 & 5: On face above camera and face directly opposite. Facing along Y-axis
 * Motors 3 & 6: On left and right sides of submarine. Facing along Z-axis
 * 
 * TODO: Check all movements in water testing to ensure the sub is moving correctly.
 * 
 * @author Vaughn Dorsey
 *
 */
public class Movement {
	
	/**
	 * Commands the submarine to drive forward by turning 
	 * the correct motor pair on to push the submarine forward.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void Forward(int speed, Motor[] motors){
		if(!motors[1].direction()) motors[1].switchDirection();
		if(!motors[4].direction()) motors[4].switchDirection();
		
		motors[1].setSpeed(speed);
		motors[4].setSpeed(speed);
	}
	
	/**
	 * Commands the submarine to drive backward by turning 
	 * the correct motor pair on to push the submarine backward.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void Backward(int speed, Motor[] motors){
		if(motors[1].direction()) motors[1].switchDirection();
		if(motors[4].direction()) motors[4].switchDirection();
		
		motors[1].setSpeed(speed);
		motors[4].setSpeed(speed);
	}

	/**
	 * Commands the submarine to translate left by turning 
	 * the correct motor pair on to push the submarine left.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void TransLeft(int speed, Motor[] motors){
		if(motors[2].direction()) motors[2].switchDirection();
		if(motors[5].direction()) motors[5].switchDirection();
		
		motors[2].setSpeed(speed);
		motors[5].setSpeed(speed);
	}

	/**
	 * Commands the submarine to translate right by turning 
	 * the correct motor pair on to push the submarine right.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void TransRight(int speed, Motor[] motors){
		if(motors[2].direction()) motors[2].switchDirection();
		if(motors[5].direction()) motors[5].switchDirection();
		
		motors[2].setSpeed(speed);
		motors[5].setSpeed(speed);
	}

	/**
	 * Commands the submarine to surface by adjusting the motor pair
	 * responsible for keeping the submarine underwater to not work as
	 * hard keeping it underwater, thereby surfacing the submarine.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void Surface(int speed, Motor[] motors){
		motors[3].setSpeed(100-speed);
		motors[6].setSpeed(100-speed);
	}

	/**
	 * Commands the submarine to surface by adjusting the motor pair
	 * responsible for keeping the submarine underwater to work harder
	 * to keep it underwater, thereby pushing the submarine deeper.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void Dive(int speed, Motor[] motors){
		motors[3].setSpeed(50+speed);
		motors[6].setSpeed(50+speed);
	}

	/**
	 * Commands the submarine to pitch up by turning 
	 * the correct motor pair on to rotate the submarine upward.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void PitchUp(int speed, Motor[] motors){
		if(motors[1].direction()) motors[1].switchDirection();
		if(!motors[4].direction()) motors[4].switchDirection();
		
		motors[1].setSpeed(speed);
		motors[4].setSpeed(speed);
	}

	/**
	 * Commands the submarine to pitch down by turning 
	 * the correct motor pair on to rotate the submarine downward.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void PitchDown(int speed, Motor[] motors){
		if(!motors[1].direction()) motors[1].switchDirection();
		if(motors[4].direction()) motors[4].switchDirection();
		
		motors[1].setSpeed(speed);
		motors[4].setSpeed(speed);
	}

	/**
	 * Commands the submarine to roll left by turning 
	 * the correct motor pair on to rotate the submarine to the left.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void RollLeft(int speed, Motor[] motors){
		motors[3].setSpeed(50-speed);
		motors[6].setSpeed(50+speed);
	}

	/**
	 * Commands the submarine to roll right by turning 
	 * the correct motor pair on to push the submarine to the right.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void RollRight(int speed, Motor[] motors){
		motors[3].setSpeed(50+speed);
		motors[6].setSpeed(50-speed);
	}

	/**
	 * Commands the submarine to yaw to the left by turning 
	 * the correct motor pair on to spin the submarine to the left.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void YawLeft(int speed, Motor[] motors){
		if(!motors[2].direction()) motors[2].switchDirection();
		if(motors[5].direction()) motors[5].switchDirection();
		
		motors[2].setSpeed(speed);
		motors[5].setSpeed(speed);
	}

	/**
	 * Commands the submarine to yaw to the right by turning 
	 * the correct motor pair on to spin the submarine to the right.
	 * 
	 * @param speed Amount of throttle as a percentage.
	 * @param motors The set of motors.
	 */
	public static void YawRight(int speed, Motor[] motors){
		if(motors[2].direction()) motors[2].switchDirection();
		if(!motors[5].direction()) motors[5].switchDirection();
		
		motors[2].setSpeed(speed);
		motors[5].setSpeed(speed);
	}
}
