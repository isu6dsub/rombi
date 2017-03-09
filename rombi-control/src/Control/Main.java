package Control;

import Sub.Rombi;

public class Main {
	//TODO: Fix this up so that we the ability to start the sub remotely
	public static void main(String[] args){
		System.out.println("ROMBI Control Program v1.0.0");
		if(args.length > 0){
			System.out.println("Beginning Submarine Setup");
			
			//All that should be done here is to create a new instance of Rombi. The Rombi constructor should do the rest.
			Rombi rombi = new Rombi();
			System.out.println("Submarine Setup Complete. Beginning main control program.");
	
			//Start the text console
			if(args[0].equals("-i")) ConsoleUI.startPrompt(rombi);
			else {
				
			}
			
		}
		else{
			System.out.println("Invalid arguments given. Please check arguments.");
			System.out.println("Valid arguments:");
			System.out.println("-a <TASKFILE> - Start sub in autonomous mode with given taskfile.");
			System.out.println("-i - Start sub in interactive mode.");
		}
		System.exit(0);
	}
}
