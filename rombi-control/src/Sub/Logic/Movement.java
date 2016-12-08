package Sub.Logic;

import Sub.Components.Motor;
import Sub.Drivers.PWM.PWMBase;

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
		
	}
	
	public static void TransRight(int speed, Motor[] motors){
		
	}
	
	public static void TurnLeft(int speed, Motor[] motors){
		
	}
	
	public static void TurnRight(int speed, Motor[] motors){
		
	}
	
	public static void PitchUp(int speed, Motor[] motors){
		
	}
	
	public static void PitchDown(int speed, Motor[] motors){
		
	}
	
	public static void RollLeft(int speed, Motor[] motors){
		
	}
	
	public static void RollRight(int speed, Motor[] motors){
		
	}
	
	public static void YawLeft(int speed, Motor[] motors){
		
	}
	
	public static void YawRight(int speed, Motor[] motors){
		
	}
}
