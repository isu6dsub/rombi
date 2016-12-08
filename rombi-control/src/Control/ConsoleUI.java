package Control;

import java.util.Scanner;

import Sub.Rombi;

public class ConsoleUI {
	public static void run(Rombi rombi){
		int exit = 0;
		Scanner in = new Scanner(System.in);
		
		while(true){
			System.out.println("Available Commands:");
			System.out.println("1. Give Command"); //TODO: Need to write code for maneuvers.
			System.out.println("2. Stop Vehicle"); 
			System.out.println("3. Shut Rombi Down");
			
			rombi.systemCheck();
			while(!in.hasNextLine()){
				System.out.println("System Check");
				rombi.systemCheck();
			}
			
			String choice = in.next();
			System.out.println(choice);
			switch(choice){
				case "1": //TODO: Move setting speed code somewhere else. Probably to Rombi class.
					while(!in.hasNextLine());
					rombi.interpretCommand(in.next());
					break;
				case "2":
					System.out.println("Stopping all motors.");
					rombi.stop();
					break;
				case "3":
					rombi.stop();
					in.close();
					return;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		}
	}
}
