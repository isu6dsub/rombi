package Control;

import java.util.Scanner;

import Sub.Rombi;

/**
 * 
 * @author Vaughn Dorsey
 *
 */
public class ConsoleUI {
	
	/**
	 * 
	 * @param rombi
	 */
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
			try{
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
								rombi.move("forward", Integer.parseInt(splitCommand[2]));
								break;
							case "backward":
								rombi.move("backward", Integer.parseInt(splitCommand[2]));
								break;
							case "left":
								rombi.move("move left", Integer.parseInt(splitCommand[2]));
								break;
							case "right":
								rombi.move("move right", Integer.parseInt(splitCommand[2]));
								break;
							default:
								System.out.println("Invalid Direction");
								break;
						}
						break;
					case "turn":
						splitCommand = command.split("\\s");
						if(splitCommand.length != 3) {
							System.out.println("Invalid arguments for command turn. See help");
						}
						else switch(splitCommand[1]){
							case "left":
								rombi.move("turn left", Integer.parseInt(splitCommand[2]));
								break;
							case "right":
								rombi.move("turn right", Integer.parseInt(splitCommand[2]));
								break;
							default:
								System.out.println("Invalid Direction");
								break;
						}
						break;
					case "pitch":
						splitCommand = command.split("\\s");
						if(splitCommand.length != 3) {
							System.out.println("Invalid arguments for command pitch. See help");
						}
						else switch(splitCommand[1]){
							case "up":
								rombi.move("pitch up", Integer.parseInt(splitCommand[2]));
								break;
							case "down":
								rombi.move("pitch down", Integer.parseInt(splitCommand[2]));
								break;
							default:
								System.out.println("Invalid Direction");
								break;
						}
						break;
					case "roll":
						splitCommand = command.split("\\s");
						if(splitCommand.length != 3) {
							System.out.println("Invalid arguments for command roll. See help");
						}
						else switch(splitCommand[1]){
							case "left":
								rombi.move("roll left", Integer.parseInt(splitCommand[2]));
								break;
							case "right":
								rombi.move("roll right", Integer.parseInt(splitCommand[2]));
								break;
							default:
								System.out.println("Invalid Direction");
								break;
						}
						break;
					case "yaw":
						splitCommand = command.split("\\s");
						if(splitCommand.length != 3) {
							System.out.println("Invalid arguments for command yaw. See help");
						}
						else switch(splitCommand[1]){
							case "left":
								rombi.move("yaw left", Integer.parseInt(splitCommand[2]));
								break;
							case "right":
								rombi.move("yaw right", Integer.parseInt(splitCommand[2]));
								break;
							default:
								System.out.println("Invalid Direction");
								break;
						}
						break;
					case "surface":
						splitCommand = command.split("\\s");
						if(splitCommand.length != 2) {
							System.out.println("Invalid arguments for command surface. See help");
						}
						rombi.move("surface", Integer.parseInt(splitCommand[1]));
						break;
					case "dive":
						splitCommand = command.split("\\s");
						if(splitCommand.length != 2) {
							System.out.println("Invalid arguments for command dive. See help");
						}
						rombi.move("dive", Integer.parseInt(splitCommand[1]));
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
			} catch(NumberFormatException e){
				System.out.println("The your speed value is not an integer. Please try again.");
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
				System.out.println("surface <speed> - Order submarine to surface");
				System.out.println("dive <speed> - Order submarine to dive");
				System.out.println("exit - Shut sub down.");
				break;
			default:
				
		}
	}
}
