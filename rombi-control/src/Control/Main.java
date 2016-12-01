package Control;

import java.util.Scanner;

import Sub.Rombi;

public class Main {
	public static void main(String[] args){
		System.out.println("ROMBI Control Program v0.1.0");
		System.out.println("Beginning Submarine Setup");
		
		//Put sub setup stuff here
		Rombi rombi = new Rombi();
		Scanner in = new Scanner(System.in);
		System.out.println("Submarine Setup Complete. Beginning main control program.");
		
		int exit = 0;
		while(exit==0){
			System.out.println("Available Commands:");
			System.out.println("1. Set Motor Speed");
			System.out.println("2. Stop All Motors");
			System.out.println("3. Shut Rombi Down");
			
			while(!in.hasNext()){
				
			}
			
			int choice = in.nextInt();
			
			switch(choice){
				case 1:
					System.out.println("Which motor?");
					int motorNumber = in.nextInt();
					System.out.println("Please give the speed as a percentage:");
					int speed = in.nextInt();
					if(motorNumber > 0 && motorNumber < 7){
						if(speed < 100 && speed >= 0) rombi.motors[motorNumber - 1].setSpeed(speed);
						else System.out.println("Invalid Speed.");
					}
					else System.out.println("Invalid Motor Number.");
					break;
				case 2:
					System.out.println("Stopping all motors.");
					for(int i = 0; i < 6; i++){
						rombi.motors[i].stop();
					}
					break;
				case 3:
					
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
			
		}
		in.close();
	}
}
