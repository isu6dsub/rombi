package Control;

import Sub.Rombi;
/**
 * This is the main class of the ROMBI Control Software.
 * 
 * @author Vaughn Dorsey
 *
 */
public class Main {
	//TODO: Fix this up so that we the ability to start the sub remotely
	public static void main(String[] args){
		System.out.println("ROMBI Control Program v1.0.0");
		if(args.length > 0){
			System.out.println("Beginning Submarine Setup");
			
			//All that should be done here is to create a new instance of Rombi. The Rombi constructor should do the rest.
			Rombi.getInstance();
			System.out.println("Submarine Setup Complete. Beginning main control program.");
	
			//Start the text console
			if(args[0].equals("-i")) ConsoleUI.startPrompt();
			else if(args[0].equals("-a") && args.length == 2){
				
			}
			else {
				errorMessage();
			}
			
		}
		else{
			errorMessage();
		}
		System.exit(0);
	}
	
	private static void errorMessage() {
		System.out.println("Invalid arguments given. Please check arguments.");
		System.out.println("Valid arguments:");
		System.out.println("-a <TASKFILE> - Start sub in autonomous mode with given taskfile.");
		System.out.println("-i - Start sub in interactive mode.");
	}
}
