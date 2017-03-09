package Control;

import java.util.Scanner;

import Sub.Rombi;

/**
 * 
 * @author Vaughn Dorsey
 *
 */
public class ConsoleUI {
	
	public static void startPrompt(Rombi rombi){
		Scanner input = new Scanner(System.in);
		System.out.println("Starting Prompt");
		
		while(true){
			System.out.print("Rombi> ");
			String command = input.nextLine();
			while(command.equals("")){
				rombi.systemCheck();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("System check");
			}
			
			String first = command.toLowerCase();
			if(first.contains(" ")) first = first.substring(0, first.indexOf(" "));
			String[] splitCommand;
			
			switch(first){
				case "help":
					showHelp("interpreter");
					break;
				case "go":
					splitCommand = command.split("\\s");
					if(splitCommand.length != 3) {
						System.out.println("Invalid arguments for command go. See help");
					}
					else switch(splitCommand[1]){
						case "forward":
							break;
						case "backward":
							break;
						case "left":
							break;
						case "right":
							break;
						default:
							System.out.println("Invalid Direction");
							break;
					}
					break;
				case "turn":
					splitCommand = command.split("\\s");
					if(splitCommand.length != 3) {
						System.out.println("Invalid arguments for command go. See help");
					}
					else switch(splitCommand[1]){
						case "left":
							break;
						case "right":
							break;
						default:
							System.out.println("Invalid Direction");
							break;
					}
					break;
				case "pitch":
					System.out.println("Command Not Implemented");
					break;
				case "roll":
					System.out.println("Command Not Implemented");
					break;
				case "yaw":
					System.out.println("Command Not Implemented");
					break;
				case "exit":
					rombi.stop();
					System.out.println("System shutdown");
					input.close();
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Command for this system version.");
			}
		}
	}
	
	public static void showHelp(String command){
		switch(command.toLowerCase()){
			case "interpreter":
				System.out.println("Help for the Rombi Interpreter");
				System.out.println("Available commands:");
				System.out.println("help <command> - Shows help info for the given command.");
				System.out.println("go <direction> <speed> - Translate submarine.");
				System.out.println("turn <direction> <speed> - Turn submarine.");
				System.out.println("pitch <direction> <speed> - Pitch submarine.");
				System.out.println("roll <direction> <speed> - Roll submarine.");
				System.out.println("yaw <direction> <speed> - Yaw submarine");
				break;
			default:
				
		}
	}
}
