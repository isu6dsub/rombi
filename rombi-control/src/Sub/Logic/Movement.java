package Sub.Logic;

import Sub.Components.Motor;
import Sub.Drivers.PWM.PWMBase;

/*
 * Motor assignments are as follows, using x,y,z axis: (All respective to values)
 * Motors 1 & 4: On top and bottom of sub, facing along X-axis
 * Motors 2 & 5: On face above camera and face directly opposite. Facing along Y-axis
 * Motors 3 & 6: On left and right sides of submarine. Facing along Z-axis
 */

//TODO: This code may need more work later when I can see the sub moving.
//It only goes in basic directions, not accounting for the orientation of the sub
//or really any other factors.

public class Movement {
	public static void Forward(int speed, Motor[] motors){
		if(!motors[1].direction()) motors[1].switchDirection();
		if(!motors[4].direction()) motors[4].switchDirection();
		
		motors[1].setSpeed(speed);
		motors[4].setSpeed(speed);
	}
	
	public static void Backward(int speed, Motor[] motors){
		if(motors[1].direction()) motors[1].switchDirection();
		if(motors[4].direction()) motors[4].switchDirection();
		
		motors[1].setSpeed(speed);
		motors[4].setSpeed(speed);
	}
	
	public static void TransLeft(int speed, Motor[] motors){
		if(motors[2].direction()) motors[2].switchDirection();
		if(motors[5].direction()) motors[5].switchDirection();
		
		motors[2].setSpeed(speed);
		motors[5].setSpeed(speed);
	}
	
	public static void TransRight(int speed, Motor[] motors){
		if(motors[2].direction()) motors[2].switchDirection();
		if(motors[5].direction()) motors[5].switchDirection();
		
		motors[2].setSpeed(speed);
		motors[5].setSpeed(speed);
	}
	
	public static void Surface(int speed, Motor[] motors){
		motors[3].setSpeed(100-speed);
		motors[6].setSpeed(100-speed);
	}
	
	public static void Dive(int speed, Motor[] motors){
		motors[3].setSpeed(50+speed);
		motors[6].setSpeed(50+speed);
	}
	
	public static void PitchUp(int speed, Motor[] motors){
		if(motors[1].direction()) motors[1].switchDirection();
		if(!motors[4].direction()) motors[4].switchDirection();
		
		motors[1].setSpeed(speed);
		motors[4].setSpeed(speed);
	}
	
	public static void PitchDown(int speed, Motor[] motors){
		if(!motors[1].direction()) motors[1].switchDirection();
		if(motors[4].direction()) motors[4].switchDirection();
		
		motors[1].setSpeed(speed);
		motors[4].setSpeed(speed);
	}
	
	public static void RollLeft(int speed, Motor[] motors){
		motors[3].setSpeed(50-speed);
		motors[6].setSpeed(50+speed);
	}
	
	public static void RollRight(int speed, Motor[] motors){
		motors[3].setSpeed(50+speed);
		motors[6].setSpeed(50-speed);
	}
	
	public static void YawLeft(int speed, Motor[] motors){
		if(!motors[2].direction()) motors[2].switchDirection();
		if(motors[5].direction()) motors[5].switchDirection();
		
		motors[2].setSpeed(speed);
		motors[5].setSpeed(speed);
	}
	
	public static void YawRight(int speed, Motor[] motors){
		if(motors[2].direction()) motors[2].switchDirection();
		if(!motors[5].direction()) motors[5].switchDirection();
		
		motors[2].setSpeed(speed);
		motors[5].setSpeed(speed);
	}
}
