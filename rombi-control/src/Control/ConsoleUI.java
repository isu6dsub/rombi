package Control;

import java.util.Scanner;

import Sub.Rombi;
//TODO: Replace this entire thing with something like a dos prompt.
public class ConsoleUI {
	public static void run(Rombi rombi){
		Scanner in = new Scanner(System.in);
		
		while(true){
			System.out.println("Available Commands:");
			System.out.println("1. Give Command"); //TODO: Need to write code for maneuvers.
			System.out.println("2. Stop Vehicle"); 
			System.out.println("3. Shut Rombi Down");
			
			rombi.systemCheck();
			while(!in.hasNextLine()){
				//This isn't reading data fast enough to be useful
				//TODO: Find a way to speed up data gathering or something.
				try { 
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
				}
				System.out.println("System Check");
				rombi.systemCheck();
			}
			
			String choice = in.next();
			switch(choice){
				case "1": //TODO: Move setting speed code somewhere else. Probably to Rombi class.
					String commands = in.nextLine();
					while(commands.equals("")){
						commands = in.nextLine();
					}
					rombi.interpretCommand(commands);
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
